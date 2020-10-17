package testDriver;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class SyntaxErrorException extends RuntimeException {

	
	
    /**
     * Creates a new instance of <code>SyntaxErrorException</code> without detail
     * message.
     */
    public SyntaxErrorException() {
    }

    
    
    
    /**
     * Constructs an instance of <code>SyntaxErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SyntaxErrorException(final String msg) {
        super(msg);
    }

}
