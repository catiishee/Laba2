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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author kateshcherbinina
 */
public class StatsCreation {

    public static String[][] getStats(String filePath, int sheetIndex) {
        try {
            Map<String, List<Double>> data = ExcelDataReader.readDataFromExcel(filePath, sheetIndex);
            List<String> columnOrder = new ArrayList<>(data.keySet());
            int size = columnOrder.size();
            List<String[]> resultList = new ArrayList<>();

            String[] headerRow = new String[size + 1];
            headerRow[0] = "";
            int index = 1;
            for (String key : columnOrder) {
                headerRow[index++] = key;
            }
            resultList.add(headerRow);

            addRowToResultList(resultList, "Среднее геометрическое", GeometricMeanCalculator.calculateGeometricMean(data));
            addRowToResultList(resultList, "Среднее арифметическое", ArithmeticMeanCalculator.calculateArithmeticMean(data));
            addRowToResultList(resultList, "Стандартное отклонение", StandardDeviationCalculator.calculateStandardDeviation(data));
            addRowToResultList(resultList, "Размах", RangeCalculator.calculateRange(data));
            addCovarianceRowsToResultList(resultList, columnOrder, CovarianceCalculator.calculateCovarianceCoefficients(data));
            addRowToResultList(resultList, "Количество элементов", CountCalculator.calculateSampleSize(data));
            addRowToResultList(resultList, "Коэффициент вариации", CoefficientOfVariationCalculator.calculateCoefficientOfVariation(data));
            addConfidenceIntervalRowsToResultList(resultList, ConfidenceIntervalCalculator.calculateConfidenceInterval(data));
            addRowToResultList(resultList, "Дисперсия", VarianceCalculator.calculateVariance(data));
            addMinMaxRowsToResultList(resultList, MinMaxCalculator.calculateMinMax(data));

            return resultList.toArray(new String[0][]);

        } catch (IOException ex) {
            Logger.getLogger(StatsCreation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static void addRowToResultList(List<String[]> resultList, String header, double[] values) {
        resultList.add(Stream.concat(Stream.of(header), Arrays.stream(values).mapToObj(Double::toString)).toArray(String[]::new));
    }

    private static void addCovarianceRowsToResultList(List<String[]> resultList, List<String> columnOrder, double[][] covarianceMatrix) {
        String[] headerRow = new String[columnOrder.size() + 1];
        headerRow[0] = "Коэффициенты ковариации";
        int index = 1;
        for (String key : columnOrder) {
            headerRow[index++] = key;
        }
        resultList.add(headerRow);

        index = 1;
        for (String key : columnOrder) {
            String[] row = new String[columnOrder.size() + 1];
            row[0] = key;
            for (int i = 0; i < columnOrder.size(); i++) {
                row[i + 1] = Double.toString(covarianceMatrix[index - 1][i]);
            }
            resultList.add(row);
            index++;
        }
    }

    private static void addConfidenceIntervalRowsToResultList(List<String[]> resultList, double[][] confidenceIntervals) {
        for (int i = 0; i < confidenceIntervals.length; i++) {
            String[] row = new String[confidenceIntervals[i].length + 1];
            row[0] = i == 0 ? "Доверительный интервал(нижняя граница)" : "Доверительный интервал(верхняя граница)";

            for (int j = 0; j < confidenceIntervals[i].length; j++) {
                row[j + 1] = Double.toString(confidenceIntervals[i][j]);
            }

            resultList.add(row);
        }
    }

    private static void addMinMaxRowsToResultList(List<String[]> resultList, double[][] minMax) {
        for (int i = 0; i < minMax.length; i++) {
            String[] row = new String[minMax[i].length + 1];
            row[0] = i == 0 ? "Минимум" : "Максимум";
            for (int j = 0; j < minMax[i].length; j++) {
                row[j + 1] = Double.toString(minMax[i][j]);
            }
            resultList.add(row);
        }
    }

}
