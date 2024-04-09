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
public class ArithmeticMeanCalculator {

    public static double[] calculateArithmeticMean(ArrayList<String[]> records) {
        double[] arithmeticMeans = new double[3];
        for (int i = 0; i < 3; i++) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (String[] record : records) {
                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
            }
            arithmeticMeans[i] = stats.getMean();
        }
        return arithmeticMeans;
    }
}
