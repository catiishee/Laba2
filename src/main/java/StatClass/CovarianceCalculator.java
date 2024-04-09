/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author user
 */
public class CovarianceCalculator {

    public static double[][] calculateCovarianceCoefficients(ArrayList<String[]> records) {
        int numCols = records.get(0).length;
        double[][] covarianceCoefficients = new double[numCols][numCols];

        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numCols; j++) {
                DescriptiveStatistics stats = new DescriptiveStatistics();
                for (String[] record : records) {
                    double value1 = Double.parseDouble(record[i].replace(",", "."));
                    double value2 = Double.parseDouble(record[j].replace(",", "."));
                    stats.addValue(value1 * value2);
                }
                covarianceCoefficients[i][j] = stats.getMean();
            }
        }
        return covarianceCoefficients;
    }

}
