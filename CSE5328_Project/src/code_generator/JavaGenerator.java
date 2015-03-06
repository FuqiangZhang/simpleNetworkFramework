package code_generator;
/* todo: We should move the private final strings to the generator interface instead of this since
 * both of the java and c++ generator.
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class JavaGenerator implements CodeGenerator {
	
	private final String FOLDER = "code_generated";
	private final String CLASS = "class";
	private final String PUBLIC = "public";
	private final String PROTECTED = "protected";
	private final String PRIVATE = "private";
	private final String SEMICOLON = ";";
	private final String COMMA = ",";
	private final String LPAREN = "(";
	private final String RPAREN = ")";
	private final String LBRACKET = "[";
	private final String RBRACKET = "]";
	private final String LBRACE = "{";
	private final String RBRACE = "}";
	private final String TAB = "    ";
	private final String NEWLINE = "\n";
        
        private final String EXTENDS = "extends";
        private final String IMPLEMENTS = "implements";
	
	private final String PARAM_SEPARATOR = COMMA;
	private final String LBLOCK_ENCLOSURE = LBRACE;
	private final String RBLOCK_ENCLOSURE = RBRACE;
	private final String LPARAM_ENCLOSURE = LPAREN;
	private final String RPARAM_ENCLOSURE = RPAREN;
	private final String LINDEX_ENCLOSURE = LBRACKET;
	private final String RINDEX_ENCLOSURE = RBRACKET;
	
	public boolean generate(Program program) {
		List<ProgramClass> classes;
		
		if(program == null) {
			System.err.println("There was no program given to be generated.");
			return false;
		}
		
		try {
			classes = program.getClasses();
			
			for(ProgramClass c : classes) {
                            //Change this from being hard coded to a generic directory...
                                File fileDir = new File("code" + File.separator + ".");
                                fileDir.mkdirs();
				File file = new File("code" + File.separator + c.getName() + ".java");
				if(!file.exists()) {
					file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				String classHeaderString = genClassHeader(c);
				System.out.println(classHeaderString);
				bw.write(classHeaderString);
				bw.write(NEWLINE);
				bw.write(NEWLINE);
				for(ClassAttribute attr : c.getAttributes()) {
					String attrString = genAttrString(attr);
					System.out.println(attrString);
					bw.write(attrString);
					bw.write(NEWLINE);
					bw.write(NEWLINE);
				}
				for(ProgramMethod method : c.getMethods()) {
					String methodString = genMethodString(method);
					System.out.println(methodString);
					bw.write(methodString);
					bw.write(NEWLINE);
					bw.write(NEWLINE);
				}
				System.out.println(RBLOCK_ENCLOSURE);
				bw.write(RBLOCK_ENCLOSURE);
				bw.write(NEWLINE);
				bw.close();
			}
			return true;
			
		}
		catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
			return false;
		}
	}
	
	private String genClassHeader(ProgramClass c) {
		String header = PUBLIC;
		if(c.getType().equals(ProgramClass.INTERFACE) || c.getType().equals(ProgramClass.ABSTRACT)) {
		    header += " " + c.getType().toLowerCase();
		}
		header += " " + c.getName();
                if(c.getSuperClass().size() > 0) {
                    header += " " + EXTENDS + " " + c.getSuperClass().get(0);
                }
                if(c.getInterface().size() > 0) {
                    header += " " + IMPLEMENTS;
		    header += " " + c.getInterface().get(0);
		    for(int i = 1; i < c.getInterface().size(); i++) {
                        header += ", " + c.getInterface().get(i);
		    }
                }
                header += LBLOCK_ENCLOSURE;
            return header;
	}
	
	private String genAttrString(ClassAttribute a) {
		return TAB + PRIVATE + " " + a.getType() + " " + a.getName() + SEMICOLON;
	}
	
	private String genMethodString(ProgramMethod m) {
		List<String> params = m.getParameters();
		String methodHeader = TAB + PUBLIC + " " + m.getReturnType() + " "
				+ m.getName() + LPARAM_ENCLOSURE;
	
		// done this way so there is not a comma at the end of the param list
		String first = params.get(0);
		methodHeader += first;
		params.remove(0);
		
		for(String param : params) {   
			methodHeader += PARAM_SEPARATOR + " " + param;
		}
		
		methodHeader += RPARAM_ENCLOSURE;
		methodHeader += " " + LBLOCK_ENCLOSURE;
		methodHeader += NEWLINE + TAB + RBLOCK_ENCLOSURE;
		
		return methodHeader;
	}
	

}
