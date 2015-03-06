/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code_generator;

/**
 *
 * @author Piox
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class CodeEditorGUI extends JPanel {

    private File fileName = new File("Untitled");
    private BorderLayout layout = new BorderLayout();
    private JPanel contentPane;
    private JScrollPane jScrollPane;
    private JMenuItem copy;
    private JMenuItem cut;
    private JMenuItem paste;
    private JMenuItem delete;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JEditorPane editPane;
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem saveAs;
    private JMenuItem selectAll;

    private CodeEditorController controller;
    
    
    public CodeEditorGUI()
    {
        initializeGUI();
    }
    
    private void initializeGUI()
    {
        jScrollPane = new JScrollPane();
        copy = new JMenuItem();
        cut = new JMenuItem();
        paste = new JMenuItem();
        delete = new JMenuItem();
        load = new JMenuItem();
        save = new JMenuItem();
        saveAs = new JMenuItem();
        selectAll = new JMenuItem();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        jMenuBar1 = new JMenuBar();
        editPane = new JEditorPane();
        contentPane = new JPanel(layout);
        
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setTitle(fileName.getName());
        setName("mainFrame");

        //jScrollPane.setHorizontalScrollBar(null);

        editPane.setBackground(new java.awt.Color(255,255,255));
        editPane.setMargin(new java.awt.Insets(1, 10, 1, 10));
        jScrollPane.setViewportView(editPane);

        //File Menu Drop Down
        jMenu1.setText("File");

        load.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        load.setText("Open");
        load.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                loadActionPerformed(e);
            }
        });
        jMenu1.add(load);

        save.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveActionPerformed(e);
            }
        });
        jMenu1.add(save);

        saveAs.setText("Save As ...");
        saveAs.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveAsActionPerformed(e);
            }
        });
        jMenu1.add(saveAs);

        jMenuBar1.add(jMenu1);
        //End File Menu Dropdown

        //Edit Menu Dropdown
        jMenu2.setText("Edit");

        cut.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cut.setText("Cut");
        cut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cutMenuActionPerformed(e);
            }
        });
        jMenu2.add(cut);

        copy.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copy.setText("Copy");
        copy.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                copyMenuActionPerformed(e);
            }
        });
        jMenu2.add(copy);

        paste.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        paste.setText("Paste");
        paste.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                pasteMenuActionPerformed(e);
            }
        });
        jMenu2.add(paste);

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // POSSIBLE DELETION
            }
        });
        jMenu2.add(delete);

        selectAll.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAll.setText("Select All");
        selectAll.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                selectAllMenuActionPerformed(e);
            }
        });
        jMenu2.add(selectAll);

        jMenuBar1.add(jMenu2);
        //End jMenu2 edit dropdown

        // Possible change in the future
        //contentPane.add(jScrollPane);

        this.setSize(640,480);
        this.setLayout(layout);
        this.add(jMenuBar1, BorderLayout.NORTH);
        this.add(jScrollPane, BorderLayout.CENTER);
    }

    private void copyMenuActionPerformed(ActionEvent e)
    {
        this.editPane.copy();
    }

    private void cutMenuActionPerformed(ActionEvent e)
    {
        this.editPane.cut();
    }

    private void pasteMenuActionPerformed(ActionEvent e)
    {
        this.editPane.paste();
    }

    private void selectAllMenuActionPerformed(ActionEvent e)
    {
        this.editPane.selectAll();
    }

    private void loadActionPerformed(ActionEvent e)
    {
        JFileChooser fileChooser = new JFileChooser(new File("."));
        int returnVal = fileChooser.showOpenDialog(this);     
        if(returnVal == JFileChooser.CANCEL_OPTION)
        {
            this.editPane.setText("File Browsing Cancelled");
        }
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            //TestingPurpose
            if(selectedFile == null || selectedFile.getName().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
            }
            
            /*
             * This is ad-hoc could never get it to pass the file correctly to the original
             * code editor controller without an error.
             */
            BufferedReader reader;
            StringBuilder stringBuilder = new StringBuilder();
            try
            {
            reader = new BufferedReader(new FileReader(selectedFile));
            while (reader.ready())
            {
                stringBuilder.append(reader.readLine() + "\n");
            }
            reader.close();
            }
            catch(IOException ioe)
            {
            this.editPane.setText("Can Not Open File");
            }
            this.editPane.setText(stringBuilder.toString());
            this.fileName = selectedFile;
            //End of controller like code
            /*
             * Unable to make a working version of this with controller.openFile(selectedFile);
             */
        }
    }
    
    private void saveActionPerformed(ActionEvent e)
    {
        BufferedWriter writer;
        try
        {
            writer = new BufferedWriter(new FileWriter(this.fileName));
            writer.write(this.editPane.getText());
            writer.close();
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Unable To Save Changes", "Unable To Save Changes", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveAsActionPerformed(ActionEvent e)
    {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);     
        if(returnVal == JFileChooser.CANCEL_OPTION)
        {
            JOptionPane.showMessageDialog(this, "File Not Selected", "File Not Selected", JOptionPane.ERROR_MESSAGE);
        }
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            BufferedWriter writer;
            try
            {
                writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                writer.write(this.editPane.getText());
                writer.close();
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(this, "Unable To Save Changes", "Unable To Save Changes", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
