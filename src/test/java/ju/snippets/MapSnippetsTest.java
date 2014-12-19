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

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class MapSnippetsTest {
    
    public MapSnippetsTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sumMaps method, of class MapSnippets.
     */
    @Test
    public void testSumMaps() {
        Map<Integer, Integer> map1 = new HashMap();
        Map<Integer, Integer> map2 = new HashMap();
        
        map1.put(1, 2);
        map1.put(2, 3);
        map1.put(3, 4);
        map1.put(4, 5);
        map2.put(3, 6);
        map2.put(4, 7);
        map2.put(5, 8);
        map2.put(6, 9);
        
        Map<Integer, Double> actual = MapSnippets.sumMaps(map1, map2);
        
        Map<Integer, Double> expected = new HashMap();
        expected.put(1, 2.0);
        expected.put(2, 3.0);
        expected.put(3, 10.0);
        expected.put(4, 12.0);
        expected.put(5, 8.0);
        expected.put(6, 9.0);
        
        assertEquals(expected, actual);
    }

    /**
     * Test of unionListMaps method, of class MapSnippets.
     */
    @Test
    public void testUnionListMaps() {
        
        /* single disjoint union */
        Map<Integer, List<Integer>> map = new HashMap();
        Map<Integer, List<Integer>> withMap = new HashMap();
        map.put(1, new ArrayList(Arrays.asList(1, 2)));
        withMap.put(1, new ArrayList(Arrays.asList(3, 4)));
        Map<Integer, List<Integer>> expected = new HashMap();
        expected.put(1, new ArrayList(Arrays.asList(1, 2, 3, 4)));
        assertEquals(expected, map);
        /* end single disjoint union */
        
        /* single nondisjoint union */
        map = new HashMap();
        withMap = new HashMap();
        map.put(1, new ArrayList(Arrays.asList(1, 2, 3)));
        withMap.put(1, new ArrayList(Arrays.asList(2, 3, 4)));
        expected = new HashMap();
        expected.put(1, new ArrayList(Arrays.asList(1, 2, 3, 4)));
        assertEquals(expected, map);
        /* end single nondisjoint union */
        
        /* single subset union */
        map = new HashMap();
        withMap = new HashMap();
        map.put(1, new ArrayList(Arrays.asList(1, 2, 3)));
        withMap.put(1, new ArrayList(Arrays.asList(2, 3)));
        expected = new HashMap();
        expected.put(1, new ArrayList(Arrays.asList(1, 2, 3)));
        assertEquals(expected, map);
        /* end single subset union */
        
        /* double union of nondisjoint and disjoint */
        map = new HashMap();
        withMap = new HashMap();
        map.put(1, new ArrayList(Arrays.asList(1, 2, 3)));
        withMap.put(1, new ArrayList(Arrays.asList(1, 3, 4, 5)));
        map.put(2, new ArrayList(Arrays.asList(1, 2)));
        withMap.put(2, new ArrayList(Arrays.asList(4, 5)));
        expected = new HashMap();
        expected.put(1, new ArrayList(Arrays.asList(1, 2, 3, 4, 5)));
        expected.put(2, new ArrayList(Arrays.asList(1, 2, 4, 5)));
        assertEquals(expected, map);
        /* end double union of nondisjoint and disjoint */
    }
    
}
