package user.rishi.wordsearch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import user.rishi.wordsearch.matrix.CharMatrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordSearch {
    private final static Log logger = LogFactory.getLog(WordSearch.class);

    private CharMatrix matrix;

    public WordSearch(char[][] data) {
        this.matrix = new CharMatrix(data);
    }

    public Stream<String> findMatchingWords(List<String> words) {
        if (words == null) {
            return Stream.empty();
        }

        // filter out the words that match
        return words.stream().filter(matrix::matchByMatrixDistance);
    }

    public static void main(String... args) {
        char[][] data = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'a', 'e', 'i'}
        };

        WordSearch wordSearch = new WordSearch(data);
        logger.info("Input CharMatrix: " + wordSearch.matrix);

        List<String> inputWords = Arrays.asList("efi", "edh", "fii", "fih", "ghebc", "z", "fiha");
        logger.info("Input words: " + inputWords);

        List<String> matchingWords = wordSearch.findMatchingWords(inputWords).collect(Collectors.toList());
        logger.info("Matching words: " + matchingWords);
    }

}
