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
//public class MinMaxCalculator {
//
//    public static double[][] calculateMinMax(ArrayList<String[]> records) {
//        double[][] minMax = new double[2][3];
//        for (int i = 0; i < 3; i++) {
//            DescriptiveStatistics stats = new DescriptiveStatistics();
//            for (String[] record : records) {
//                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
//            }
//            minMax[0][i] = stats.getMin();
//            minMax[1][i] = stats.getMax();
//        }
//        return minMax;
//    }
//}
public class MinMaxCalculator {

    public static double[][] calculateMinMax(Map<String, List<Double>> dataMap) {
        int columnCount = dataMap.size();
        double[][] minMax = new double[2][columnCount];
        int columnIndex = 0;

        for (List<Double> columnData : dataMap.values()) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(value);
            }
            minMax[0][columnIndex] = stats.getMin();
            minMax[1][columnIndex++] = stats.getMax();
        }
        return minMax;
    }
}
