/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laba2;

import java.io.File;
import java.io.IOException;
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

/**
 *
 * @author kateshcherbinina
 */
public class ExcelDataReader {

    public static Map<String, List<Double>> readDataFromExcel(String filePath, int sheetIndex) throws IOException {
        Map<String, List<Double>> dataMap = new HashMap<>();
        List<String> keys = new ArrayList<>();


        try {
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            DataFormatter dataFormatter = new DataFormatter();
            boolean firstRowSkipped = false;
            int columnCount = 0;

            for (Row row : sheet) {
                if (!firstRowSkipped) {
                    firstRowSkipped = true;
                    columnCount = row.getLastCellNum();

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
                        continue;
                    }
                    String key = keys.get(i);
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
