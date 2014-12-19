package ju.tbd;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class ItemIndexPair<T> {
    
    public final T item;
    public final int index;
    
    public ItemIndexPair(T item, int index) {
        this.item = item;
        this.index = index;
    }
}
