package collections;

import java.util.Collection;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public final class CollectionSnippets {
    
    public static <T> void addAllUniquely(Collection<? super T> coll, Collection<T> withColl) {
        for (T item : withColl) {
            if (!coll.contains(item)) {
                coll.add(item);
            }
        }
    }
}
