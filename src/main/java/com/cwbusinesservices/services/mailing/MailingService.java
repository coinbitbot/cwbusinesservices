package com.cwbusinesservices.services.mailing;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.services.mailing.sendpulse.sendpulse.restapi.Sendpulse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by KutsykV on 02.11.2015.
 */
@Component
public class MailingService implements IMailingService {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private EmailBuilder emailBuilder;
    @Autowired
    private Sendpulse sendpulse;


    public String smtpListEmails(int limit, int offset, String from, String to, String sender, String recipient) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("{\"data\":[");
        boolean start = true;
        try {
            Date fromDate = formatter.parse(from);
            Date toDate = formatter.parse(to);
            while (toDate.after(fromDate)) {
                Date nextDay = new Date(fromDate.getTime() + (1000 * 60 * 60 * 24));
                int batch = 0;
                Map<String, Object> smtpRes = sendpulse.smtpListEmails(50, 0,
                        formatter.format(fromDate),
                        formatter.format(fromDate),
                        sender, recipient);
                while (smtpRes.get("data").toString().length() > 2) {
                    String tmp = smtpRes.get("data").toString();
                    tmp = tmp.substring(1, tmp.length() - 1);
                    if (start) {
                        resultBuilder.append(tmp);
                        start = false;
                    } else {
                        resultBuilder.append(",").append(tmp);
                    }
                    batch++;
                    offset = batch * 50;
                    smtpRes = sendpulse.smtpListEmails(50, offset,
                            formatter.format(fromDate),
                            formatter.format(fromDate),
                            sender, recipient);
                }
                fromDate = nextDay;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resultBuilder.append("]}");
        return resultBuilder.toString();
    }

    @Override
    public boolean sendEmailToUser(EmailTemplateCodeEnum typeOfEmail, String userEmail, Map<String, String> data) throws BaseException {
        EmailTemplateEntity content = emailBuilder.getEmailContent(typeOfEmail, data);
        return send(userEmail, content);
    }

    @Override
    public boolean sendEmailToUser(EmailTemplateEntity content, String userEmail, Map<String, String> data) throws BaseException {
        emailBuilder.formEmailContent(content, data);
        return send(userEmail, content);
    }

    @Override
    public boolean sendEmailToUsers(EmailTemplateCodeEnum typeOfEmail, List<String> userEmails, Map<String, String> data) throws BaseException {
        for (String user : userEmails) {
            sendEmailToUser(typeOfEmail, user, data);
        }
        return true;
    }

    private boolean send(String toEmail, EmailTemplateEntity content) {
        final ExecutorService service = Executors.newFixedThreadPool(1);
        final Future<String> task = service.submit(new SenderTask(fromEmail, toEmail, content.getText(), content.getSubject()));

        return executeSendTask(service, task);
    }

    private boolean executeSendTask(final ExecutorService service, final Future<String> task) {
        try {
            final String str = task.get(); // this raises ExecutionException if thread dies
            if (str.contains("\"result\":true"))
                return true;
            System.out.println("CAN NOT SEND EMAIL");
        } catch (final InterruptedException ex) {
            ex.printStackTrace();
        } catch (final ExecutionException ex) {
            ex.printStackTrace();
        }
        service.shutdownNow();
        return false;
    }

    private String originalEmail(String email) {
        String login = email.substring(0, email.indexOf('@'));
        int indexOfPlus = login.indexOf('+');
        if (indexOfPlus > -1)
            login = login.substring(0, indexOfPlus);
        return login + email.substring(email.indexOf('@'));
    }

    private String defineEmailToSend(UserEntity toUser, Map<String, String> data) {
        String email;
        if (data != null && data.get("email") != null) {
            email = data.get("email");
        } else
            email = originalEmail(toUser.getEmail());
        return email;
    }

    class SenderTask implements Callable<String> {

        String fromEmail;
        String toEmail;
        String text;
        String subject;

        SenderTask(String fromEmail, String toEmail, String text, String subject) {
            this.fromEmail = fromEmail;
            this.toEmail = toEmail;
            this.text = text;
            this.subject = subject;
        }

        public String call() {
            Map<String, Object> fromSender = new HashMap<String, Object>();
            fromSender.put("name", fromName);
            fromSender.put("email", fromEmail);
            ArrayList<Map> toSend = new ArrayList<Map>();
            Map<String, Object> elementto = new HashMap<String, Object>();
            elementto.put("email", toEmail);
            toSend.add(elementto);
            Map<String, Object> emaildata = new HashMap<String, Object>();
            emaildata.put("html", text);
            emaildata.put("text", text);
            emaildata.put("subject", subject);
            emaildata.put("from", fromSender);
            emaildata.put("to", toSend);
            Map<String, Object> result = (Map<String, Object>) sendpulse.smtpSendMail(emaildata);
            return result.toString();
        }
    }

    @Value("${from.email}")
    private String fromEmail;
    @Value("${from.email.name}")
    private String fromName;

}
