package codegeneration;
import environment.Environment;

/**
 * Represents an if statement
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class If extends Statement
{
    Condition cond;
    Statement stmt;

    /**
     * Constructor for If
     * @param cond the condition statement in the If statement
     * @param stmt the statement in the body of the if statement
     */
    public If(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * executes the If statement
     * @param env the environment used
     */
    public void exec(Environment env)
    {
        if (cond.eval(env) == 1)
        {
            stmt.exec(env);
        }
    }
}
