package scanner;
import java.io.*;

/**
 * This is a tester for the Scanner class that has the Scanner read
 * from a text file and print out its token
 * @author Alex Hu
 * @version 1.28.22
 */
public class ScannerTester 
{
    /**
     * Tokenizes the text file and prints it to the output stream
     * @param args command-line arguments
     * @throws Exception if something goes wrong when tokenizing
     */
    public static void main(String[] args) throws Exception
    {
        String filePath = "C:\\Users\\hufen\\Desktop\\AlexSchool"
                        + "\\2021-22\\ATCS Compilers\\scannerTest copy.txt";
        
        FileInputStream inStream = new FileInputStream(new File(filePath));

        Scanner sc = new Scanner(inStream);

        while(sc.hasNext())
        {
            System.out.println(sc.nextToken());
        }
    }
}
