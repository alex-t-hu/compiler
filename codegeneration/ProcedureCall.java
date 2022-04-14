package codegeneration;
import environment.Environment;
import java.util.*;

/**
 * Represents a procedure call
 * 
 * @author Alex Hu
 * @version 3.31.22
 */
public class ProcedureCall extends Expression
{
    String name;
    List<Expression> params;

    /**
     * Constructor
     * 
     * @param name the name of the procedure being called
     * @param params the parameters of the procedure call
     */
    public ProcedureCall(String name, List<Expression> params)
    {
        this.name = name;
        this.params = params;
    }

    /**
     * Executes the procedure in the given environment
     * 
     * @param env the environment in which the procedure is executed
     * @return the value of the procedure when evaluated
     */
    public int eval(Environment env)
    {
        ProcedureDeclaration procedure = env.getProcedure(name);
        Environment procedureEnv = new Environment(env);
        List<String> parameterList = procedure.parameterList;

        for (int i = 0; i < parameterList.size(); i++)
        {
            procedureEnv.declareVariable(parameterList.get(i), params.get(i).eval(env));
        }

        procedureEnv.declareVariable(name, 0);
        procedure.stmt.exec(procedureEnv);

        return procedureEnv.getVariable(name);
    }
}
