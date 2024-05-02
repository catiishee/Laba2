/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laba2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author kateshcherbinina
 */
public class GUI extends JFrame {

    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JPanel buttonPanel;

    public GUI() {
        setTitle("Статистические показатели");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonPanel = new JPanel(new GridLayout(3, 1));
        createButtons();

        resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
    }

    private void createButtons() {
        JButton importButton = new JButton("Импорт");
        JButton exportButton = new JButton("Экспорт");
        JButton exitButton = new JButton("Выход");

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы XLSX", "xlsx");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    int sheetIndex = getSheetIndexFromUserInput(selectedFile);
                    if (sheetIndex != -1) {
                        updateTable(selectedFile.getAbsolutePath(), sheetIndex);
                    }
                }
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath() + "/Лаба2РезультатЩербининаБ21-901.xlsx";
                    ExcelDataWriter results = new ExcelDataWriter();
                    results.writeDataToExcel(path, convertToStringArray(tableModel));
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(exitButton);
    }

    public void updateTable(String filePath, int sheetIndex) {
        String[][] data = StatsCreation.getStats(filePath, sheetIndex);

        String[] columnNames = data[0];
        String[][] tableData = Arrays.copyOfRange(data, 1, data.length);

        if (tableModel == null) {
            tableModel = new DefaultTableModel(tableData, columnNames);
            resultTable.setModel(tableModel);
        } else {
            tableModel.setDataVector(tableData, columnNames);
            tableModel.fireTableStructureChanged();
        }
    }

//    public int getSheetIndexFromUserInput() {
//        JTextField sheetIndexField = new JTextField(5);
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Индекс листа:"));
//        panel.add(sheetIndexField);
//
//        int result = JOptionPane.showConfirmDialog(null, panel, "Введите номер варианта", JOptionPane.OK_CANCEL_OPTION);
//        if (result == JOptionPane.OK_OPTION) {
//            String input = sheetIndexField.getText();
//            try {
//                int value = Integer.parseInt(input);
//                if (value >= 0 && value <= 9) {
//                    return value;
//                } else {
//                    throw new NumberFormatException();
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Некорректный ввод. Пожалуйста, введите число от 0 до 9.", "Ошибка", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//        return -1;
//    }
    public int getSheetIndexFromUserInput(File file) {
        try (Workbook workbook = WorkbookFactory.create(file)) {
            int numSheets = workbook.getNumberOfSheets();
            JTextField sheetIndexField = new JTextField(5);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Индекс листа (от 0 до " + (numSheets - 1) + "):"));
            panel.add(sheetIndexField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Введите номер варианта", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String input = sheetIndexField.getText();
                try {
                    int value = Integer.parseInt(input);
                    if (value >= 0 && value < numSheets) {
                        return value;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Некорректный ввод. Пожалуйста, введите число от 0 до " + (numSheets - 1) + ".", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка при открытии файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    public static String[][] convertToStringArray(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();
        String[][] data = new String[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Object value = model.getValueAt(i, j);
                data[i][j] = (value == null) ? "" : value.toString();
            }
        }
        return data;
    }
}
