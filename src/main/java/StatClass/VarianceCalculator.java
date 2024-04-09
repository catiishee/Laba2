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
public class VarianceCalculator {
    public static double[] calculateVariance(ArrayList<String[]> records) {
        double[] variances = new double[3];
        for (int i = 0; i < 3; i++) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (String[] record : records) {
                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
            }
            variances[i] = stats.getVariance();
        }
        return variances;
    }
}
