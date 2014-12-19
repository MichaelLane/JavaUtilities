package ju.snippets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static ju.snippets.StringSnippets.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class StringSnippetsTest {

    public StringSnippetsTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of underscoreToCamel method, of class StringSnippets.
     */
    @Test
    public void testUnderscoreToCamel() {
        assertEquals("hello", underscoreToCamel("hello"));
        assertEquals("getId", underscoreToCamel("get_id"));
        assertEquals("hello", underscoreToCamel("_hello"));
        assertEquals("HelloThere", underscoreToCamel("Hello_There"));
        assertEquals("HelloThere", underscoreToCamel("Hello_there"));
        assertEquals("helloThere", underscoreToCamel("_hello_there"));
    }

}