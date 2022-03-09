package parser;
import scanner.*;
import java.io.*;

/**
 * Tests the Parser class
 * @author Alex Hu
 * @version 3.7.22
 */
public class ParserTester 
{
    /**
     * Creates a new parser and parses a text file
     * @param args not used
     * @throws Exception if something goes wrong
     */
    public static void main(String[] args) throws Exception
    {
        String filePath = "C:\\Users\\hufen\\Desktop\\AlexSchool"
                        + "\\2021-22\\ATCS Compilers\\parser\\testFile3.txt";           
        FileInputStream inStream = new FileInputStream(new File(filePath));
        Scanner sc = new Scanner(inStream);
        Parser parser = new Parser(sc);

        parser.parseStatement();
    }
}
