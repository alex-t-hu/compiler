package scanner;
import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Alex Hu
 * @version 1.28.22
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;

    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     * @throws Exception if something goes wrong with I/O
     */
    public Scanner(InputStream inStream) throws Exception
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;

        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     * @throws Exception if something goes wrong with I/O
     */
    public Scanner(String inString) throws Exception
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;

        getNextChar();
    }

    /**
     * checks if the character is a numerical digit
     * @param c the character to be tested
     * @return whether the character is a digit
     */
    public static boolean isDigit(char c)
    {
        return '0' <= c && c <= '9';
    }

    /**
     * checks whether the character is a letter
     * @param c the character to be tested
     * @return whether character is a letter
     */
    public static boolean isLetter(char c)
    {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    /**
     * checks whether the character is a "white space"
     * (tab, newline, space, etc...)
     * @param c the character to be tested
     * @return whether character is a "white space"
     */
    public static boolean isWhiteSpace(char c)
    {
        return (c == ' ') || (c == '\t') || (c == '\r') || (c == '\n');
    }

    /**
     * reads the next character from the input stream
     * until it reaches the end
     * @throws IOException if an IO error occurs
     */
    private void getNextChar() throws IOException
    {
        try
        {
            int temp = in.read();

            if (temp == -1)
            {
                eof = true;
            }
            else
            {
                currentChar = (char)temp;
            }
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
            in.close();
            System.exit(1);
        }
    }

    /**
     * reads the next character from the input streamm
     * and checks if the current character is what is
     * expected
     * @param expected the expected current character
     * @throws Exception either IO error or expected differs from current
     */
    private void eat(char expected) throws Exception
    {
        if (expected != currentChar)
        {
            throw new ScanErrorException("Expected: "
                                         + expected
                                         + ", Got: "
                                         + currentChar);
        }

        getNextChar();
    }

    /**
     * @return whether there is another token to read from the file
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Scans a number from the input stream
     * A number is a sequence of digits
     * @return the next number in the input stream
     * @throws Exception if the number is invalid
     */
    private String scanNumber() throws Exception
    {
        String number = "";

        while (hasNext() && isDigit(currentChar))
        {
            number = number + String.valueOf(currentChar);

            eat(currentChar);
        }

        if (isLetter(currentChar))
        {
            throw new ScanErrorException("invalid number ending in letters");
        }

        return number;
    }

    /**
     * returns the next identifier from the input stream
     * An identifier is a letter followed by letters or digits
     * @return the next identifier
     * @throws Exception some IO error
     */
    private String scanIdentifier() throws Exception
    {
        String identifier = String.valueOf(currentChar);

        eat(currentChar);

        while(hasNext() && 
            (isDigit(currentChar) || isLetter(currentChar)))
        {
            identifier = identifier + String.valueOf(currentChar);

            eat(currentChar);
        }

        return identifier;
    }

    /**
     * returns the next operand from the input stream
     * operators are:
     * +, -, /, *, %, ^
     * (, )
     * $, @
     * ;
     * <, >, =, 
     * @return the next operand from the input stream
     * @throws Exception if the character is not recognized
     */
    private String scanOperand() throws Exception
    {
        if (currentChar == '(' || currentChar == ')'
                || currentChar == '=' || currentChar == '+'
                || currentChar == '-' || currentChar == '/'
                || currentChar == '*' || currentChar == '%'
                || currentChar == '$' || currentChar == '@'
                || currentChar == '^' || currentChar == ';'
                || currentChar == ':' || currentChar == '<'
                || currentChar == '>')
        {
            char temp = currentChar;

            eat(currentChar);

            return String.valueOf(temp); 
        }

        throw new ScanErrorException("unrecognized character");
    }

    /**
     * returns the next token from the input stream
     * A token is either a number, identifier, or operator
     * @return the next token
     * @throws Exception if the next token is not valid or some IO error
     */
    public String nextToken() throws Exception
    {
        String token;

        while (hasNext() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }

        if (!hasNext() || currentChar == '.')
        {
            eof = true;

            token = "END";

            in.close();
        }
        else if (isDigit(currentChar))
        {
            token = scanNumber();
        }
        else if (isLetter(currentChar))
        {
            token = scanIdentifier();
        }
        else
        {
            token = scanOperand();
        }

        return token;
    }    
}
