/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laba2;

import javax.swing.SwingUtilities;

/**
 *
 * @author kateshcherbinina
 */
public class Laba2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI mainFrame = new GUI();
                mainFrame.setVisible(true);
            }
        });
    }

}
