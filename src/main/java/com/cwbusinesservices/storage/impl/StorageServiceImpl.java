package com.cwbusinesservices.storage.impl;

import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.storage.IStorage;
import com.cwbusinesservices.storage.IStorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Andrii on 05.10.2016.
 */
@Service
public class StorageServiceImpl implements IStorageService {

    @Autowired
    private IStorage storage;

    /**
     * use this if entity has a single file
     * if entity has something like gallery then this method is not for you
     *
     * @param id - entity id
     * @param file - file to be saved
     * @param type - entity type
     * @throws ServiceErrorException
     */
    @Override
    public Boolean uploadFile(int id, MultipartFile file, FileEntityTypeEnum type) throws ServiceErrorException {
        String folder = folders.get(type);

        File entityFolder = new File(ROOT_DIR + folder + '/' + id);
        if(!entityFolder.exists())
            entityFolder.mkdirs();

        deleteFile(id, type);

        String fileName = id + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        final File serverFile = new File(entityFolder.getAbsolutePath() + '/' + fileName);
        try {
            copyFile(file, serverFile);
        } catch (IOException e) {
            throw new ServiceErrorException();
        }

        if (useRemote) {
            async(() -> {
                try {
                    storage.upload(Files.readAllBytes(serverFile.toPath()), fileName, folder + '/' + id);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StorageException e) {
                    e.printStackTrace();
                }
            });
        }

        return true;
    }

    @Override
    public Boolean deleteFile(int id, FileEntityTypeEnum type) throws ServiceErrorException {
        String folder = folders.get(type);

        File entityFolder = new File(ROOT_DIR + folder + '/' + id);
        if (!entityFolder.exists())
            return true;

        File file = fileByPartName(entityFolder, String.valueOf(id));
        if (file != null) {
            file.setWritable(true);
            file.delete();
        }
        return true;
    }

