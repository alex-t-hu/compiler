package codegeneration;
import java.io.*;

/**
 * Emits assembly code to a file
 * 
 * @author Mrs. Datar
 * @version 4.13.22
 */
public class Emitter
{
    private PrintWriter out;

    /**
     * constructor
     * 
     * @param outputFileName the name of the file that contains the code
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * writes code to the file with correct indentation
     * @param code the code that is written
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;

        out.println(code);
    }

	/**
	 * closes the file
	 * 
	 * @precondition should be called after all calls to emit.
	 */
    public void close()
    {
        out.close();
    }
}