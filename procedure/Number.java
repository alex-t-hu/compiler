package procedure;
import environment.Environment;

/**
 * Represents a Number
 * 
 * @author Alex Hu
 * @version 3.25.22
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
}
