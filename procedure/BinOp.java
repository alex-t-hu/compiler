package procedure;
import environment.Environment;

/**
 * Represents a binary operator expression
 * This is of the form: exp1 relop exp2, where
 * relop is either '<', '<=', '<>', '>', '>=', or '='
 * 
 * @author Alex Hu
 * @version 3.11.22
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * constructor for BinOp
     * @param op the string representing the operator
     * @param exp1 the first expression
     * @param exp2 the second expression
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * evaluates the binary expression
     * @param env the environment used when evaluating
     * @return the value of the expression
     */
    public int eval(Environment env)
    {
        int v1 = exp1.eval(env), v2 = exp2.eval(env);

        if(op.equals("+"))
        {
            return v1 + v2;
        }
        else if(op.equals("-"))
        {
            return v1 - v2;
        }
        else if(op.equals("*"))
        {
            return v1 * v2;
        }
        else if(op.equals("/"))
        {
            return v1 / v2;
        }

        return 0;
    }
}
