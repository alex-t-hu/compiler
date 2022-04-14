package procedure;
import environment.Environment;

/**
 * Represents an assignment in the abstract syntax tree
 * Assignments are of the form: id := expression
 * @author Alex Hu
 * @version 3.11.22
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;
    
    /**
     * constructor for assignment
     * @param var the name of the variable
     * @param exp the expression to be assigned
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }

    /**
     * executes a variable assignment, updating the environment 
     * @param env the current environment
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
}
