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
//public class CoefficientOfVariationCalculator {
//
//    public static double[] calculateCoefficientOfVariation(ArrayList<String[]> records) {
//        double[] coefficientsOfVariation = new double[3];
//        for (int i = 0; i < 3; i++) {
//            DescriptiveStatistics stats = new DescriptiveStatistics();
//            for (String[] record : records) {
//                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
//            }
//            coefficientsOfVariation[i] = stats.getStandardDeviation() / stats.getMean();
//        }
//        return coefficientsOfVariation;
//    }
//
//}
public class CoefficientOfVariationCalculator {

    public static double[] calculateCoefficientOfVariation(Map<String, List<Double>> dataMap) {
        int columnCount = dataMap.size();
        double[] coefficientsOfVariation = new double[columnCount];
        int columnIndex = 0;

        for (List<Double> columnData : dataMap.values()) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(value);
            }
            double mean = stats.getMean();
            double standardDeviation = stats.getStandardDeviation();
            coefficientsOfVariation[columnIndex++] = (mean != 0) ? standardDeviation / mean : 0.0;
        }
        return coefficientsOfVariation;
    }
}

