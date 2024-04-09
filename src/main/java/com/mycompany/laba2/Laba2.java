/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laba2;

import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public class Laba2 {
    
    public static void main(String[] args){
        //CreateStats.createStats();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI mainFrame = new GUI();
                mainFrame.setVisible(true);
            }
        });
    }
    
}
