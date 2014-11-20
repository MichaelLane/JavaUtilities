package maps;

import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.Maps;
/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class MapsSnippets {
    
    /**
     * 
     * @param <K>
     * @param <V>
     * @param map1
     * @param map2
     * @return 
     */
    public static <K> Map<K, Number> sumMaps(
        Map<K, ? extends Number> map1, Map<K, ? extends Number> map2) {
        
        Map<K, Number> sumMap = new HashMap(map2);
        
        map1.forEach((k, v) -> 
            sumMap.merge(k, v, (a, b) ->
                a.doubleValue() + b.doubleValue()));        
        
        return sumMap;
    }
}
