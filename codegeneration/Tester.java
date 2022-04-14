package codegeneration;
import scanner.*;
import java.io.*;
import environment.Environment;

/**
 * Tests the Code Generation functionality
 * 
 * @author Alex Hu
 * @version 4.13.22
 */
public class Tester 
{
    /**
     * Creates a new parser and parses a statement of a PASCAL program,
     * printing the output of the statement
     * @param args not used
     * @throws Exception if something goes wrong
     */
    public static void main(String[] args) throws Exception
    {
        String filePath = "C:\\Users\\hufen\\Desktop\\AlexSchool"
                        + "\\2021-22\\ATCS Compilers\\codegeneration\\parserTest.txt";           
        FileInputStream inStream = new FileInputStream(new File(filePath));
        Scanner sc = new Scanner(inStream);
        Parser parser = new Parser(sc);
        Emitter e = new Emitter("C:\\Users\\hufen\\Desktop\\AlexSchool"
                        + "\\2021-22\\ATCS Compilers\\codegeneration\\output.txt");
        
        parser.parseProgram().compile(e);
        e.close();
    }
}
