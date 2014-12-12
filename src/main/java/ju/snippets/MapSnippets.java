package ju.snippets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ju.snippets.CollectionSnippets.*;

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
     * 
     * @param <K>
     * @param map1 non-null
     * @param map2 non-null
     * @return 
     */
    public static <K> Map<K, Number> sumMaps(
        Map<K, ? extends Number> map1, Map<K, ? extends Number> map2) {
        
        if (map1 == null || map2 == null) return null;
        
        Map<K, Number> sumMap = new HashMap(map2);
        
        map1.forEach((k, v) -> 
            sumMap.merge(k, v, (a, b) ->
                a.doubleValue() + b.doubleValue()));        
        
        return sumMap;
    }
    
    public static <K, V> void unionListMaps(
    Map<K, List<V>> map, 
    Map<? extends K, ? extends List<V>> withMap) {
        
        withMap.forEach((k, v) -> {
            List<V> old = map.get(k);
            if (old != null) {
                addAllUniquely(old, v);
            } else {
                map.put(k, v);
            }
        });
    }
}
