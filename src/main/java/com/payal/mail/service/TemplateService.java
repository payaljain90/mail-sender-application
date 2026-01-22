package com.payal.mail.service;

import com.payal.mail.model.HrDetails;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TemplateService {

    public String getMailBody(HrDetails hr) {
        try {
            // load the template from resources - during execution resources become the part of .jar folder so we need to load files from classpath
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("mail_body.txt");

            if (inputStream == null) {
                throw new RuntimeException("mail_body.txt not found in resources");
            }

            // read the template as a string
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            Scanner sc = new Scanner(System.in);

            // HR-specific data comes from HrDetails object
            String firstName = hr.getName();
            String companyName = hr.getCompany();

            //taking i/p from user for other info
            System.out.print("Enter specific product or team: ");
            String specificProductOrTeam = sc.nextLine();

            System.out.print("Enter something you noticed: ");
            String specificThingYouNoticed = sc.nextLine();

            System.out.print("Enter your role: ");
            String yourRole = sc.nextLine();

            System.out.print("Enter years of experience: ");
            String yearsOfExperience = sc.nextLine();

            System.out.print("Enter domain: ");
            String domain = sc.nextLine();

            System.out.print("Enter skill1: ");
            String skill1 = sc.nextLine();

            System.out.print("Enter skill2: ");
            String skill2 = sc.nextLine();

            System.out.print("Enter skill3: ");
            String skill3 = sc.nextLine();

            System.out.print("Enter achievement with result: ");
            String achievementWithResult = sc.nextLine();

            System.out.print("Enter relevant area: ");
            String relevantArea = sc.nextLine();

            System.out.print("Enter specific area you can impact: ");
            String specificAreaYouCanImpact = sc.nextLine();

            System.out.print("Enter your name: ");
            String yourName = sc.nextLine();

            // replace placeholders
            body = body.replace("{firstName}", firstName);
            body = body.replace("{companyName}", companyName);
            body = body.replace("{specificProductOrTeam}", specificProductOrTeam);
            body = body.replace("{specificThingYouNoticed}", specificThingYouNoticed);
            body = body.replace("{yourRole}", yourRole);
            body = body.replace("{yearsOfExperience}", yearsOfExperience);
            body = body.replace("{domain}", domain);
            body = body.replace("{skill1}", skill1);
            body = body.replace("{skill2}", skill2);
            body = body.replace("{skill3}", skill3);
            body = body.replace("{achievementWithResult}", achievementWithResult);
            body = body.replace("{relevantArea}", relevantArea);
            body = body.replace("{specificAreaYouCanImpact}", specificAreaYouCanImpact);
            body = body.replace("{yourName}", yourName);

            return body;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load mail body template", e);
        }
    }
}
