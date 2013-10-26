package com.mmse.insurance.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class View {
	public View() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Company X Insurance Software");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center to the screen
        
        JLabel label = new JLabel("Company X");
        frame.getContentPane().add(label);
        
        //Display the window
        frame.setVisible(true);
    }
}
