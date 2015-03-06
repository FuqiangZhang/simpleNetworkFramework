package code_generator;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CodeGenTest {
	
	private JTextArea textArea;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filepath = JOptionPane.showInputDialog("Enter the filename of the XML file to generate code for",
					"code" + File.separator + "DCD_Test.xml");
		CodeGenController controller = new CodeGenController();
		boolean success = controller.generate(filepath);
		if(success) {
			JOptionPane.showMessageDialog(null, "Your code was successfully generated.");
		}
		else {
			JOptionPane.showMessageDialog(null, "Error: your code could not be generated");
		}
		
	}
        //Eric Simply testing an external push
}
