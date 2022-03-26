package ast;
import java.util.*;
import environment.Environment;

/**
 * Represents a block, which is a list of statements
 * 
 * @author Alex Hu
 * @version 3.25.22
 */
public class Block extends Statement
{
    List<Statement> stmts;
    
    /**
     * Constructor for Block
     * @param stmts the list of statements
     */
    public Block(List<Statement> stmts)
    {
        this.stmts = stmts;
    }

    /**
     * Executes each statement in the block in order
     * @param env the environment used
     */
    public void exec(Environment env)
    {
        for(Statement stmt: stmts)
        {
            stmt.exec(env);
        }
    }
}
