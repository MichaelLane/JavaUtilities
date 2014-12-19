package ju.snippets;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SamplingSnippetsTest {

    private BinomialDistribution binomial;
    private UniformRealDistribution real;
    private NormalDistribution normal;
    
    
    public SamplingSnippetsTest() {
    }

    @Before
    public void setUp() {
        binomial = new BinomialDistribution(100, 0.5);
        real = new UniformRealDistribution(0, 1);
        normal = new NormalDistribution(0, 1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sampleAboveBound method, of class SamplingSnippets.
     */
    @Test
    public void testSampleAboveBound_3args_1() {
    }

    /**
     * Test of sampleBelowBound method, of class SamplingSnippets.
     */
    @Test
    public void testSampleBelowBound_3args_1() {
    }

    /**
     * Test of sampleWithinBounds method, of class SamplingSnippets.
     */
    @Test
    public void testSampleWithinBounds_4args_1() {
    }

    /**
     * Test of sampleAboveBound method, of class SamplingSnippets.
     */
    @Test
    public void testSampleAboveBound_3args_2() {
    }

    /**
     * Test of sampleBelowBound method, of class SamplingSnippets.
     */
    @Test
    public void testSampleBelowBound_3args_2() {
    }

    /**
     * Test of sampleWithinBounds method, of class SamplingSnippets.
     */
    @Test
    public void testSampleWithinBounds_4args_2() {
    }

}