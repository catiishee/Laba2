/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author user
 */
public class ConfidenceIntervalCalculator {
    private static final double DEFAULT_CONFIDENCE_LEVEL = 0.95;

    public static double[][] calculateConfidenceInterval(ArrayList<String[]> records) {
        return calculateConfidenceInterval(records, DEFAULT_CONFIDENCE_LEVEL);
    }

    private static double[][] calculateConfidenceInterval(ArrayList<String[]> records, double confidenceLevel) {
        double[][] confidenceIntervals = new double[2][3];
        double zScore = new NormalDistribution().inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);

        for (int i = 0; i < 3; i++) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (String[] record : records) {
                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
            }
            double mean = stats.getMean();
            double stdDev = stats.getStandardDeviation();
            double marginOfError = zScore * stdDev / Math.sqrt(stats.getN());
            confidenceIntervals[0][i] = mean - marginOfError;
            confidenceIntervals[1][i] = mean + marginOfError;
        }

        return confidenceIntervals;
    }
}
