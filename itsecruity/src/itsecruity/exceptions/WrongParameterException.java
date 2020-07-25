
package itsecruity.exceptions;

public class WrongParameterException extends RuntimeException {

    /**
     * Creates a new instance of <code>WrongParameterException</code> without
     * detail message.
     */
    WrongParameterException() {

    }

    /**
     * Constructs an instance of <code>ScannerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongParameterException(final String msg) {
        super(msg);
    }

}
