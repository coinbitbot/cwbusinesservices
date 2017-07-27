package com.cwbusinesservices.storage.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.storage.IStorage;
import com.cwbusinesservices.storage.IStorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Created by Andrii on 05.10.2016.
 */
@Service
public class StorageServiceImpl implements IStorageService {

    @Autowired
    private IStorage storage;

    @Override
    public boolean uploadRequestFile(int id, MultipartFile file) throws NoSuchEntityException, ServiceErrorException {
        uploadSingleFile(id, file, REQUESTS_FOLDER);

        return true;
    }

    @Override
    public void getRequestFile(int id, HttpServletResponse response) throws NoSuchEntityException, StorageException {
        getSingleFile(id, response, REQUESTS_FOLDER);
    }

    @Override
    public boolean requestHasFile(int id) throws NoSuchEntityException {
        return checkIfSingleFileExists(id, REQUESTS_FOLDER);
    }

    @Override
    public boolean uploadServiceFile(int id, MultipartFile file) throws NoSuchEntityException, ServiceErrorException {
        uploadSingleFile(id, file, SERVICES_FOLDER);

        return true;
    }

    @Override
    public void getServiceFile(int id, HttpServletResponse response) throws NoSuchEntityException, ServiceErrorException, StorageException {
        getSingleFile(id, response, SERVICES_FOLDER);
    }

    @Override
    public boolean serviceHasFile(int id) throws NoSuchEntityException {
        return checkIfSingleFileExists(id, SERVICES_FOLDER);
    }

    /**
     * use this if entity has a single file
     * if entity has something like gallery then this method is not for you
     *
     * @param id - entity id
     * @param file - file to be saved
     * @param folder - entity folder
     * @throws IOException
     * @throws ServiceErrorException
     */
    private void uploadSingleFile(int id, MultipartFile file, String folder) throws ServiceErrorException {
        File entityFolder = new File(ROOT_DIR + folder + '/' + id);
        if(!entityFolder.exists())
            entityFolder.mkdirs();

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
    }

    /**
     * put single entity file to output stream
     * use this only for entities that has a single file, not a gallery
     *
     * @param id - entity id
     * @param response - user response to set headers and put file
     * @param folder - entity folder
     */
    private void getSingleFile(int id, HttpServletResponse response, String folder) throws NoSuchEntityException, StorageException {
        try {
            File entityFolder = new File(ROOT_DIR + folder + '/' + id);
            if (!entityFolder.exists())
                throw new NoSuchEntityException(folder, "id: " + id);

            File file = fileByPartName(entityFolder, id + "");
            if (file == null) {
                throw new NoSuchEntityException(folder, "no file with id: " + id);
            }

            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");

            response.getOutputStream().write(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new StorageException("problems while loading file");
        }
    }

    /**
     * use this for entities with one file
     * check if entity has fle
     *
     * @param id - entity id
     * @param folder - entity folder
     * @return <code>true</code> if file exists, <code>false</code> otherwise
     * @throws NoSuchEntityException
     */
    private boolean checkIfSingleFileExists(int id, String folder) throws NoSuchEntityException {
        File entityFolder = new File(ROOT_DIR + REQUESTS_FOLDER + '/' + id);
        if (!entityFolder.exists())
            throw new NoSuchEntityException(folder, "id: " + id);

        return fileByPartName(entityFolder, id + "") != null;
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
                if (f.isFile() && name.equals(fileName(f))) {
                    return f;
                }
            }
        }

        return null;
    }

    private String fileName(File file) {
        int dot = file.getName().lastIndexOf('.');
        if (dot > -1) {
            return file.getName().substring(0, dot);
        }

        return file.getName();
    }

    private void close(Closeable stream) {
        try {
            stream.close();
        } catch (Exception e) { }
    }

    private final String ROOT_DIR = System.getProperty("catalina.home") + "/cw-business-services";

    private final String REQUESTS_FOLDER = "/requests";
    private final String SERVICES_FOLDER = "/services";

    @Value("${remote_storage.use}")
    private boolean useRemote;
}
