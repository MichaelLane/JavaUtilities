package ju.snippets;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Some useful methods for working with strings.
 * 
 * @author Michael Lane
 */
public final class StringSnippets {
    
    /**
     * Converts a string of underscores to a camelfied string; e.g.
     * parse_file => parseFile
     * Apples_and_oranges => ApplesAndOranges
     * Apples_And_Oranges => ApplesAndOranges
     * 
     * @param str non-null
     * @return 
     */
    public static String underscoreToCamel(String str) {
        
        // capitalize the first character and every character after an underscore
        String toReturn = WordUtils.capitalize(str, '_');
        // take out underscores
        toReturn = toReturn.replaceAll("_", "");
        // make the first character big or little case, depending on str
        toReturn = 
            new StringBuilder(toReturn)
            .replace(0, 1, str.substring(0, 1))
            .toString();
        return toReturn;
        
	}
    
    /**
     * Converts a camelCase string into a string of underscores; e.g.
     * parseFile => parse_file
     * ApplesAndOranges => Apples_And_Oranges
     * 
     * @param str non-null
     * @return 
     */
//    public static String camelToUnderscore(String str) {
//        
//        if (str == null) return null;
//        // e.g.
//        // parseID => parse_ID
//        // parseFile => parse_File
//        // ApplesANDOranges => ApplesAND_Oranges
//        String toReturn = str.replaceAll(".[A-Z][^A-Z]", "$1_$2");
//        
//    }
}
