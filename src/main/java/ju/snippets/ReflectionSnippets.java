package ju.snippets;

/**
 *
 * @author michael
 */
public final class ReflectionSnippets {
    
    public static String getMethodName(int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[ste.length - 1 - depth].getMethodName();
    }
}
