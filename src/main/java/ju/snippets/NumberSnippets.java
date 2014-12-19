package ju.snippets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class NumberSnippets {
    
    public static List<Double> convertToDoubleList(List<? extends Number> list) {
        List<Double> doubleList = new ArrayList(list.size());
        for (Number num : list) {
            doubleList.add(num.doubleValue());
        }
        return doubleList;
    }
    
    public static List<Long> convertToLongList(List<? extends Number> list) {
        List<Long> longList = new ArrayList(list.size());
        for (Number num : list) {
            longList.add(num.longValue());
        }
        return longList;
    }
    
    public static List<Integer> convertToIntList(List<? extends Number> list) {
        List<Integer> intList = new ArrayList(list.size());
        for (Number num : list) {
            intList.add(num.intValue());
        }
        return intList;
    }
    
    public static <K> Map<K, Double> convertToDoubleMap(Map<K, ? extends Number> map) {
        Map<K, Double> doubleValues = new HashMap();
        map.forEach((k, v) -> {
            doubleValues.put(k, v.doubleValue());
        });
        return doubleValues;
    }
    
    public static <K> Map<K, Long> convertToLongMap(Map<K, ? extends Number> map) {
        Map<K, Long> longValues = new HashMap();
        map.forEach((k, v) -> {
            longValues.put(k, v.longValue());
        });
        return longValues;
    }
    
    public static <K> Map<K, Integer> convertToIntMap(Map<K, ? extends Number> map) {
        Map<K, Integer> intValues = new HashMap();
        map.forEach((k, v) -> {
            intValues.put(k, v.intValue());
        });
        return intValues;
    }
}
