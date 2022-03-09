package parser;
import scanner.*;
import java.util.HashMap;

/**
 * Parser can parse an stream of tokens. 
 * It prints the output of the pascal program.
 * @author Alex Hu
 * @version 3.9.22
 */
public class Parser 
{
    Scanner scanner;
    String currentToken;
    HashMap<String, Integer> variables;

    /**
     * Parser constructor with a Scanner as input
     * @precondition scanner has not been called yet
     * @param scanner the scanner that the Parser uses
     * @throws Exception if scanning goes wrong
     */
    public Parser(Scanner scanner) throws Exception
    {
        this.scanner = scanner;
        currentToken = scanner.nextToken();
        variables = new HashMap<String, Integer>();
    }

    /**
     * "Eats" the currentToken and updates it with the nextToken
     * @param expectedToken what is expected for currentToken
     * @throws Exception if currentToken is not what is expected
     */
    private void eat(String expectedToken) throws Exception
    {
        if (!expectedToken.equals(currentToken))
        {
            throw new IllegalArgumentException("Expected: "
                                                + expectedToken
                                                + ", Got: "
                                                + currentToken);
        }

        currentToken = scanner.nextToken();
    }

    /**
    * Parses an integer
    * @precondition current token is an integer
    * @postcondition number token has been eaten
    * @return the value of the parsed integer
    * @throws Exception if the token is not a number
    */
    private int parseNumber() throws Exception
    {
        int num = Integer.parseInt(currentToken);

        eat(currentToken);

        return num;
    }

    /**
     * Parses a pascal statement
     * Statements can be WRITELN's, BEGIN and END blocks,
     * or assignments
     * @precondition currentToken is first token of the statement
     * @postcondition currentToken is first token after statement
     * @throws Exception if syntax is incorrect
     */
    public void parseStatement() throws Exception
    {
        if (currentToken.equals("WRITELN"))
        {
            eat(currentToken);
            eat("(");
        
            System.out.println(parseExpression());

            eat(")");
            eat(";");
        }
        else if (currentToken.equals("BEGIN"))
        {
            eat("BEGIN");

            while(!currentToken.equals("END"))
            {
                parseStatement();
            }

            eat("END");
            eat(";");
        }
        else
        {
            String id = currentToken;

            eat(currentToken);
            eat(":=");

            variables.put(id, parseExpression());

            eat(";");
        }
    }

    /**
     * parses a factor and returns its value
     * Factors consist of numbers and variables with 
     * minus signs and parentheses
     * @precondition currentToken is the first token of the factor
     * @postcondition currentToken is just after the last token of factor
     * @return the value of the factor
     * @throws Exception if syntax is incorrect
     */
    public int parseFactor() throws Exception
    {
        if (currentToken.equals("("))
        {
            eat(currentToken);

            int num = parseExpression();

            eat(")");

            return num;
        }
        else if (currentToken.equals("-"))
        {
            eat(currentToken);
            
            return -parseFactor();
        }
        else if (Character.isDigit(currentToken.charAt(0)))
        {
            return parseNumber();
        }
        else
        {
            if (variables.containsKey(currentToken))
            {
                int val = variables.get(currentToken);

                eat(currentToken);

                return val;
            }
            else
            {
                String message = currentToken + " is not a valid identifier";

                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * parses a term 
     * A term consists of factor(s) multiplied and divided by each other
     * @precondition currentToken is first token of the term
     * @postcondition currentToken is just after last token of the term
     * @return the value of the term
     * @throws Exception if the syntax is incorrect
     */
    public int parseTerm() throws Exception
    {
        int num = parseFactor();

        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if (currentToken.equals("*"))
            {
                eat(currentToken);

                num *= parseFactor();
            }
            else
            {
                eat(currentToken);

                num /= parseFactor();
            }
        }

        return num;
    }

    /**
     * parses an expression
     * An expression is a sum or difference of term(s)
     * @precondition currentToken is the first token of the expression
     * @postcondition currentToken is just after the last token
     * @return the value of the expression
     * @throws Exception if syntax is incorrect or variable is not defined
     */
    public int parseExpression() throws Exception
    {
        int num = parseTerm();

        while (currentToken.equals("+") || currentToken.equals("-"))
        {
            if (currentToken.equals("+"))
            {
                eat(currentToken);

                num += parseTerm();
            }
            else
            {
                eat(currentToken);

                num -= parseTerm();
            }
        }

        return num;
    }
}
