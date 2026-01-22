package com.payal.mail.service;

import com.payal.mail.config.MailConfig;
import com.payal.mail.model.HrDetails;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;

import java.io.InputStream;
import java.util.Properties;

public class MailService {

	//to get configuration details we need object
    private final MailConfig mailConfig;

    //initializing mailConfig object - gets initialized when we create object of MailService Class
    public MailService(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    public void sendMail(HrDetails hr, String body) {
        try {
            // properties : instructions to connect with mail server
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", mailConfig.getSmtpHost());
            props.put("mail.smtp.port", String.valueOf(mailConfig.getSmtpPort()));

            // session : create environment to send mail by accepting props and Authenticator
            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(mailConfig.getUsername(), mailConfig.getPassword());
                        }
                    });

            // create message - MimeMessage - we have text + attachment
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailConfig.getUsername(), mailConfig.getFromName()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hr.getEmail()));
            message.setSubject("Job Application");

            // MimeBodyPart - create multipart mail - body + attachment
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            
            // load file from resources
            InputStream is = getClass().getClassLoader().getResourceAsStream(mailConfig.getResumePath());
            // use DataSource with InputStream to read file and its contents
            DataSource dataSource = new ByteArrayDataSource(is, "application/pdf");
            // DataHandler - to convert the file into suitable format that gmail support
            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName(mailConfig.getResumePath());

            // Multipart - combine the multiple body part created by MimeBodyPart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            // setting the content to message
            message.setContent(multipart);

            // send mail
            Transport.send(message);

            System.out.println("Mail sent to " + hr.getEmail());

        } catch (Exception e) {
            System.out.println("Failed to send mail to " + hr.getEmail());
            e.printStackTrace();
        }
    }
}
