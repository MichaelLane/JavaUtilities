package ju.snippets;

import java.util.Collection;
import java.util.HashMap;
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
     * Adds all of the key-value pairs from withMap to map. If two keys are incident
     * in the two maps, then the union of their values are put into map. For example,
     * map              = {1 => [4, 5], 2 => [6, 7]}
     * withMap          = {1 => [4, 8], 3 => [9, 10]}
     * unionCollMaps(map, withMap)
     * map              = {1 => [4, 5, 8], 2 => [6, 7], 3 => [9, 10]}
     * withMap          = {1 => [4, 8], 3 => [9, 10]}
     * 
     * @param <K>
     * @param <C>
     * @param map
     * @param withMap 
     */
    public static <K, C extends Collection> void unionCollMaps(
    Map<K, C> map, 
    Map<? extends K, ? extends C> withMap) {
        
        withMap.forEach((k, v) -> {
            Collection old = map.get(k);
            if (old != null) {
                addAllUniquely(old, v);
            } else {
                map.put(k, v);
            }
        });
    }
    
    /**
     * Append values to a key in a Map, where the values in the Map are Collections. 
     * Example:
     * map = {"Hello" => [1, 2], "Hi" => [2, 3]}
     * key = "Hello"
     * values = [3, 4]
     * addAllToCollMap(map, key, values)
     * map = {"Hello" => [1, 2, 3, 4], "Hi" => [2, 3]}
     * 
     * If key is not present in map, then create it:
     * map = {"Hello" => [1, 2], "Hi" => [2, 3]}
     * key = "Sup"
     * values = [5, 6]
     * addAllToCollMap(map, key, values)
     * map = {"Hello" => [1, 2], "Hi" => [2, 3], "Sup" => [5, 6]}
     * 
     * @param <K>
     * @param <C>
     * @param map
     * @param key 
     * @param values 
     */
    public static <K, C extends Collection> void addAllToCollMap(
    Map<K, C> map, K key, C values) {
        
        C old = map.get(key);
        if (old != null) {
            old.addAll(values);
        } else {
            map.put(key, values);
        }
    }
    
    /**
     * The natural companion to addAllToCollMap. If key does not exist in map, then
     * a new Collection must be instantiated. It is not possible to infer the type
     * to instantiate through generics, so a Class must be provided so that the 
     * correct type is known at runtime.
     * 
     * @param <K>
     * @param <V>
     * @param <C>
     * @param map
     * @param key
     * @param value
     * @param cls
     * @throws java.lang.ReflectiveOperationException Thrown if something goes wrong
     * with instantiating the Collection.
     */
    public static <K, V, C extends Collection<? super V>> void addToCollMap(
    Map<K, C> map, K key, V value, Class<? extends Collection> cls) {
        
        C old = map.get(key);
        if (old != null) {
            old.add(value);
        } else {
            C newColl = null;
            try {
                newColl = (C) cls.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Problem instantiating class: " + cls.toString());
            }
            newColl.add(value);
            map.put(key, newColl);
        }
    }
    
}
