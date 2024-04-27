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
public class CountCalculator {

    public static double[] calculateSampleSize(Map<String, List<Double>> dataMap) {
        int sampleSize = dataMap.size();

        double[] sampleSizes = new double[sampleSize];
        int index = 0;
        for (List<Double> data : dataMap.values()) {
            sampleSizes[index] = data.size();
            index++;
        }

        return sampleSizes;
    }
}
