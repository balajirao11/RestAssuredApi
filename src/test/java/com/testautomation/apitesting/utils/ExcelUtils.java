package com.testautomation.apitesting.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static List<Map<String, String>> readExcel(String filePath, String sheetName) throws IOException {

        List<Map<String, String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream("src/test/resources/testdata.xls");
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet1 = wb.getSheet("Translations");
        int rows = sheet1.getPhysicalNumberOfRows();
        int cols = sheet1.getRow(0).getLastCellNum();
        System.out.println("File path:" +filePath);
        System.out.println("Sheet name:" +sheetName);
        System.out.println("data:" +data);
        System.out.println("sheet name:" +sheet1);
            // Get the header row
            Row headerRow = sheet1.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }

            // Read data rows
            for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
                Row row = sheet1.getRow(i);
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.put(headers.get(j), cell.toString());
                }
                data.add(rowData);
            }

            wb.close();


        return data;
    }
}