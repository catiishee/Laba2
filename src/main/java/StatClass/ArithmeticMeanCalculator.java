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
//public class ArithmeticMeanCalculator {
//
//    public static double[] calculateArithmeticMean(ArrayList<String[]> records) {
//        double[] arithmeticMeans = new double[3];
//        for (int i = 0; i < 3; i++) {
//            DescriptiveStatistics stats = new DescriptiveStatistics();
//            for (String[] record : records) {
//                stats.addValue(Double.parseDouble(record[i].replace(",", ".")));
//            }
//            arithmeticMeans[i] = stats.getMean();
//        }
//        return arithmeticMeans;
//    }
//}
public class ArithmeticMeanCalculator {

    public static double[] calculateArithmeticMean(Map<String, List<Double>> dataMap) {
        int columnCount = dataMap.size();
        double[] arithmeticMeans = new double[columnCount];
        int columnIndex = 0;

        for (List<Double> columnData : dataMap.values()) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(value);
            }
            arithmeticMeans[columnIndex++] = stats.getMean();
        }
        return arithmeticMeans;
    }
}
