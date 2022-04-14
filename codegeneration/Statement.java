package codegeneration;
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

    /**
     * Transforms this statement into assembly code 
     * @param e the emitter used to write code to a file
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}
