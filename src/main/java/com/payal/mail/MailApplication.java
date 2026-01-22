package com.payal.mail;

import com.payal.mail.config.MailConfig;
import com.payal.mail.model.HrDetails;
import com.payal.mail.service.ExcelReaderService;
import com.payal.mail.service.MailService;
import com.payal.mail.service.TemplateService;

import java.util.List;

public class MailApplication {

    public static void main(String[] args) {

        System.out.println("===================  Mail Application  ====================");

        // configuration
        MailConfig mailConfig = new MailConfig();

        // initialize services
        ExcelReaderService excelService = new ExcelReaderService();
        TemplateService templateService = new TemplateService();
        MailService mailService = new MailService(mailConfig);

        // read HR details from Excel
        List<HrDetails> hrList = excelService.readHrDetails();

        //System.out.println("Total HRs : " + hrList.size());

        // loop through each HR to send mail
        for (HrDetails hr : hrList) {
            System.out.println("\nmail for: " + hr.getName() + " - " + hr.getCompany() );

            // mail body
            String body = templateService.getMailBody(hr);

            // send mail
            mailService.sendMail(hr, body);
        }

        System.out.println("\n----------------------------------------------------------------");
    }
}
