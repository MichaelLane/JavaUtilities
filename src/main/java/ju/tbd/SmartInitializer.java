package ju.tbd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ju.snippets.CollectionSnippets.*;
import static ju.snippets.MapSnippets.*;

/**
 * I think that this is an instance of the "visitor" design pattern?
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class SmartInitializer {

    private static final String DEFAULT_TO_NULLARY_CONSTRUCTOR_UNEXPECTED_EXCEPTION = 
        "smartInitialize is "
        + "missing an initializer for the parameter's type. The nullary "
        + "construtor "
        + "was used in an attempt to return a new instance, but an "
        + "exception was thrown.";
    
    /**
     * This is either a good idea or a really bad idea...
     * @param o
     * @return 
     */
    public static Object smartInitialize(Object o) {        
        
        if (o instanceof ArrayList) {
            return new ArrayList(((ArrayList) o).size());
        } else if (o instanceof HashMap) {
            return new HashMap(((HashMap) o).size());

        } else {
            try {
                return o.getClass().newInstance();   
            } catch (IllegalAccessException e) {
                if (o instanceof List) {
                    return new ArrayList(((List) o).size());
                } else if (o instanceof Map) {
                    return new HashMap(((Map) o).size());
                } else {
                    throw new SmartInitializeException(
                    "The parameter's type does not have an explicit initializer, "
                        + "its nullary constructor has blocked access, and this "
                        + "scenario cannot be resolved for the parameter's "
                        + "type in particular.");
                }
            } catch (ReflectiveOperationException e) {
                throw new RuntimeReflectiveOperationException(
                    DEFAULT_TO_NULLARY_CONSTRUCTOR_UNEXPECTED_EXCEPTION);
            }
        }
    }
}
