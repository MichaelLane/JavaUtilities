package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Some useful methods for sorting Arrays.
 * 
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SortingSnippets {
    
    /**
     * Sorts an array according to values stored in a Map. 
     * 
     * For example, if array contains
     * the five U.S. States "Florida", "Georgia", "Alabama", Mississippi", "South 
     * Carolina" in that order, and values maps those U.S. states to the year of 
     * their admission to the union: Florida => 1845, Georgia => 1788, Alabama => 1819
     * Mississippi => 1817, Louisiana => 1812; then after this method is called,
     * array will be sorted as follows: Georgia, Louisiana, Mississippi, Alabama,
     * Florida.
     * 
     * For several reasons, before any sorting occurs, the "values" Map is converted
     * to a new Map that maps keys to Doubles, rather than Numbers. For this reason,
     * VALUES SHOULD ONLY CONTAIN NUMBERS THAT CAN BE LOSSLESSLY CONVERTED TO DOUBLES.
     * Such Numbers are: Byte, Short, Integer, Char, Long, Float, or Double. This 
     * excludes BigInteger and BigDecimal in the standard Java API.
     * 
     * @param <T>
     * @param array non-null
     * @param values non-null
     */
    public static <T> void sortUsingValuesInMap(T[] array, Map<T, Number> values) {
        
        if (array == null || values == null) return;
        
        Map<T, Double> doubleValues = new HashMap();
        for (T key : values.keySet()) {
            doubleValues.put(key, values.get(key).doubleValue());
        }
        
        Arrays.sort(array, (T t1, T t2) -> { //comparator
            if (doubleValues.get(t1) > doubleValues.get(t2)) return 1;
            if (doubleValues.get(t1) < doubleValues.get(t2)) return -1;
            return 0;
        });
    }
}
