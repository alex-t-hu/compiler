package codegeneration;
import environment.Environment;

/**
 * Represents a while statement
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class While extends Statement
{
    Condition cond;
    Statement stmt;

    /**
     * Constructor for While
     * @param cond the condition used
     * @param stmt the statement executed
     */
    public While(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * Executes the while loop
     * 
     * @param env the environment used
     */
    public void exec(Environment env)
    {
        while (cond.eval(env) == 1)
        {
            stmt.exec(env);
        }
    }
}
