/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class CovarianceCalculator {

    public static double[][] calculateCovarianceCoefficients(Map<String, List<Double>> dataMap) {
        int numCols = dataMap.size();
        double[][] covarianceCoefficients = new double[numCols][numCols];

        List<String> keys = List.copyOf(dataMap.keySet());

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numCols; j++) {
                List<Double> values1 = dataMap.get(keys.get(i));
                List<Double> values2 = dataMap.get(keys.get(j));
                double mean1 = values1.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
                double mean2 = values2.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

                double sum = 0.0;
                int dataSize = Math.min(values1.size(), values2.size());
                for (int k = 0; k < dataSize; k++) {
                    double value1 = values1.get(k);
                    double value2 = values2.get(k);
                    sum += (value1 - mean1) * (value2 - mean2);
                }

                covarianceCoefficients[i][j] = sum / (dataSize - 1);
            }
        }
        return covarianceCoefficients;
    }
}
