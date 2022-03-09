package scanner;
import java.io.*;

/**
 * Scanner can parse an input stream into tokens. It can also remove single-line comments and
 * parse some multi-character tokens like "<=" and ":=."
 * @author Alex Hu
 * @version 3.7.22
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
     * @precondition inStream is properly initialized
     * @postcondition a Scanner object associated with inStream is created
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
     * @precondition inStream is properly initialized
     * @postcondition a Scanner object associated with inStream is created
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
     * @precondition the input stream is properly initialized and the currentChar is not EOF
     * @postcondition the reader advances by 1 character
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
     * @precondition the input stream is properly initialized and currentChar is not the EOF
     * @postcondition the input stream is advanced by 1
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
     * @precondition currentChar is the start of the number
     * @postcondition currentChar just after the end of the number
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
     * @precondition currentChar is the start of the identifier
     * @postcondition currentChar is just after the end of the identifier
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
     * returns the next operand from the input stream, or
     * token if the currentChar is at a single-line comment
     * operators are:
     * +, -, /, *, %, ^
     * (, )
     * $, @
     * ;
     * <, >, =, 
     * @precondition currentChar should be an operator character
     * @postcondition currentChar is just after the end of the token
     * @return the next operand from the input stream
     * @throws Exception if the character is not recognized
     */
    private String scanOperand() throws Exception
    {
        if (currentChar == '(' || currentChar == ')'
                || currentChar == '=' || currentChar == '+'
                || currentChar == '-' || currentChar == '/'
                || currentChar == '*' || currentChar == '%'
                || currentChar == ';'
                || currentChar == ':' || currentChar == '<'
                || currentChar == '>')
        {
            char temp = currentChar;

            eat(currentChar);
            
            if (temp == '/' && currentChar == '/')
            {
                while (hasNext() && currentChar != '\n')
                {
                    eat(currentChar);
                }

                return nextToken();
            }
            else if (temp == ':' && currentChar == '=')
            {
                eat(currentChar);

                return ":=";
            }
            else if (temp == '=' && currentChar == '=')
            {
                eat(currentChar);

                return "==";
            }
            else if (temp == '<' && currentChar == '=')
            {
                eat(currentChar);

                return "<=";
            }
            else if (temp == '>' && currentChar == '=')
            {
                eat(currentChar);

                return ">=";
            }
            else if (temp == '<' && currentChar == '>')
            {
                eat(currentChar);

                return "<>";
            }

            return String.valueOf(temp); 
        }

        throw new ScanErrorException("unrecognized character: " + currentChar);
    }

    /**
     * returns the next token from the input stream
     * A token is either a number, identifier, or operator
     * @precondition the currentChar is beyond the previous token 
     * @postcondition currentChar is just after the end of the token 
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
