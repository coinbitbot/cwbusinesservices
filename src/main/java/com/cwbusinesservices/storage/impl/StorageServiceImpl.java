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
    public boolean uploadRequestFile(int id, MultipartFile file) throws NoSuchEntityException, ServiceErrorException, IOException, ValidationException {
        File papersContainer = new File(ROOT_DIR + REQUESTS_FOLDER + '/' + id);
        if(!papersContainer.exists())
            papersContainer.mkdirs();

        String fileName = id + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        final File serverFile = new File(papersContainer.getAbsolutePath() + '/' + fileName);
        copyFile(file, serverFile);

        if (useRemote) {
            async(() -> {
                try {
                    storage.upload(Files.readAllBytes(serverFile.toPath()), fileName, REQUESTS_FOLDER + '/' + id);
                } catch (StorageException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return true;
    }

    @Override
    public void getRequestFile(int id, HttpServletResponse response) throws NoSuchEntityException, ForbiddenException, ServiceErrorException, StorageException {
        try {
            File folder = new File(ROOT_DIR + REQUESTS_FOLDER + '/' + id);
            if (!folder.exists())
                throw new NoSuchEntityException("request", "id: " + id);

            File file = fileByPartName(folder, id + "");
            if (file == null) {
                throw new NoSuchEntityException("request", "no file for request with id: " + id);
            }

            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");

            response.getOutputStream().write(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new StorageException("problems while loading file");
        }
    }

    @Override
    public boolean requestHasFile(int id) throws NoSuchEntityException {
        File folder = new File(ROOT_DIR + REQUESTS_FOLDER + '/' + id);
        if (!folder.exists())
            throw new NoSuchEntityException("publication", "id: " + id);

        return fileByPartName(folder, id + "") != null;
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

    @Value("${remote_storage.use}")
    private boolean useRemote;
}
