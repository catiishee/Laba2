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
//public class StandardDeviationCalculator {
//
//    public static double[] calculateStandardDeviation(ArrayList<String[]> records) {
//        double[] standardDeviations = new double[3];
//        for (int i = 0; i < 3; i++) {
//            DescriptiveStatistics stats = new DescriptiveStatistics();
//            for (String[] record : records) {
//                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
//            }
//            standardDeviations[i] = stats.getStandardDeviation();
//        }
//        return standardDeviations;
//    }
//}
public class StandardDeviationCalculator {

    public static double[] calculateStandardDeviation(Map<String, List<Double>> dataMap) {
        int columnCount = dataMap.size();
        double[] standardDeviations = new double[columnCount];
        int columnIndex = 0;

        for (List<Double> columnData : dataMap.values()) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(value);
            }
            standardDeviations[columnIndex++] = stats.getStandardDeviation();
        }
        return standardDeviations;
    }
}
