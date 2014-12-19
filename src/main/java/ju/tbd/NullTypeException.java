package ju.tbd;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class NullTypeException extends RuntimeException {
    
    public NullTypeException(String methodName, String type) {
        super("The method " + methodName + " was called on the null type " + type);
    }
}
