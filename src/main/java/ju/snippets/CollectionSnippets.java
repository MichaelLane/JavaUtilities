package ju.snippets;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ju.snippets.ComparatorSnippets.*;
import static ju.snippets.NumberSnippets.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class CollectionSnippets {
    
    public static <T> void addAllUniquely(Collection<T> coll, Collection<? extends T> withColl) {
        for (T item : withColl) {
            if (!coll.contains(item)) {
                coll.add(item);
            }
        }
    }
    
    /**
     * Sorts a List according to values stored in a Map. 
     * 
     * For example, if list contains
     * the five U.S. States "Florida", "Georgia", "Alabama", Mississippi", "South 
     * Carolina" in that order, and values maps those U.S. states to the year of 
     * their admission to the union: Florida => 1845, Georgia => 1788, Alabama => 1819
     * Mississippi => 1817, Louisiana => 1812; then after this method is called,
     * list will be sorted as follows: Georgia, Louisiana, Mississippi, Alabama,
     * Florida.
     * 
     * For several reasons, before any sorting occurs, the "values" Map is converted
     * to a new Map that maps keys to Doubles, rather than Numbers. For this reason,
     * VALUES SHOULD ONLY CONTAIN NUMBERS THAT CAN BE LOSSLESSLY CONVERTED TO DOUBLES.
     * Such Numbers are: Byte, Short, Integer, Char, Long, Float, or Double. This 
     * excludes BigInteger and BigDecimal in the standard Java API.
     * 
     * @param <T>
     * @param list non-null
     * @param values non-null
     */
    public static <T> void sortUsingValuesInMap(
    List<? extends T> list, Map<T, ? extends Number> values) {
        
        Map<T, Double> doubleValues = convertToDoubleMap(values);
        Collections.sort(list, comparatorUsingValuesInMap(doubleValues));
    }
    
    public static <T> T getMinUsingValuesInMap(
    List<? extends T> list, Map<T, ? extends Number> values) {
        
        Map<T, Double> doubleValues = convertToDoubleMap(values);
        return Collections.min(list, comparatorUsingValuesInMap(doubleValues));
    }
    
    public static <T> T getMaxUsingValuesInMap(
    List<? extends T> list, Map<T, ? extends Number> values) {
        
        Map<T, Double> doubleValues = convertToDoubleMap(values);
        return Collections.max(list, comparatorUsingValuesInMap(doubleValues));
    }
}