    /**
     * put single entity file to output stream
     * use this only for entities that has a single file, not a gallery
     *
     * @param id - entity id
     * @param response - user response to set headers and put file
     * @param type - entity type
     */
    @Override
    public void getFile(int id, HttpServletResponse response, FileEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException {
        String folder = folders.get(type);

        try {
            File entityFolder = new File(ROOT_DIR + folder + '/' + id);
            if (!entityFolder.exists())
                throw new NoSuchEntityException(folder, "id: " + id);

            File file = fileByPartName(entityFolder, id + "");
            if (file == null) {
                throw new NoSuchEntityException(folder, "no file with id: " + id);
            }

            setHeaders(file, response);

            response.getOutputStream().write(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new StorageException("problems while loading file");
        }
    }

    private boolean isFileImage(File file) {
        Pattern pattern = Pattern.compile("(png|jpg|jpeg)$");
        String extension = FilenameUtils.getExtension(file.getName());

        return pattern.matcher(extension).matches();
    }

    /**
     * use this for entities with one file
     * check if entity has fle
     *
     * @param id - entity id
     * @param type - entity type
     * @return <code>true</code> if file exists, <code>false</code> otherwise
     */
    @Override
    public Boolean hasFile(int id, FileEntityTypeEnum type) {
        String folder = folders.get(type);

        File entityFolder = new File(ROOT_DIR + folder + '/' + id);
        if (!entityFolder.exists())
            return false;

        return fileByPartName(entityFolder, id + "") != null;
    }

    @Override
    public Boolean uploadImage(MultipartFile file, String name) throws ServiceErrorException {
        File entityFolder = new File(ROOT_DIR + PLAIN_IMAGES_FOLDER);
        if(!entityFolder.exists())
            entityFolder.mkdirs();

        if (StringUtils.isBlank(name)) {
            name = file.getOriginalFilename();
        } else {
            name += "." + FilenameUtils.getExtension(file.getOriginalFilename());
        }

        final File serverFile = new File(entityFolder.getAbsolutePath() + '/' + name);
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            throw new ServiceErrorException();
        }

        if (useRemote) {
            async(() -> {
                try {
                    storage.upload(Files.readAllBytes(serverFile.toPath()), serverFile.getName(), PLAIN_IMAGES_FOLDER);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StorageException e) {
                    e.printStackTrace();
                }
            });
        }

        return true;
    }

    @Override
    public Boolean deleteImage(int number) throws ServiceErrorException {
        File rootFolder = new File(ROOT_DIR + PLAIN_IMAGES_FOLDER);
        if (!rootFolder.exists())
            return true;

        File[] files = rootFolder.listFiles();
        if (files != null && number >= 0 && number < files.length) {
            File image = files[number];

            image.setWritable(true);
            return image.delete();
        }

        return true;
    }

    @Override
    public void getImage(String name, HttpServletResponse response) throws NoSuchEntityException, ServiceErrorException, StorageException {
        try {
            File rootFolder = new File(ROOT_DIR + PLAIN_IMAGES_FOLDER);
            if (!rootFolder.exists())
                return;

            File image = fileByPartName(rootFolder, FilenameUtils.getBaseName(name));
            if (image != null) {
                setHeaders(image, response);

                response.getOutputStream().write(Files.readAllBytes(image.toPath()));
            }
        } catch (IOException e) {
            throw new StorageException("problems while loading file");
        }
    }

    @Override
    public int countImages() {
        File rootFolder = new File(ROOT_DIR + PLAIN_IMAGES_FOLDER);
        if (!rootFolder.exists())
            return 0;

        File[] files = rootFolder.listFiles();

        return files != null ? files.length : 0;
    }

    @Override
    public List<String> getImagesData() {
        File rootFolder = new File(ROOT_DIR + PLAIN_IMAGES_FOLDER);
        if (!rootFolder.exists())
            return new ArrayList<>();

        File[] files = rootFolder.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(files)
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private void copyFile(MultipartFile source, File result) throws IOException, ServiceErrorException {
        if(source == null || source.getBytes() == null || source.getBytes().length == 0 || result == null)
            throw new ServiceErrorException();

        FileOutputStream fileOutputStream = new FileOutputStream(result);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(source.getBytes());

        close(bufferedOutputStream);
        close(fileOutputStream);
    }

    private void async(Runnable runnable) {
        new Thread(runnable).start();
    }

    /**
     * search file in <code>folder</code> by it's part name(without extension)
     *
     * @param folder - folder where search should be applied
     * @param name - file name without extension
     *
     * @return <code>file</code> found by part name
     */
    private File fileByPartName(File folder, String name) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile() && name.equals(FilenameUtils.getBaseName(f.getName()))) {
                    return f;
                }
            }
        }

        return null;
    }

    private void setHeaders(File file, HttpServletResponse response) {
        Date lastModified = new Date(file.lastModified());
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        String lastModifiedFormatted = format.format(lastModified);

        response.setContentLength((int)file.length());
        response.setHeader("Last-Modified", lastModifiedFormatted);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        if (isFileImage(file)) {
            response.setContentType("image/"+FilenameUtils.getExtension(file.getName()));
            response.setDateHeader("Expires", new Date(new Date().getTime() + MAX_IMAGE_LIVE).getTime());
            response.setHeader("Cache-Control", "max-age=" + MAX_IMAGE_LIVE);
            response.setHeader("Pragma", "max-age=" + MAX_IMAGE_LIVE);
            response.setHeader("Location", file.getName());
        } else {
            response.setContentType("application/force-download");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        }
    }

    private void close(Closeable stream) {
        try {
            stream.close();
        } catch (Exception e) { }
    }

    //30 minutes
    private static final int MAX_IMAGE_LIVE = 1000 * 60 * 30;

    private final String ROOT_DIR = System.getProperty("catalina.home") + "/cw-business-services";

    private static final String REQUESTS_FOLDER = "/requests";
    private static final String REQUESTS_COMMENT_FOLDER = "/requests_comments";
    private static final String SERVICES_FOLDER = "/services";
    private static final String COMPANY_FOLDER = "/companies";
    private static final String POST_FOLDER = "/posts";
    private static final String EMPLOYEES_FOLDER = "/employees";
    private static final String CAROUSEL_IMAGE_FOLDER = "/carousel_image";
    private static final String PLAIN_IMAGES_FOLDER = "/plain_images";

    private static final Map<FileEntityTypeEnum, String> folders = new HashMap<FileEntityTypeEnum, String>(){
        {
            put(FileEntityTypeEnum.COMPANY, COMPANY_FOLDER);
            put(FileEntityTypeEnum.SERVICE, SERVICES_FOLDER);
            put(FileEntityTypeEnum.REQUEST, REQUESTS_FOLDER);
            put(FileEntityTypeEnum.REQUEST_COMMENT, REQUESTS_COMMENT_FOLDER);
            put(FileEntityTypeEnum.POST, POST_FOLDER);
            put(FileEntityTypeEnum.EMPLOYEE, EMPLOYEES_FOLDER);
            put(FileEntityTypeEnum.CAROUSEL_IMAGE, CAROUSEL_IMAGE_FOLDER);
        }
    };

    @Value("${remote_storage.use}")
    private boolean useRemote;
}
