package codegeneration;
import environment.Environment;

/**
 * Represents a condition, which is a boolean expression
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class Condition extends Expression
{
    Expression exp1, exp2;
    String relop;

    /**
     * Constructor for Condition
     * @param exp1 the LHS expression
     * @param relop the operator used for comparing
     * @param exp2 the RHS expression
     */
    public Condition(Expression exp1, String relop, Expression exp2)
    {
        this.exp1 = exp1;
        this.relop = relop;
        this.exp2 = exp2;
    }

    /**
     * evaluates the conditional expression
     * @param env the environment used
     * @return the value of the expression, where 1 is true and 0 is false
     */
    public int eval(Environment env)
    {
        int val1 = exp1.eval(env);
        int val2 = exp2.eval(env);

        if (relop.equals("<>"))
        {
            if (val1 != val2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else if (relop.equals("<"))
        {
            if (val1 < val2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else if (relop.equals("<="))
        {
            if (val1 <= val2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else if (relop.equals(">"))
        {
            if (val1 > val2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else if (relop.equals(">="))
        {
            if (val1 >= val2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else if (relop.equals("="))
        {
            if (val1 == val2)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            String message = "invalid relational operator found: "
                             + relop;

            System.out.println(message);

            return 0;
        }
    }
}