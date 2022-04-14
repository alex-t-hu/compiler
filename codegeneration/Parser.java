package codegeneration;
import scanner.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser can parse an stream of tokens, creating the parse tree.
 * The parse tree is represented with a hierarchy of objects.
 * The root node is a Program object, which represents the
 * entire Pascal program.
 * 
 * @author Alex Hu
 * @version 4.11.22
 */
public class Parser 
{
    Scanner scanner;
    String currentToken;

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
    private Number parseNumber() throws Exception
    {
        int num = Integer.parseInt(currentToken);

        eat(currentToken);

        return new Number(num);
    }

    /**
     * Parses a pascal program, returning the root node of the parse tree
     * 
     * @throws Exception if there is a syntax error
     * @return the object representing the program 
     */
    public Program parseProgram() throws Exception
    {
        List<ProcedureDeclaration> procedures = new ArrayList<ProcedureDeclaration>();

        while (currentToken.equals("PROCEDURE"))
        {
            eat(currentToken);

            String name = currentToken;
            List<String> parameterList = new ArrayList<String>();

            eat(currentToken);
            eat("(");

            while (!currentToken.equals(")"))
            {
                parameterList.add(currentToken);

                eat(currentToken);

                if (currentToken.equals(","))
                {
                    eat(currentToken);
                }
            }

            eat(")");
            eat(";");

            procedures.add(new ProcedureDeclaration(name, parseStatement(), parameterList));
        }

        return new Program(procedures, parseStatement());
    }

    /**
     * Parses a pascal statement
     * Statements can be WRITELN's, BEGIN and END blocks,
     * or assignments
     * @precondition currentToken is first token of the statement
     * @postcondition currentToken is first token after statement
     * @throws Exception if syntax is incorrect
     * @return the Statement just parsed
     */
    public Statement parseStatement() throws Exception
    {
        if (currentToken.equals("WRITELN"))
        {
            eat(currentToken);
            eat("(");
            
            Expression exp = parseExpression();

            eat(")");
            eat(";");

            return new Writeln(exp);
        }
        else if (currentToken.equals("BEGIN"))
        {
            List<Statement> stmts = new ArrayList<Statement>();

            eat("BEGIN");

            while(!currentToken.equals("END"))
            {
                stmts.add(parseStatement());
            }

            eat("END");
            eat(";");

            return new Block(stmts);
        }
        else if (currentToken.equals("IF"))
        {
            eat(currentToken);

            Expression exp1 = parseExpression();
            String relop = currentToken;

            eat(currentToken);

            Expression exp2 = parseExpression();

            eat("THEN");

            return new If(new Condition(exp1, relop, exp2), parseStatement());
        }
        else if (currentToken.equals("WHILE"))
        {
            eat(currentToken);

            Expression exp1 = parseExpression();
            String relop = currentToken;

            eat(currentToken);

            Expression exp2 = parseExpression();

            eat("DO");

            return new While(new Condition(exp1, relop, exp2), parseStatement());
        }
        else
        {
            String id = currentToken;

            eat(currentToken);
            eat(":=");

            Expression exp = parseExpression();

            eat(";");

            return new Assignment(id, exp);
        }
    }

    /**
     * parses a factor and returns its value
     * Factors consist of numbers, variables, and 
     * procedure calls with 
     * minus signs and parentheses
     * 
     * @precondition currentToken is the first token of the factor
     * @postcondition currentToken is just after the last token of factor
     * @return the value of the factor
     * @throws Exception if syntax is incorrect
     */
    public Expression parseFactor() throws Exception
    {
        if (currentToken.equals("("))
        {
            eat(currentToken);

            Expression exp = parseExpression();

            eat(")");

            return exp;
        }
        else if (currentToken.equals("-"))
        {
            eat(currentToken);
            
            return new BinOp("-", new Number(0), parseFactor());
        }
        else if (Character.isDigit(currentToken.charAt(0)))
        {
            return parseNumber();
        }
        else
        {
            String name = currentToken;

            eat(currentToken);

            if (currentToken.equals("("))
            {
                List<Expression> params = new ArrayList<Expression>();

                eat(currentToken);

                while (!currentToken.equals(")"))
                {
                    params.add(parseExpression());

                    if (currentToken.equals(","))
                    {
                        eat(",");
                    }
                }

                eat(")");

                return new ProcedureCall(name, params);
            }
            else
            {
                return new Variable(name);
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
    public Expression parseTerm() throws Exception
    {
        Expression exp = parseFactor();

        while (currentToken.equals("*") || currentToken.equals("/"))
        {
            if (currentToken.equals("*"))
            {
                eat(currentToken);

                exp = new BinOp("*", exp, parseFactor());
            }
            else
            {
                eat(currentToken);

                exp = new BinOp("/", exp, parseFactor());
            }
        }

        return exp;
    }

    /**
     * parses an expression
     * An expression is a sum or difference of term(s)
     * @precondition currentToken is the first token of the expression
     * @postcondition currentToken is just after the last token
     * @return the value of the expression
     * @throws Exception if syntax is incorrect or variable is not defined
     */
    public Expression parseExpression() throws Exception
    {
        Expression exp = parseTerm();

        while (currentToken.equals("+") || currentToken.equals("-"))
        {
            if (currentToken.equals("+"))
            {
                eat(currentToken);

                exp = new BinOp("+", exp, parseTerm());
            }
            else
            {
                eat(currentToken);

                exp = new BinOp("-", exp, parseTerm());
            }
        }

        return exp;
    }
}
