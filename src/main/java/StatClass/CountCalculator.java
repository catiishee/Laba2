/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StatClass;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CountCalculator {
    public static int[] calculateSampleSize(ArrayList<String[]> records) {
        int[] sampleSizes = new int[3];
        for (int i = 0; i < 3; i++) {
            sampleSizes[i] = records.size();
        }
        return sampleSizes;
    }

}
