package ju.snippets;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class ComparatorSnippets {
    
    /**
     * Get the natural comparator for the keys of a map, where the values implement
     * Comparable. For example:
     * map = {"Hello" => 1, "Hi" => 2, "Sup" => 3}
     * comparator = comparatorUsingValuesInMap(map)
     * comparator.compare("Hello", "Hi") &lt; 0
     * comparator.compare("Sup", "Hi") &gt; 0
     * @param <K>
     * @param <V>
     * @param map
     * @return 
     */
    public static <K, V extends Comparable> Comparator<K> comparatorUsingValuesInMap(
    Map<K, V> map) {
        
        return (K k1, K k2) -> {
            return map.get(k1).compareTo(map.get(k2));
        };
    }
}
