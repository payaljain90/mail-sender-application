package com.payal.mail.service;

import com.payal.mail.model.HrDetails;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderService {

    public List<HrDetails> readHrDetails() {

        List<HrDetails> hrList = new ArrayList<>();

        try {
            // load Excel file from resources(during execution excel file stores inside .jar file so we need to load it from classpath)
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("HR_List.xlsx");

            if (inputStream == null) {
                throw new RuntimeException("HR_List.xlsx not found in resources");
            }

            //inputStream to workbook sheet
            Workbook workbook = new XSSFWorkbook(inputStream);
            //taking only 0th numbered sheet 
            Sheet sheet = workbook.getSheetAt(0);

            // starts from 1 to skip header row-0
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            	
            	//row at ith index
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                
                //2th indexed col - row.getCell(2)
                Cell nameCell = row.getCell(2);
                Cell emailCell = row.getCell(3);
                Cell companyCell = row.getCell(5);
                
                //if cell null:" " otherwise : cell to string 
                String name = (nameCell != null) ? nameCell.toString().trim() : "";
                String email = (emailCell != null) ? emailCell.toString().trim() : "";
                String company = (companyCell != null) ? companyCell.toString().trim() : "";

                HrDetails hr = new HrDetails(name, email, company);
                hrList.add(hr);
            }

            workbook.close();

        } catch (Exception e) {
            throw new RuntimeException("Error while reading HR details from Excel", e);
        }

        return hrList;
    }
}
