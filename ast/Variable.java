package ast;
import environment.Environment;

/**
 * Represents a variable
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructor for Variable
     * @param name the name of the variable
     */
    public Variable(String name)
    {
        this.name = name;
    }
    
    /**
     * Returns the value of the variable
     * @param env the environment used
     * @return the value of the variable if it exists
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
