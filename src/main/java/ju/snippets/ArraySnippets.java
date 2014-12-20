package ju.snippets;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import static ju.snippets.ComparatorSnippets.*;

/**
 * Some useful methods for sorting Arrays.
 * 
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class ArraySnippets {
    
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
     * 
     * #needtest
     * 
     * @param <T1>
     * @param <T2>
     * @param array
     * @param map
     */
    public static <T1, T2 extends T1> void sortUsingValuesInMap(T2[] array, Map<T1, ? extends Comparable> map) {
        
        Arrays.sort(array, comparatorUsingValuesInMap(map));
    }
}

