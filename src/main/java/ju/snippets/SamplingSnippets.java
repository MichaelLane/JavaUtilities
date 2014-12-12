package ju.snippets;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import ju.probability.SamplingBoundsException;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SamplingSnippets {
   
    /**
	 * Sample ONCE from a normal distribution. If you want to sample
	 * more than once, it would be better to instantiate a new
	 * NormalDistribution and sample from that.
	 * 
	 * @param mean
	 * @param sd must be positive
	 */
	public static double sampleFromNormal(double mean, double sd) {
		
		if (sd <= 0) {
			throw new IllegalArgumentException("sd must be positive");
		}

		NormalDistribution distr = new NormalDistribution(mean, sd);
		return distr.sample();
	}

	/**
	 * Sample ONCE from a binomial distribution. If you want to sample
	 * more than once, it would be better to instantiate a new
	 * BinomialDistribution and sample from that.
	 * 
	 * @param trials Number of trials; positive
	 * @param p Probability of success; in (0, 1)
	 */
	public static double sampleFromBinomial(int trials, double p) {

		if (trials <= 0) {
			throw new IllegalArgumentException("trials must be positive");
		}
		if (p < 0 || p > 1) {
			throw new IllegalArgumentException("p must be in the range (0, 1)");
		}

		BinomialDistribution distr = new BinomialDistribution(trials, p);
		return distr.sample();
	}

	/**
	 * Sample ONCE from a normal distribution. If you want to sample
	 * more than once, it would be better to instantiate a new
	 * UniformRealDistribution and sample from that.
	 * 
	 * @param lower Lower bound of the uniform distro
	 * @param upper Upper bound of the uniform distro
	 */
	public static double sampleFromUniformReal(double lower, double upper) {

		UniformRealDistribution distr = new UniformRealDistribution(lower, upper);
		return distr.sample();
	}
    
    public static double sampleFromNormalAboveBound(double mean, double sd, double bound) {
        
        NormalDistribution distr = new NormalDistribution(mean, sd);
        return sampleAboveBound(distr, bound, Integer.MAX_VALUE);
    }
    
    public static double sampleFromBinomialAboveBound(int trials, double p, double bound) {
        
        BinomialDistribution distr = new BinomialDistribution(trials, p);
        return sampleAboveBound(distr, bound, Integer.MAX_VALUE);
    }
    
    public static double sampleFromNormalBelowBound(double mean, double sd, double bound) {
        
        NormalDistribution distr = new NormalDistribution(mean, sd);
        return sampleBelowBound(distr, bound, Integer.MAX_VALUE);
    }
    
    public static double sampleFromBinomialBelowBound(int trials, double p, double bound) {
        
        BinomialDistribution distr = new BinomialDistribution(trials, p);
        return sampleBelowBound(distr, bound, Integer.MAX_VALUE);
    }
    
    public static double sampleFromNormalWithinBounds(
    double mean, double sd, double lower, double upper) {
        
        NormalDistribution distr = new NormalDistribution(mean, sd);
        return sampleWithinBounds(distr, lower, upper, Integer.MAX_VALUE);
    }
    
    public static double sampleFromBinomialWithinBounds(
    int trials, double p, double lower, double upper) {
        
        BinomialDistribution distr = new BinomialDistribution(trials, p);
        return sampleWithinBounds(distr, lower, upper, Integer.MAX_VALUE);
    }
    
    public static double sampleAboveBound(
    AbstractRealDistribution distro, double bound, int attempts) {
        return sampleWithinBounds(distro, bound, Double.POSITIVE_INFINITY, attempts);
    }
    
    public static double sampleBelowBound(
    AbstractRealDistribution distro, double bound, int attempts) {
        return sampleWithinBounds(distro, Double.NEGATIVE_INFINITY, bound, attempts);
    }
    
    public static double sampleWithinBounds(
    AbstractRealDistribution distro, double lower, double upper, int attempts) {
        
        for (int attempt = 0; attempt < attempts; attempt++) {
            double sample = distro.sample();
            if (sample >= lower && sample <= upper) {
                return sample;
            }
        }
        
        throw new SamplingBoundsException();
    }
    
    public static double sampleAboveBound(
    AbstractIntegerDistribution distro, double bound, int attempts) {
        return sampleWithinBounds(distro, bound, Double.POSITIVE_INFINITY, attempts);
    }
    
    public static double sampleBelowBound(
    AbstractIntegerDistribution distro, double bound, int attempts) {
        return sampleWithinBounds(distro, Double.NEGATIVE_INFINITY, bound, attempts);
    }
    
    public static double sampleWithinBounds(
    AbstractIntegerDistribution distro, double lower, double upper, int attempts) {
        
        for (int attempt = 0; attempt < attempts; attempt++) {
            double sample = distro.sample();
            if (sample >= lower && sample <= upper) {
                return sample;
            }
        }
        
        throw new SamplingBoundsException();
    }
}
