package com.cwbusinesservices.services.mailing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.cwbusinesservices.pojo.enums.EmailTypes;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * Created by KutsykV on 24.01.2016.
 */
@Component
public class EmailBuilder {

    @Value("${mail.host}")
    private String host;

    public String getEmailContent(EmailTypes typeOfEmail, Map<String, String> data, Locale locale) {
        try {
            String content = readResourceText("email/" + locale.getLanguage() + "/email." + typeOfEmail.toString() + ".html");

            // based on email type return email content
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String readResourceText(String resourceName) throws IOException {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            StringBuilder content = new StringBuilder();
            Resource resource = new ClassPathResource(resourceName);
            fileInputStream = new FileInputStream(resource.getFile().getAbsolutePath());
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");
            reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            return content.toString();
        } finally {
            close(reader);
            close(inputStreamReader);
            close(fileInputStream);
        }
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) { }
        }
    }

}
