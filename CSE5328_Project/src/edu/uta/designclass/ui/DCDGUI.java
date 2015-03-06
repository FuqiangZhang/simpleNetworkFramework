package edu.uta.designclass.ui;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import edu.uta.designclass.component.DesignClassDiagram;
import edu.uta.designclass.controller.Actions;
import edu.uta.designclass.controller.DCDController;

public class DCDGUI {

	private JTable t = new JTable();
	private String msg = null;
	private DesignClassDiagram dcd;
	private JFrame mainFrame;
	private JFrame dataFrame;
	private Actions actions;
	private DCDTablePanel dataTable;
	private JPanel mainPanel;
	private FKCanvas canvas = new FKCanvas("DCD", 800, 600);
	JSplitPane splitPane;
	public DCDGUI() {
		Dimension d = new Dimension(500, 500);
		actions = new Actions();
		actions.setCanvas(canvas);
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
		menuItem.addActionListener(actions);
		menu.add(menuItem);
		   
		JMenuItem writexml = new JMenuItem("Write XML");
		writexml.addActionListener(actions);
		menu.add(writexml);
		
		
		
		mainFrame = new JFrame();
		mainFrame.setSize(d);
		mainFrame.setJMenuBar(menuBar);
//		mainPanel = createPanel();
//		mainFrame.add(mainPanel);

		JPanel diagram = canvas.getCanvs();
		JPanel tablepanel = this.createTable();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane( diagram),new JScrollPane( tablepanel));
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(350);// 由jb2011 从200改成现在值
		
		mainFrame.add(splitPane, BorderLayout.CENTER);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainFrame.add(createTable());
		mainFrame.setVisible(true);
	}

	public void refresh() {
		splitPane.updateUI();
	}

	private JPanel createTable() {
		dataTable = new DCDTablePanel();
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JScrollPane pane;

		JButton cancel = new JButton("Cancel");
		JButton submit = new JButton("Submit");
		cancel.addActionListener(actions);
		submit.addActionListener(actions);
		buttonPanel.add(submit);
		buttonPanel.add(cancel);

		pane = new JScrollPane(dataTable);
		pane.setPreferredSize(new Dimension(500, 500));
		panel.add(pane);
		// panel.add(buttonPanel);
		return panel;
		// dataFrame = new JFrame();
		// dataFrame.add(panel, BorderLayout.PAGE_START);
		// // dataFrame.add(buttonPanel, BorderLayout.AFTER_LINE_ENDS);
		// dataFrame.setBounds(0, 0, 500, 500);
		// dataFrame.setResizable(false);
		// dataFrame.setTitle("Design Class Diagram Data");
		// dataFrame.pack();
	}

	// public void ShowTable(){
	// dataFrame.setVisible(true);
	// }

	// public static void displayDCD() {
	// canvas.setVisible(true);
	// canvas.drawDCD();
	// }
	public JPanel createPanel() {

		JPanel panel = new JPanel();
		// JButton produce = new JButton("Produce DCD");
		// JButton edit = new JButton("Edit DCD");
		// JButton writeXMLButton = new JButton("Write XML");

		// actions = new Actions();

		// produce.addActionListener(actions);
		// edit.addActionListener(actions);
		// writeXMLButton.addActionListener(actions);
		//
		// panel.add(produce);
		// panel.add(edit);
		// panel.add(writeXMLButton);
		JPanel diagramPanel = new JPanel();
		diagramPanel.add(new JScrollPane(canvas.getCanvs()));
		diagramPanel.setPreferredSize(new Dimension(500, 400));
		panel.add(new JScrollPane(canvas.getCanvs()), BorderLayout.WEST);
		panel.add(createTable(), BorderLayout.EAST);

		return panel;
	}

	public static void main(String[] args) {
		try {
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
		}
		UIManager.put("RootPane.setupButtonVisible", false);
		DCDGUI gui = new DCDGUI();
		DCDController.setGUI(gui);
	}
}