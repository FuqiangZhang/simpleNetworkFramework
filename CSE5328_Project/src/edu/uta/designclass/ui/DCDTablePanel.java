package edu.uta.designclass.ui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.uta.designclass.component.*;
import edu.uta.designclass.controller.DCDController;

public class DCDTablePanel extends JTable {
	private DefaultTableCellRenderer tcr;
	private static String[] columnNames = { "Class Name", "Attributes",
			"Methods" };
	private static Object[][] data = new Object[0][columnNames.length];

	private DesignClassDiagram dcd;

	public DCDTablePanel() {
		this.setCellSelectionEnabled(true);
		this.setRowSelectionAllowed(true);
		this.setAutoscrolls(true);
		tcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component cell = super.getTableCellRendererComponent(table,
						value, isSelected, hasFocus, row, column);
				return cell;
			}
		};
		setTableItem();
		dcd = DCDController.getDCD();
		updateTable();
		setUpdateData();
	}


	private void setUpdateData() {
		this.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					if (e.getColumn() == 0) {
						System.out.println(e.getLastRow());
						setClassName(e.getLastRow());
					}
				}
				DCDController.getDcdgui().refresh();
			}
		});
	}

	private String oldName;

	// set the class name to the cell
	public void setClassName(int i) {
		String name = this.getValueAt(i, 0).toString();
		if (oldName != name) {
			oldName = name;
			classArrayList.get(i).setName(name);
		}
	}
	
	public void setAttributeName(int i){
		
	}
	
	private void updateClass() {
		classArrayList = DCDController.getDCD().getClasses();
		for (DCDClass dc : classArrayList) {
			String classname = dc.getName();
			int i = createRow();
			this.getModel().setValueAt(classname, i, 0);
//			updateAttribute(dc);
		}
	}
	
	private void updateAttribute(DCDClass dc){
		ArrayList<DCDComponent> attributes = dc.getAttributesArray();
		for(DCDComponent da: attributes){
			int i = createRow();
			this.getModel().setValueAt(((DCDAttribute) da).getName(), i, 1);
		}
	}
	
	public int createRow() {
		String[] row = new String[columnNames.length];
		((DefaultTableModel) this.getModel()).addRow(row);
		return ((DefaultTableModel) this.getModel()).getRowCount() - 1;
	}

	ArrayList<DCDClass> classArrayList;

	private void updateTable() {
		updateClass();
	}

	private void setTableItem() {

		this.setModel(new DefaultTableModel(data, columnNames) {
			Class[] columnTypes = new Class[] { String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int rowindex, int colindex) {
				return true;
			}
		});

		this.getColumn("Class Name").setCellRenderer(tcr);
		this.getColumn("Attributes").setCellRenderer(tcr);
		this.getColumn("Methods").setCellRenderer(tcr);

	}

}
