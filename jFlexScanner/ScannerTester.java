package jFlexScanner;
import java.io.*;

/**
 * This is a tester for the Scanner class that has the Scanner read
 * from a text file of SMS messages and process it according to a 
 * set of rules defined in jFlexScannerTemplate.flex
 * @author Alex Hu
 * @version 2.11.22
 */
public class ScannerTester 
{
    /**
     * Scans the text file for certain types of tokens and prints 
     * information to the output stream
     * @param args command-line arguments are not used
     * @throws Exception if something goes wrong when tokenizing
     */
    public static void main(String[] args) throws Exception
    {
        String filePath = "C:\\Users\\hufen\\Desktop\\AlexSchool"
                        + "\\2021-22\\ATCS Compilers\\jflex-1.8.2\\SMSSpamCollection.txt";
                        
        FileReader inStream = new FileReader(new File(filePath));

        Scanner sc = new Scanner(inStream);
        
        while(!sc.yyatEOF())
        {
            System.out.println(sc.nextToken());
        }
    }
}
