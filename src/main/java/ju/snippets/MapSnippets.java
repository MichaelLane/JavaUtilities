package ju.snippets;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ju.snippets.CollectionSnippets.*;
import static ju.snippets.NumberSnippets.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class MapSnippets {
    
    /**
     * Calculates the sum of two maps. For example, if
     * map1 = {"hi"=2, "hello"=3}
     * map2 = {"yo"=4, "hello"=5}
     * 
     * then
     * sumMaps(map1, map2) = {"hi"=2, "hello"=8.0, "yo"=4}
     * 
     * Notice if one key is present in one map but not in the other, the sum is
     * simply the value of that key. Also notice that sums are doubles; this was
     * necessary to make the code reasonably concise. For this reason, VALUES SHOULD 
     * ONLY CONTAIN NUMBERS THAT CAN BE LOSSLESSLY CONVERTED TO DOUBLES.
     * 
     * @param <K>
     * @param <V>
     * @param map1 non-null
     * @param map2 non-null
     * @return 
     */
    public static <K, V extends Number> Map<K, Double> sumMaps(
        Map<K, V> map1, Map<K, V> map2) {
        
        Map<K, Double> sumMap = new HashMap(convertToDoubleMap(map2));
        
        map1.forEach((k, v) -> {
            sumMap.merge(k, v.doubleValue(), (a, b) -> {
                return a + b;
            });
        });
        
        return sumMap;
    }
    
    /**
     * @param <K>
     * @param <V>
     * @param map
     * @param withMap 
     */
    public static <K, V, C extends Collection<V>> void unionCollMaps(
    Map<K, C> map, 
    Map<? extends K, ? extends C> withMap) {
        
        withMap.forEach((k, v) -> {
            Collection<V> old = map.get(k);
            if (old != null) {
                addAllUniquely(old, v);
            } else {
                map.put(k, v);
            }
        });
    }
    
    public static <K, V, C extends Collection<V>> void addToCollMap(
    Map<K, C> map, K key, V value) {
        
        C old = map.get(key);
        old.add(value);
    }
}
