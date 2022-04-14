package codegeneration;
import java.util.*;

import environment.Environment;

/**
 * Represents a procedure declaration
 * 
 * @author Alex Hu
 * @version 3.31.22
 */
public class ProcedureDeclaration extends Statement
{
    String name;
    Statement stmt;
    List<String> parameterList;

    /**
     * Constructor
     * 
     * @param name the name of the procedure
     * @param stmt the statement of the procedure
     * @param parameterList the list of parameters of the procedure
     */
    public ProcedureDeclaration(String name, Statement stmt, List<String> parameterList)
    {
        this.name = name;
        this.stmt = stmt;
        this.parameterList = parameterList;
    }

    /**
     * Enters this procedure into the environment
     * 
     * @param env the environment of the procedure
     */
    public void exec(Environment env)
    {
        env.setProcedure(name, this);
    }
}
