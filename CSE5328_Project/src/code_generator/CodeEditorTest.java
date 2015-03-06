/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code_generator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Piox
 */
public class CodeEditorTest {

    public static void main(String[] args)
    {
        JFrame application = new JFrame("Code Generation GUI Tester");
        CodeEditorGUI editPanel = new CodeEditorGUI();
        GUI optionPanel = new GUI();
        application.add(editPanel, BorderLayout.CENTER);
        application.add(optionPanel, BorderLayout.WEST);
        application.add(new JLabel("Testing the panel layout"), BorderLayout.SOUTH);
        application.add(new JLabel("Testing the panel layout"), BorderLayout.NORTH);
        
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(640,480);
        application.setVisible(true);
    }
}
