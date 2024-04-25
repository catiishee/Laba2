/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laba2;

import StatClass.ArithmeticMeanCalculator;
import StatClass.CoefficientOfVariationCalculator;
import StatClass.ConfidenceIntervalCalculator;
import StatClass.CountCalculator;
import StatClass.CovarianceCalculator;
import StatClass.GeometricMeanCalculator;
import StatClass.MinMaxCalculator;
import StatClass.RangeCalculator;
import StatClass.StandardDeviationCalculator;
import StatClass.VarianceCalculator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kateshcherbinina
 */
//public class CreateStats {
//
//    public static void createStats(GUI mainFrame, String filePath, int sheetIndex) {
//        try {
//            Map<String, List<Double>> data = ExcelDataReader.readDataFromExcel(filePath, sheetIndex);
//
//            double[] result1 = GeometricMeanCalculator.calculateGeometricMean(data);
//            mainFrame.addRowToTable("Среднее геометрическое", result1);
//
//            double[] result2 = ArithmeticMeanCalculator.calculateArithmeticMean(data);
//            mainFrame.addRowToTable("Среднее арифметическое", result2);
//
//            double[] result3 = StandardDeviationCalculator.calculateStandardDeviation(data);
//            mainFrame.addRowToTable("Стандартное отклонение", result3);
//
//            double[] result4 = RangeCalculator.calculateRange(data);
//            mainFrame.addRowToTable("Размах", result4);
//
//            double[][] result5 = CovarianceCalculator.calculateCovarianceCoefficients(data);
//            String[] header1 = {"Коэффициенты ковариации", "X", "Y", "Z"};
//            mainFrame.addRowToTable(header1);
//            mainFrame.addRowToTable("X", result5[0]);
//            mainFrame.addRowToTable("Y", result5[1]);
//            mainFrame.addRowToTable("Z", result5[2]);
//
//            int[] result6 = CountCalculator.calculateSampleSize(data);
//            mainFrame.addRowToTable("Количество элементов", new double[]{result6[0], result6[1], result6[2]});
//
//            double[] result7 = CoefficientOfVariationCalculator.calculateCoefficientOfVariation(data);
//            mainFrame.addRowToTable("Коэффициент вариации", result7);
//
//            double[][] result8 = ConfidenceIntervalCalculator.calculateConfidenceInterval(data);
//            mainFrame.addRowToTable("Доверительный интервал(нижняя граница)", result8[0]);
//            mainFrame.addRowToTable("Доверительный интервал(верхняя граница)", result8[1]);
//
//            double[] result9 = VarianceCalculator.calculateVariance(data);
//            mainFrame.addRowToTable("Дисперсия", result9);
//
//            double[][] result10 = MinMaxCalculator.calculateMinMax(data);
//            mainFrame.addRowToTable("Минимум", result10[0]);
//            mainFrame.addRowToTable("Максимум", result10[1]);
//
//        } catch (IOException ex) {
//            Logger.getLogger(CreateStats.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//}
public class CreateStats {

    public static String[][] createStats(String filePath, int sheetIndex) {
        try {

            Map<String, List<Double>> data = ExcelDataReader.readDataFromExcel(filePath, sheetIndex);

            Set<String> keys = data.keySet();
            int size = keys.size();

            List<String[]> resultList = new ArrayList<>();

            String[] headerRow = new String[size + 1];
            headerRow[0] = "";
            int index = 1;
            for (String key : keys) {
                headerRow[index++] = key;
            }
            resultList.add(headerRow);

            double[] result1 = GeometricMeanCalculator.calculateGeometricMean(data);
            resultList.add(new String[]{"Среднее геометрическое", Double.toString(result1[0]), Double.toString(result1[1]), Double.toString(result1[2])});

            double[] result2 = ArithmeticMeanCalculator.calculateArithmeticMean(data);
            resultList.add(new String[]{"Среднее арифметическое", Double.toString(result2[0]), Double.toString(result2[1]), Double.toString(result2[2])});

            double[] result3 = StandardDeviationCalculator.calculateStandardDeviation(data);
            resultList.add(new String[]{"Стандартное отклонение", Double.toString(result3[0]), Double.toString(result3[1]), Double.toString(result3[2])});

            double[] result4 = RangeCalculator.calculateRange(data);
            resultList.add(new String[]{"Размах", Double.toString(result4[0]), Double.toString(result4[1]), Double.toString(result4[2])});

//            Set<String> keys = data.keySet();
//            int size = keys.size();
            double[][] covarianceMatrix = CovarianceCalculator.calculateCovarianceCoefficients(data);

            headerRow = new String[size + 1];
            headerRow[0] = "Коэффициенты ковариации";
            index = 1;
            for (String key : keys) {
                headerRow[index++] = key;
            }
            resultList.add(headerRow);

            index = 1;
            for (String key : keys) {
                String[] row = new String[size + 1];
                row[0] = key;
                for (int i = 0; i < size; i++) {
                    row[i + 1] = Double.toString(covarianceMatrix[index - 1][i]);
                }
                resultList.add(row);
                index++;
            }

            int[] result6 = CountCalculator.calculateSampleSize(data);
            resultList.add(new String[]{"Количество элементов", Integer.toString(result6[0]), Integer.toString(result6[1]), Integer.toString(result6[2])});

            double[] result7 = CoefficientOfVariationCalculator.calculateCoefficientOfVariation(data);
            resultList.add(new String[]{"Коэффициент вариации", Double.toString(result7[0]), Double.toString(result7[1]), Double.toString(result7[2])});

            double[][] result8 = ConfidenceIntervalCalculator.calculateConfidenceInterval(data);
            resultList.add(new String[]{"Доверительный интервал(нижняя граница)", Double.toString(result8[0][0]), Double.toString(result8[0][1]), Double.toString(result8[0][2])});
            resultList.add(new String[]{"Доверительный интервал(верхняя граница)", Double.toString(result8[1][0]), Double.toString(result8[1][1]), Double.toString(result8[1][2])});

            double[] result9 = VarianceCalculator.calculateVariance(data);
            resultList.add(new String[]{"Дисперсия", Double.toString(result9[0]), Double.toString(result9[1]), Double.toString(result9[2])});

            double[][] result10 = MinMaxCalculator.calculateMinMax(data);
            resultList.add(new String[]{"Минимум", Double.toString(result10[0][0]), Double.toString(result10[0][1]), Double.toString(result10[0][2])});
            resultList.add(new String[]{"Максимум", Double.toString(result10[1][0]), Double.toString(result10[1][1]), Double.toString(result10[1][2])});

            String[][] resultArray = new String[resultList.size()][];
            resultList.toArray(resultArray);

            return resultArray;

        } catch (IOException ex) {
            Logger.getLogger(CreateStats.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
