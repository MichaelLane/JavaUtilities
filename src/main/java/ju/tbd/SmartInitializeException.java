package ju.tbd;

import static ju.snippets.CollectionSnippets.*;
import static ju.snippets.MapSnippets.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SmartInitializeException extends RuntimeException {
    
    public SmartInitializeException(String message) {
        super(message);
    }
    
    public SmartInitializeException() {
        super();
    }
}
