package com.mmse.insurance.view;


import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mmse.insurance.control.Controller;

public class View {
	
	Controller controller;
	
	public View(Controller controller) {
		this.controller = controller;
		
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
        
        JLabel label = new JLabel("Company X management panel");
        frame.getContentPane().add(label);
        
        //Display the window
        frame.setVisible(true);
    }
}
