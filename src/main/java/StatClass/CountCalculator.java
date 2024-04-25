/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
//public class CountCalculator {
//
//    public static int[] calculateSampleSize(ArrayList<String[]> records) {
//        int[] sampleSizes = new int[3];
//        for (int i = 0; i < 3; i++) {
//            sampleSizes[i] = records.size();
//        }
//        return sampleSizes;
//    }
//
//}
public class CountCalculator {

    public static int[] calculateSampleSize(Map<String, List<Double>> dataMap) {
        int sampleSize = dataMap.size();

        int[] sampleSizes = new int[sampleSize];
        int index = 0;
        for (List<Double> data : dataMap.values()) {
            sampleSizes[index] = data.size();
            index++;
        }

        return sampleSizes;
    }
}
