package codegeneration;
import environment.Environment;

/**
 * Represents a Number
 * 
 * @author Alex Hu
 * @version 4.13.22
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor for Number
     * @param value the value of the number
     */
    public Number(int value)
    {
        this.value = value;
    }

    /**
     * evaluates the number
     * @param env the environment used
     * @return the value of the numb er
     */
    public int eval(Environment env)
    {
        return value;
    }

    /**
     * converts this number to assembly code
     * 
     * @param e the emitter used to write code to a file
     */
    public void compile(Emitter e)
    {
        e.emit("li $v0 " + value);
    }
}
