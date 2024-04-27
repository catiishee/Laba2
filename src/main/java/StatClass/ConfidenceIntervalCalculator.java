/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.List;
import java.util.Map;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author user
 */
public class ConfidenceIntervalCalculator {

    private static final double DEFAULT_CONFIDENCE_LEVEL = 0.95;

    public static double[][] calculateConfidenceInterval(Map<String, List<Double>> dataMap) {
        return calculateConfidenceInterval(dataMap, DEFAULT_CONFIDENCE_LEVEL);
    }

    private static double[][] calculateConfidenceInterval(Map<String, List<Double>> dataMap, double confidenceLevel) {
        int columnCount = dataMap.size();
        double[][] confidenceIntervals = new double[2][columnCount];
        int columnIndex = 0;

        double zScore = new NormalDistribution().inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2); // Вычисление Z-значения (стандартизированного значения)
                                                                                                              // для заданного уровня доверия с использованием нормального распределения.
        for (List<Double> columnData : dataMap.values()) {                                                    // Z-значение используется для определения ширины доверительного интервала.
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(value);
            }
            double mean = stats.getMean();
            double stdDev = stats.getStandardDeviation();
            double marginOfError = zScore * stdDev / Math.sqrt(stats.getN());
            confidenceIntervals[0][columnIndex] = mean - marginOfError;
            confidenceIntervals[1][columnIndex] = mean + marginOfError;
            columnIndex++;
        }

        return confidenceIntervals;
    }
}
