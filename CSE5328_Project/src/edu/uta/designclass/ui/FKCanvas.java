package edu.uta.designclass.ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import edu.uta.designclass.component.DesignClassDiagram;
import edu.uta.designclass.controller.DCDController;
import edu.uta.designclass.factory.DCDFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class FKCanvas implements ActionListener {

	private JFrame frame;
	private CanvasPane canvas;

	// private Image canvasImage;
	public CanvasPane getCanvs(){
		return canvas;
	}

	public FKCanvas(String title, int width, int height) {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Options");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		// a group of JMenuItems
		menuItem = new JMenuItem("Export", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Export to JPEG");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		canvas = new CanvasPane();
		canvas.setPreferredSize(new Dimension(2014, 400));

//		frame = new JFrame("Design Class Diagram");
//		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(width, height);
//		frame.setJMenuBar(menuBar);
//		frame.setLayout(new FlowLayout());
//
//		frame.setContentPane(canvas);
//		frame.setTitle(title);
//		frame.pack();

	}
	
	public void drawPicture(){
		BufferedImage image = new BufferedImage(canvas.getWidth(),
				canvas.getHeight(), BufferedImage.TYPE_INT_RGB);

		Graphics2D g2 = (Graphics2D) image.getGraphics();

		canvas.paint(g2);
		try {
			ImageIO.write(image, "png", new File("dcd" + File.separator
					+ "DCD.png"));
		} catch (Exception err) {
			System.out.println("Export failed.");
		}
	}
	
	public void actionPerformed(ActionEvent e) {

		JMenuItem clicked = (JMenuItem) e.getSource();

		if (clicked.getText() == "Export") {
			BufferedImage image = new BufferedImage(canvas.getWidth(),
					canvas.getHeight(), BufferedImage.TYPE_INT_RGB);

			Graphics2D g2 = (Graphics2D) image.getGraphics();

			canvas.paint(g2);
			try {
				ImageIO.write(image, "png", new File("dcd" + File.separator
						+ "DCD.png"));
			} catch (Exception err) {
				System.out.println("Export failed.");
			}
		}

	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public boolean isVisible() {
		return frame.isVisible();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Dimension getSize() {
		return canvas.getSize();
	}

	public void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {
		}
	}

	private class CanvasPane extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			DCDController.getDCD().draw((Graphics2D) g, 0, 0);
		}
	}

	public void drawDCD() {
		canvas.updateUI();
	}
}
