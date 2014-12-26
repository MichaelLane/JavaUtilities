package ju.snippets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ju.tbd.RuntimeReflectiveOperationException;
import static ju.tbd.SmartInitializer.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class NumberSnippets {
    
    public static Collection<Double> convertToDoubleColl(
    Collection<? extends Number> coll) {
        Collection<Double> doubleColl = (Collection<Double>) smartInitialize(coll);
        for (Number num : coll) {
            doubleColl.add(num.doubleValue());
        }
        return doubleColl;
    }
    
    public static Collection<Long> convertToLongColl(
    Collection<? extends Number> coll) {
        Collection<Long> longColl = (Collection<Long>) smartInitialize(coll);
        for (Number num : coll) {
            longColl.add(num.longValue());
        }
        return longColl;
    }
    
    public static Collection<Integer> convertToIntColl(
    Collection<? extends Number> coll) {
        Collection<Integer> intColl = (Collection<Integer>) smartInitialize(coll);
        for (Number num : coll) {
            intColl.add(num.intValue());
        }
        return intColl;
    }
    
    public static <K> Map<K, Double> convertValuesToDoubles(Map<K, ? extends Number> map) {
        Map<K, Double> doubleValues = (Map<K, Double>) smartInitialize(map);
        map.forEach((k, v) -> {
            doubleValues.put(k, v.doubleValue());
        });
        return doubleValues;
    }
    
    public static <K> Map<K, Long> convertValuesToLongs(Map<K, ? extends Number> map) {
        Map<K, Long> longValues = (Map<K, Long>) smartInitialize(map);
        map.forEach((k, v) -> {
            longValues.put(k, v.longValue());
        });
        return longValues;
    }
    
    public static <K> Map<K, Integer> convertValuesToInts(Map<K, ? extends Number> map) {
        Map<K, Integer> intValues = (Map<K, Integer>) smartInitialize(map);
        map.forEach((k, v) -> {
            intValues.put(k, v.intValue());
        });
        return intValues;
    }
}
