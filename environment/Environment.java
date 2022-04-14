package environment;
import java.util.HashMap;
import procedure.ProcedureDeclaration;
// import codegeneration.ProcedureDeclaration;

/**
 * Represents an environment, a "storage area" for variables and procedures
 * 
 * @author Alex Hu
 * @version 3.31.22
 */
public class Environment 
{
    Environment parent;
    HashMap<String, Integer> variables;
    HashMap<String, ProcedureDeclaration> procedures;

    /**
     * Constructor for the root Environment
     */
    public Environment()
    {
        parent = null;
        variables = new HashMap<String, Integer>();
        procedures = new HashMap<String, ProcedureDeclaration>();
    }

    /**
     * Constructor for a child Environment
     * 
     * @param parent the parent environment of the current environment
     */
    public Environment(Environment parent)
    {
        this.parent = parent;
        variables = new HashMap<String, Integer>();
        procedures = new HashMap<String, ProcedureDeclaration>();
    }

    /**
     * associates the given variable name with the given value
     * if the variable does not exist in the current environment, it sets the
     * variable in the parent environment to the new value
     * 
     * @param variable the variable that is being created/assigned a value
     * @param value the value that's being assigned
     */
    public void setVariable(String variable, int value)
    {
        if (variables.containsKey(variable))
        {
            variables.put(variable, value);
        }
        else
        {
            boolean variableExists = false;
            Environment env = parent;

            while (env != null)
            {
                if (env.variables.containsKey(variable))
                {
                    env.variables.put(variable, value);

                    variableExists = true;

                    break;
                }

                env = env.parent;
            }

            if (!variableExists)
            {
                variables.put(variable, value);
            }
        }
    }

    /**
     * Declares a variable in the current environment
     * 
     * @precondition only used for parameter intializations
     * @param variable the name of the variable 
     * @param value the value of the variable
     */
    public void declareVariable(String variable, int value)
    {
        variables.put(variable, value);
    }

    /**
     * returns the value of the variable
     * 
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
            Environment env = parent;

            while (env != null)
            {
                if (env.variables.containsKey(variable))
                {
                    return env.variables.get(variable);
                }

                env = env.parent;
            }

            String message = variable + " is not a valid variable identifier";

            System.out.println(message);

            return 0;
        }
    }

    /**
     * Retrieves the procedure with a certain name
     * 
     * @param name the name of the procedure
     * @return the corresponding procedure declaration
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        if (procedures.containsKey(name))
        {
            return procedures.get(name);
        }
        else
        {
            String message = name + " is not a valid procedure identifier";

            System.out.println(message);

            return null;
        }
    }

    /**
     * Assigns the name to the procedure's declaration
     * 
     * @param name the name of the procedure
     * @param procedure the declaration of the procedure  
     */
    public void setProcedure(String name, ProcedureDeclaration procedure)
    {
        procedures.put(name, procedure);
    }
}
