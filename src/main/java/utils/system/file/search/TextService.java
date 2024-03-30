package utils.system.file.search;

public interface TextService {

    /**
     * Knuth-Morris-Pratt pattern matcher - Find all indexes
     * */
    int[] indexesOf(char[] text, char[] pattern);

}
