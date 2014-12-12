package ju.probability;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SamplingBoundsException extends RuntimeException {

    public SamplingBoundsException() {
        super("Could not sample within the given bounds.");
    }

}
