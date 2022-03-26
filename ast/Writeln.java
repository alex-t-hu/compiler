package ast;
import environment.Environment;

/**
 * Represents a Writeln statement in Pascal, which is a print statement
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for Writeln
     * @param exp the expression to be printed
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * Executes the print statement
     * @param env the environment to be used
     * @postcondition the value of the expression has been printed to the standard console
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
