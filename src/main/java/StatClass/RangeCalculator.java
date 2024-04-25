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
//public class RangeCalculator {
//
//    public static double[] calculateRange(ArrayList<String[]> records) {
//        double[] ranges = new double[3];
//        for (int i = 0; i < 3; i++) {
//            DescriptiveStatistics stats = new DescriptiveStatistics();
//            for (String[] record : records) {
//                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
//            }
//            ranges[i] = stats.getMax() - stats.getMin();
//        }
//        return ranges;
//    }
//
//}
public class RangeCalculator {

    public static double[] calculateRange(Map<String, List<Double>> dataMap) {
        int columnCount = dataMap.size();
        double[] ranges = new double[columnCount];
        int columnIndex = 0;

        for (List<Double> columnData : dataMap.values()) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(value);
            }
            ranges[columnIndex++] = stats.getMax() - stats.getMin();
        }
        return ranges;
    }
}
