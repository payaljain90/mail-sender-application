package com.payal.mail.config;

import java.io.InputStream;
import java.util.Properties;

public class MailConfig {

    // variable to store values from application.properties file
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String password;
    private String fromName;
    private String resumePath;

    // called when MailConfig object is created
    public MailConfig() {
        loadProperties();
    }

    // reads application.properties file
    private void loadProperties() {

        try {
            // loading application.properties from res folder
            InputStream inputStream =getClass().getClassLoader().getResourceAsStream("application.properties");

            //if inputStream is null
            if (inputStream == null) {
                throw new RuntimeException("application.properties file not found in resources folder");
            }

            // Properties class is used to read .properties file
            Properties properties = new Properties();
            properties.load(inputStream);

            // Read values from properties 
            smtpHost = properties.getProperty("mail.smtp.host");
            smtpPort = Integer.parseInt(
                    properties.getProperty("mail.smtp.port"));
            username = properties.getProperty("mail.username");
            password = properties.getProperty("mail.password");
            fromName = properties.getProperty("mail.from");
            resumePath = properties.getProperty("resume.path");

        } catch (Exception e) {
            throw new RuntimeException("Error while loading mail configuration", e);
        }
    }

    // getter methods to access configuration values
    public String getSmtpHost() {
        return smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFromName() {
        return fromName;
    }

    public String getResumePath() {
        return resumePath;
    }
}
