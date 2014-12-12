/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ju.snippets;

/**
 *
 * @author michael
 */
public final class ReflectionSnippets {
    
    public static String getMethodName(int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
    }
}
