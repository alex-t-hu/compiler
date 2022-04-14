package codegeneration;
import environment.Environment;
import java.util.*;

/**
 * Represents the entire pascal program in the Parse Tree.
 * In other words, it represents the root node.
 * 
 * @author Alex Hu
 * @version 4.13.22
 */
public class Program extends Statement
{
    List<ProcedureDeclaration> procedures;
    Statement stmt;

    /**
     * Constructor
     * 
     * @param procedures the list of procedure declarations
     * @param stmt the statement executed by the program
     */
    public Program(List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.procedures = procedures;
        this.stmt = stmt;
    }

    /**
     * Executes the program. A program consists of a list of procedure
     * declarations followed by a statement
     * 
     * @param env the environment to be used
     */
    public void exec(Environment env)
    {
        for (ProcedureDeclaration procedureDeclaration: procedures)
        {
            procedureDeclaration.exec(env);
        }

        stmt.exec(env);
    }

    /**
     * converts this program to assembly code
     * @param e the emitter used to write code to a file
     */
    public void compile(Emitter e)
    {
        e.emit("main:");

        for (ProcedureDeclaration proc : procedures)
        {
            proc.compile(e);
        }

        stmt.compile(e);
    }
}
