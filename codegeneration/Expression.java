package codegeneration;
import environment.Environment;

/**
 * Represents an abstract Expression.
 * Expressions can be evaluated to produce numbers
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public abstract class Expression 
{
    /**
     * evaluates the expression
     * @param env the environment used
     * @return the expression's value
     */
    public abstract int eval(Environment env);

    /**
     * Transforms this expression into assembly code 
     * @param e the emitter used to write code to a file
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}
