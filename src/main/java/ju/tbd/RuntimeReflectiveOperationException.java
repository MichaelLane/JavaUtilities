package ju.tbd;

/**
 * Thrown whenever a ReflectiveOperationException is, except this Exception is unchecked.
 * Generally this should be used in favor of ReflectiveOperationException if it 
 * is highly likely that it is the programmer's fault that a ReflectiveOperationException
 * occurred.
 * @author Michael Lane <mlane@gatech.edu>
 */
public class RuntimeReflectiveOperationException extends RuntimeException {
    
    public RuntimeReflectiveOperationException(String message) {
        super(message);
    }
    
    public RuntimeReflectiveOperationException() {
        super("An unexpected ReflectiveOperationException was thrown.");
    }
}
