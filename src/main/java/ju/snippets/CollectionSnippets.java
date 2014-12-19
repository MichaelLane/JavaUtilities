package ju.snippets;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import static ju.snippets.ComparatorSnippets.*;
import ju.tbd.ItemIndexPair;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class CollectionSnippets {

    /**
     * 
     * @param <T>
     * @param coll
     * @param withColl 
     */
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
    List<? extends T> list, Map<T, ? extends Comparable> values) {
        
        Collections.sort(list, comparatorUsingValuesInMap(values));
    }
    
    /**
     * @param <K>
     * @param list
     * @param values
     * @return 
     */
    public static <K> ItemIndexPair<K> getMinUsingValuesInMap(
    List<? extends K> list, Map<K, ? extends Comparable> values) {
        
        if (list.isEmpty()) return null;
        
        ListIterator<? extends K> iter = list.listIterator();
        int index = 0;
        int minIndex = 0;
        K minItem = iter.next();
        Comparable minValue = values.get(minItem);
        while (iter.hasNext()) {
            K item = iter.next();
            Comparable value = values.get(item);
            if (value.compareTo(minValue) < 0) {
                minIndex = index;
            }
            index++;
        }
        
        return new ItemIndexPair(minItem, minIndex);
    }
    
    /**
     * @param <K>
     * @param list
     * @param values
     * @return 
     */
    public static <K> ItemIndexPair<K> getMaxUsingValuesInMap(
    List<? extends K> list, Map<K, ? extends Comparable> values) {
        
        if (list.isEmpty()) return null;
        
        ListIterator<? extends K> iter = list.listIterator();
        int index = 0;
        int maxIndex = 0;
        K maxItem = iter.next();
        Comparable maxValue = values.get(maxItem);
        while (iter.hasNext()) {
            K item = iter.next();
            Comparable value = values.get(item);
            if (value.compareTo(maxValue) > 0) {
                maxIndex = index;
            }
            index++;
        }
        
        return new ItemIndexPair(maxItem, maxIndex);
    }
}
