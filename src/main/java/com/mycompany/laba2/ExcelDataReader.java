/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laba2;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
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
//public class ExcelDataReader {
//
//    public static ArrayList<String[]> readDataFromExcel(String filePath, int sheetIndex) throws IOException {
//        ArrayList<String[]> records = new ArrayList<>();
//        Workbook workbook = null;
//
//        try {
//            workbook = WorkbookFactory.create(new File(filePath));
//            Sheet sheet = workbook.getSheetAt(sheetIndex);
//            DataFormatter dataFormatter = new DataFormatter();
//            boolean firstRowSkipped = false;
//
//            for (Row row : sheet) {
//                if (!firstRowSkipped) {
//                    firstRowSkipped = true;
//                    continue;
//                }
//
//                String[] rowData = new String[3];
//                int columnIndex = 0;
//
//                for (int i = 0; i < 3; i++) {
//                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                    rowData[columnIndex++] = dataFormatter.formatCellValue(cell);
//                }
//
//                records.add(rowData);
//            }
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Ошибка при импорте данных из файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
//        }
//
//        return records;
//    }
//}
public class ExcelDataReader {

    public static Map<String, List<Double>> readDataFromExcel(String filePath, int sheetIndex) throws IOException {
        Map<String, List<Double>> dataMap = new HashMap<>();
        List<String> keys = new ArrayList<>(); // Список для хранения ключей

        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            DataFormatter dataFormatter = new DataFormatter();
            boolean firstRowSkipped = false;
            int columnCount = 0;

            for (Row row : sheet) {
                if (!firstRowSkipped) {
                    firstRowSkipped = true;
                    columnCount = row.getLastCellNum();
                    // Чтение значений первых клеток и добавление в список ключей
                    for (int i = 0; i < columnCount; i++) {
                        Cell cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        String key = dataFormatter.formatCellValue(cell);
                        keys.add(key);
                    }
                    continue;
                }

                for (int i = 0; i < columnCount; i++) {
                    Cell cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    if (cell == null || cell.getCellType() == CellType.BLANK) {
                        continue; // Пропуск пустых ячеек
                    }
                    String key = keys.get(i); // Использование ранее прочитанного ключа
                    if (dataMap.containsKey(key)) {
                        List<Double> columnData = dataMap.get(key);
                        columnData.add(cell.getNumericCellValue());
                    } else {
                        List<Double> columnData = new ArrayList<>();
                        columnData.add(cell.getNumericCellValue());
                        dataMap.put(key, columnData);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при импорте данных из файла: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

        return dataMap;
    }
}
