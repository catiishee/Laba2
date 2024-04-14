/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laba2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author kateshcherbinina
 */
public class ExcelDataReader {

    public static ArrayList<String[]> readDataFromExcel(String filePath, int sheetIndex) throws IOException {
        ArrayList<String[]> records = new ArrayList<>();
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            DataFormatter dataFormatter = new DataFormatter();
            boolean firstRowSkipped = false;

            for (Row row : sheet) {
                if (!firstRowSkipped) {
                    firstRowSkipped = true;
                    continue;
                }

                String[] rowData = new String[3];
                int columnIndex = 0;

                for (int i = 0; i < 3; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[columnIndex++] = dataFormatter.formatCellValue(cell);
                }

                records.add(rowData);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при импорте данных из файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        return records;
    }
}
