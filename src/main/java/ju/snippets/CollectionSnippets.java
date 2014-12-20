package ju.snippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
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
     * Add all the elements of withColl to coll, except the elements that already 
     * occur in coll. Example:
     * coll         = [1, 2, 3]
     * withColl     = [2, 3, 4, 5]
     * addAllUniquely(coll, withColl)
     * coll         = [1, 2, 3, 4, 5]
     * withColl     = [2, 3, 4, 5]
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
     * Add all the elements of withColl to coll, except the elements that already 
     * occur in coll. Example:
     * coll         = [1, 2, 3]
     * withColl     = [2, 3, 4, 5]
     * addAllUniquely(coll, withColl)
     * coll         = [1, 2, 3, 4, 5]
     * withColl     = [2, 3, 4, 5]
     * @param <T>
     * @param coll
     * @param withColl 
     */
    public static <T> void addUniquely(Collection<T> coll, T item) {
        if (!coll.contains(item)) {
            coll.add(item);
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
     * @param <T>
     * @param list non-null
     * @param values non-null
     */
    public static <T> void sortUsingValuesInMap(
    List<? extends T> list, Map<T, ? extends Comparable> values) {
        
        Collections.sort(list, comparatorUsingValuesInMap(values));
    }
    
    /**
     * Get the minimum of a List according to values stored in a Map. 
     * @param <K>
     * @param list
     * @param values
     * @return The ItemIndexPair representing the minimum Object and its index in 
     * the List.
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
                minItem = item;
                minIndex = index;
            }
            index++;
        }
        
        return new ItemIndexPair(minItem, minIndex);
    }
    
    /**
     * Get the minimum of a Collection according to values stored in a Map. 
     * @param <K>
     * @param coll
     * @param values
     * @return 
     */
    public static <K> K getMinUsingValuesInMap(
    Collection<? extends K> coll, Map<K, ? extends Comparable> values) {
        
        if (coll.isEmpty()) return null;
        
        Iterator<? extends K> iter = coll.iterator();
        K minItem = iter.next();
        Comparable minValue = values.get(minItem);
        while (iter.hasNext()) {
            K item = iter.next();
            Comparable value = values.get(item);
            if (value.compareTo(minValue) < 0) {
                minItem = item;
            }
        }
        
        return minItem;
    }
    
    /**
     * Get the maximum of a List according to values stored in a Map. 
     * @param <K>
     * @param list
     * @param values
     * @return The ItemIndexPair representing the maximum Object and its index in 
     * the List.
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
                maxItem = item;
                maxIndex = index;
            }
            index++;
        }
        
        return new ItemIndexPair(maxItem, maxIndex);
    }
    
    /**
     * Get the maximum of a Collection according to values stored in a Map. 
     * @param <K>
     * @param coll
     * @param values
     * @return 
     */
    public static <K> K getMaxUsingValuesInMap(
    Collection<? extends K> coll, Map<K, ? extends Comparable> values) {
        
        if (coll.isEmpty()) return null;
        
        Iterator<? extends K> iter = coll.iterator();
        K maxItem = iter.next();
        Comparable maxValue = values.get(maxItem);
        while (iter.hasNext()) {
            K item = iter.next();
            Comparable value = values.get(item);
            if (value.compareTo(maxValue) < 0) {
                maxItem = item;
            }
        }
        
        return maxItem;
    }
    
    /**
     * Initialize a List with the given items.
     * @param <T>
     * @param args
     * @return 
     */
    public static <T> List<T> init(Class<? extends List> cls, T... args) {
        try {
            return cls.getDeclaredConstructor(Collection.class).newInstance(args);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not instantiate class " + cls.toString());
        }
    }
}
