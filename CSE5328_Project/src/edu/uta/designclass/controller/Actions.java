package edu.uta.designclass.controller;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.uta.designclass.ui.FKCanvas;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
public class Actions implements ActionListener {
	private FKCanvas canvas;
	public void setCanvas(FKCanvas canvas){
		this.canvas = canvas;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		String cmString = e.getActionCommand();
		if (cmString == "Edit DCD") {
			//this.createTable();
			DCDController.displayTable();
		} else if (cmString == "Produce DCD") {
			// JOptionPane.showMessageDialog(null, "Produced DCD");
//			DCDController.displayDCD();
		} else if (cmString == "Cancel") {
//			dataFrame.dispose();
		} else if (cmString == "Submit") {
//			msg = new String(DCDController.writeDCDData(dataTable));
//			JOptionPane.showMessageDialog(null, msg);
//			dataFrame.dispose();
		} else if (cmString.equalsIgnoreCase("Write XML")){
			boolean flag = DCDController.writeXML();
			if(flag){
				JOptionPane.showMessageDialog(null, "success");
			}else{
				JOptionPane.showMessageDialog(null, "fail");
			}
		}
		if (cmString == "Export") {
			System.out.println(cmString);
			canvas.drawPicture();
		}
	}
}
