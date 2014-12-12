package ju.systemOutPrinting;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class PrettyPrinting {
    
    public static <K, V> void prettyPrint(String message, Map<K, V> map) {
        prettyPrint(message, map, 1);
    }
    
    public static <K, V> void prettyPrint(String message, Map<K, V> map, int numTabs) {
        
        if (map == null) return;
        if (numTabs < 0) numTabs = 0;
        
        String tabs = StringUtils.repeat('\t', numTabs);
        if (message != null) System.out.println(formattedMessage(message, numTabs));
        Iterable<K> keys = map.keySet();  
        for (K key : keys) {
            V value = map.get(key);
            String kStr;
            String vStr;
            if (key != null) kStr = key.toString();
            else kStr = "null";
            if (value != null) vStr = value.toString();
            else vStr = "null";
            System.out.println(tabs + kStr + " => " + vStr);
        }
    }
    
    public static <T> void prettyPrint(String message, Iterable<T> iter) {
        prettyPrint(message, iter, 1);
    }
    
    public static <T> void prettyPrint(String message, Iterable<T> iter, int numTabs) {
        
        if (iter == null) return;
        if (numTabs < 0) numTabs = 0;
        
        String tabs = StringUtils.repeat('\t', numTabs);
        if (message != null) System.out.println(formattedMessage(message, numTabs));
        for (T item : iter) {
            String str;
            if (item != null) str = item.toString();
            else str = "null";
            System.out.println(tabs + str);
        }
    }
    
    public static <K, V> void prettyPrintMapSubset(String message, Iterable<K> iter, Map<K, V> map) {
        prettyPrintMapSubset(message, iter, map, 1);
    }
    
    /**
     * Pretty print an Iterable, with items in the list attached to their corresponding
     * values in a map; e.g. if the Map sends states in the union to year of their
     * admission to the Union, and Iterable contains the states "Florida, Georgia,
     * Alabama", then this method prints
     * 
     * Florida => 1845
     * Georgia => 1819
     * Alabama => 1819
     */
    public static <K, V> void prettyPrintMapSubset(
    String message, Iterable<K> iter, Map<K, V> map, int numTabs) {
        
        if (iter == null || map == null) return;
        if (numTabs < 0) numTabs = 0;
        
        String tabs = StringUtils.repeat('\t', numTabs);
        if (message != null) System.out.println(formattedMessage(message, numTabs));
        
        for (K item : iter) {
            String kStr;
            String vStr;
            if (item != null) kStr = item.toString();
            else kStr = "null";
            if (map.containsKey(item)) {
                V value = map.get(item);
                if (value != null) vStr = value.toString();
                else vStr = "null";
            } else vStr = "null";
            System.out.println(tabs + kStr + " => " + vStr);
        }
    }
    
    private static String formattedMessage(String message, int numTabs) {
        numTabs = (numTabs > 0) ? numTabs - 1 : 0;
        String tabsMessage = StringUtils.repeat('\t', numTabs);
        return tabsMessage + message;
    }
}
