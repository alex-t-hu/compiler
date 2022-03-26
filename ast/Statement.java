package ast;
import environment.Environment;

/**
 * Represents an abstract Statement
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public abstract class Statement 
{
    /**
     * Requires that statements can be executed
     * @param env the environment in which the statement is executed
     */
    public abstract void exec(Environment env);
}
