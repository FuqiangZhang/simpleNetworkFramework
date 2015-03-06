package code_generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JPanel {
	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private String[] langList;
	private JComboBox langComboBox;
	private JButton generateButton;
	private JPanel actionPanel;
        
        // PATH FOR XML FILE TO BE READ OF DCD
        private final String XML_FILE = "dcd.xml";
    private final String XML_PATH = File.separator + "code" + File.separator;
	
	public GUI() {
		JPanel contentPane = new JPanel(new BorderLayout());
		
		JTextArea textArea = new JTextArea(1, 30);
        textArea.setText("File Path");
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		String[] langList = {"Java"};
		JComboBox langComboBox = new JComboBox(langList);
		
		JButton generateButton = new JButton();
		generateButton.setText("Generate Code");
		generateButton.addActionListener(new generateListener(textArea));
		
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.LINE_AXIS));
		actionPanel.add(langComboBox);
		actionPanel.add(Box.createHorizontalStrut(15));
		actionPanel.add(generateButton);
		
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(actionPanel, BorderLayout.SOUTH);
                this.add(contentPane);
	}
	
	private class generateListener implements ActionListener {
		private JTextArea textArea;
		public void actionPerformed(ActionEvent e) {
                        CodeGenController controller = new CodeGenController();
                        File file = new File("code" + File.separator);
                        try
                        {
                            file.mkdirs();
                            System.out.println(file.getAbsoluteFile());
                            file = new File("code" + File.separator + XML_FILE);
                            file.createNewFile();
                            textArea.setText(file.getCanonicalPath());
                        }
                        catch(IOException e2)
                        {
                            e2.printStackTrace();
                        }
                        //FILE PATH FOR XML
                        boolean success = controller.generate(file.getAbsolutePath());
                        if(success)
                        {
                            JOptionPane.showMessageDialog(null, "Your code was successfully generated.");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error: your code could not be generated");
                        }
		}
		
		public generateListener(JTextArea textArea) {
			this.textArea = textArea;
		}
	}

}
