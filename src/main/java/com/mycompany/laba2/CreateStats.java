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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kateshcherbinina
 */
public class CreateStats {

    public static void createStats(GUI mainFrame, String filePath, int sheetIndex) {
        try {
            ArrayList<String[]> data = ExcelDataReader.readDataFromExcel(filePath, sheetIndex);

            double[] result1 = GeometricMeanCalculator.calculateGeometricMean(data);
            mainFrame.addRowToTable("Среднее геометрическое", result1);

            double[] result2 = ArithmeticMeanCalculator.calculateArithmeticMean(data);
            mainFrame.addRowToTable("Среднее арифметическое", result2);

            double[] result3 = StandardDeviationCalculator.calculateStandardDeviation(data);
            mainFrame.addRowToTable("Стандартное отклонение", result3);

            double[] result4 = RangeCalculator.calculateRange(data);
            mainFrame.addRowToTable("Размах", result4);

            double[][] result5 = CovarianceCalculator.calculateCovarianceCoefficients(data);
            String[] header1 = {"Коэффициенты ковариации", "X", "Y", "Z"};
            mainFrame.addRowToTable(header1);
            mainFrame.addRowToTable("X", result5[0]);
            mainFrame.addRowToTable("Y", result5[1]);
            mainFrame.addRowToTable("Z", result5[2]);

            int[] result6 = CountCalculator.calculateSampleSize(data);
            mainFrame.addRowToTable("Количество элементов", new double[]{result6[0], result6[1], result6[2]});

            double[] result7 = CoefficientOfVariationCalculator.calculateCoefficientOfVariation(data);
            mainFrame.addRowToTable("Коэффициент вариации", result7);

            double[][] result8 = ConfidenceIntervalCalculator.calculateConfidenceInterval(data);
            mainFrame.addRowToTable("Доверительный интервал(нижняя граница)", result8[0]);
            mainFrame.addRowToTable("Доверительный интервал(верхняя граница)", result8[1]);

            double[] result9 = VarianceCalculator.calculateVariance(data);
            mainFrame.addRowToTable("Дисперсия", result9);

            double[][] result10 = MinMaxCalculator.calculateMinMax(data);
            mainFrame.addRowToTable("Минимум", result10[0]);
            mainFrame.addRowToTable("Максимум", result10[1]);

        } catch (IOException ex) {
            Logger.getLogger(CreateStats.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
