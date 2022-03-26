package environment;
import java.util.HashMap;

/**
 * Represents an environment, a "storage area" for variables
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class Environment 
{
    HashMap<String, Integer> variables;

    /**
     * Constructor for Environment
     */
    public Environment()
    {
        variables = new HashMap<String, Integer>();
    }

    /**
     * associates the given variable name with the given value
     * @param variable the variable that is being created/assigned a value
     * @param value the value that's being assigned
     */
    public void setVariable(String variable, int value)
    {
        variables.put(variable, value);
    }

    /**
     * returns the value of the variable
     * @param variable the variable whose value is being accessed
     * @return the value corresponding to the variable
     */
    public int getVariable(String variable)
    {
        if (variables.containsKey(variable))
        {
            return variables.get(variable);
        }
        else
        {
            String message = variable + " is not a valid identifier";

            System.out.println(message);

            return 0;
        }
    }
}
