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
    
    public static <K> Map<K, Double> convertToDoubleMap(Map<K, ? extends Number> map) {
        Map<K, Double> doubleValues = new HashMap();
        map.forEach((k, v) -> {
            doubleValues.put(k, v.doubleValue());
        });
        return doubleValues;
    }
}
