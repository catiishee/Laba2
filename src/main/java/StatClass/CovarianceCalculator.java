/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author user
 */
//public class CovarianceCalculator {
//
//    public static double[][] calculateCovarianceCoefficients(ArrayList<String[]> records) {
//        int numCols = records.get(0).length;
//        double[][] covarianceCoefficients = new double[numCols][numCols];
//
//        for (int i = 0; i < numCols; i++) {
//            for (int j = 0; j < numCols; j++) {
//                DescriptiveStatistics stats = new DescriptiveStatistics();
//                for (String[] record : records) {
//                    double value1 = Double.parseDouble(record[i].replace(",", "."));
//                    double value2 = Double.parseDouble(record[j].replace(",", "."));
//                    stats.addValue(value1 * value2);
//                }
//                covarianceCoefficients[i][j] = stats.getMean();
//            }
//        }
//        return covarianceCoefficients;
//    }
//
//}
public class CovarianceCalculator {

    public static double[][] calculateCovarianceCoefficients(Map<String, List<Double>> dataMap) {
        int numCols = dataMap.size();
        double[][] covarianceCoefficients = new double[numCols][numCols];

        List<String> keys = List.copyOf(dataMap.keySet());

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numCols; j++) {
                List<Double> values1 = dataMap.get(keys.get(i));
                List<Double> values2 = dataMap.get(keys.get(j));
                DescriptiveStatistics stats = new DescriptiveStatistics();
                int dataSize = Math.min(values1.size(), values2.size());

                for (int k = 0; k < dataSize; k++) {
                    double value1 = values1.get(k);
                    double value2 = values2.get(k);
                    stats.addValue(value1 * value2);
                }
                covarianceCoefficients[i][j] = stats.getMean();
            }
        }
        return covarianceCoefficients;
    }
}

