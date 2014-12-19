/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ju.snippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ju.snippets.CollectionSnippets.*;
import ju.tbd.ItemIndexPair;

/**
 *
 * @author michael
 */
public class CollectionSnippetsTest {
    
    private Map<String, Integer> bigMap;
    private List<String> letters;
    
    public CollectionSnippetsTest() {
    }
    
    @Before
    public void setUp() {
        bigMap = new HashMap();
        bigMap.put("a", 7);
        bigMap.put("b", 2);
        bigMap.put("c", 1);
        bigMap.put("d", 4);
        bigMap.put("e", 6);
        bigMap.put("f", 3);
        bigMap.put("g", 9);
        bigMap.put("h", 10);
        bigMap.put("i", 5);
        letters = new ArrayList(Arrays.asList(
        "a", "b", "c", "d", "e", "f", "g", "h", "i"));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addAllUniquely method, of class CollectionSnippets.
     */
    @Test
    public void testAddAllUniquely() {
        
        /* test subset */
        List<Integer> coll = new ArrayList(Arrays.asList(
        1, 2, 3));
        List<Integer> withColl = new ArrayList(Arrays.asList(
        2, 3));
        
        addAllUniquely(coll, withColl);
        
        List<Integer> expected = new ArrayList(Arrays.asList(
        1, 2, 3));
        
        assertEquals(expected, coll);
        /* end test subset */
        
        
        /* test nonempty intersection */
        coll = new ArrayList(Arrays.asList(
        1, 2, 3));
        withColl = new ArrayList(Arrays.asList(
        2, 3, 4));
        
        addAllUniquely(coll, withColl);
        
        expected = new ArrayList(Arrays.asList(
        1, 2, 3, 4));
        
        assertEquals(expected, coll);
        /* end test nonempty intersection */
        
        
        /* test empty intersection */
        coll = new ArrayList(Arrays.asList(
        1, 2));
        withColl = new ArrayList(Arrays.asList(
        3, 4));
        
        addAllUniquely(coll, withColl);
        
        expected = new ArrayList(Arrays.asList(
        1, 2, 3, 4));
        assertEquals(expected, coll);
        /* end test empty intersection */
        
    }

    /**
     * Test of sortUsingValuesInMap method, of class CollectionSnippets.
     */
    @Test
    public void testSortUsingValuesInMap() {
    }

    /**
     * Test of getMinUsingValuesInMap method, of class CollectionSnippets.
     */
    @Test
    public void testGetMinUsingValuesInMap() {
        
        /* singleton edge case */
        List<String> list = new ArrayList();
        list.add("d");
        ItemIndexPair<String> pair = getMinUsingValuesInMap(list, bigMap);
        assertEquals(pair.index, 0);
        assertEquals(pair.item, "d");
        /* end singleton edge case */
        
        /* subset of letters */
        List<String> subset = letters.subList(4, 8);
        pair = getMinUsingValuesInMap(subset, bigMap);
        assertEquals(pair.index, 5);
        assertEquals(pair.item, "f");
        /* end subset of letters */        
        
        /* full letters */
        pair = getMinUsingValuesInMap(letters, bigMap);
        assertEquals(pair.index, 2);
        assertEquals(pair.item, "c");
        /* end full letters */
    }

    /**
     * Test of getMaxUsingValuesInMap method, of class CollectionSnippets.
     */
    @Test
    public void testGetMaxUsingValuesInMap() {
        
        /* singleton edge case */
        List<String> list = new ArrayList();
        list.add("f");
        ItemIndexPair<String> pair = getMaxUsingValuesInMap(list, bigMap);
        assertEquals(pair.index, 0);
        assertEquals(pair.item, "f");
        /* end singleton edge case */
        
        /* subset of letters */
        List<String> subset = letters.subList(0, 4);
        pair = getMaxUsingValuesInMap(subset, bigMap);
        assertEquals(pair.index, 0);
        assertEquals(pair.item, "a");
        /* end subset of letters */        
        
        /* full letters */
        pair = getMaxUsingValuesInMap(letters, bigMap);
        assertEquals(pair.index, 7);
        assertEquals(pair.item, "h");
        /* end full letters */
        
    }
    
}
