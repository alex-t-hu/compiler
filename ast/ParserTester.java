package ast;
import scanner.*;
import java.io.*;
import environment.Environment;

/**
 * Tests the Parser class based on the AST framework
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class ParserTester 
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
                        + "\\2021-22\\ATCS Compilers\\ast\\parserTest6.txt";           
        FileInputStream inStream = new FileInputStream(new File(filePath));
        Scanner sc = new Scanner(inStream);
        Parser parser = new Parser(sc);

        parser.parseStatement().exec(new Environment());
    }
}
