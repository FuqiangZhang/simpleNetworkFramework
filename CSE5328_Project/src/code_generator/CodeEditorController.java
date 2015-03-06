package code_generator;
/* todo: Write this
 *      open file method
 *      save file / close file
 *      basic functionality
 *
 */
import javax.swing.JFileChooser;
import java.io.*;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class CodeEditorController {

    public void saveFile()
    {
        //Implementation
    }

    public void saveAsFile()
    {
        //Implementation
    }

    public String openFile(File selectedFile)
    {
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
            return("Can Not Open File");

        }
        return(stringBuilder.toString());
    }
}
