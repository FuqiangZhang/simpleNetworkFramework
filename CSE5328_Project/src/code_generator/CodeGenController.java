package code_generator;
/* todo: Change this perhaps to be accessed by the file dropdown menu. Nothing too extensive to change
 *      Language parameter.
 *      String perhaps?
 *
 */
import java.io.File;

public class CodeGenController {
	
	public boolean generate(String xmlFilePath)
    {
		if(!(new File(xmlFilePath).exists()))
        {
            System.err.println("Error file does not exist");
            return false;
        }
		XMLParser parser = new XMLParser();
		Program program = parser.parse(xmlFilePath);
		CodeGenerator generator = new JavaGenerator();
		return generator.generate(program);
	}

}
