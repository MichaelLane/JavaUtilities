package ju.snippets;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class ComparatorSnippets {
    
    public static <K, V extends Comparable> Comparator<K> comparatorUsingValuesInMap(
    Map<K, V> values) {
        
        return (K k1, K k2) -> {
            return values.get(k1).compareTo(values.get(k2));
        };
    }
}
