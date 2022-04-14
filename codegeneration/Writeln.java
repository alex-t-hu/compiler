package codegeneration;
import environment.Environment;

/**
 * Represents a Writeln statement in Pascal, which is a print statement
 * 
 * @author Alex Hu
 * @version 4.13.22
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

    /**
     * converts this print statement to assembly code
     * 
     * @param e the emitter used to write code to a file
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");
    }
}
