/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author user
 */
public class GeometricMeanCalculator {

    public static double[] calculateGeometricMean(Map<String, List<Double>> dataMap) {
        int columnCount = dataMap.size();
        double[] geometricMeans = new double[columnCount];
        int columnIndex = 0;

        for (List<Double> columnData : dataMap.values()) {
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value : columnData) {
                stats.addValue(Math.abs(value));
            }
            geometricMeans[columnIndex++] = stats.getGeometricMean();
        }
        return geometricMeans;
    }
}
