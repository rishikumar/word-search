package user.rishi.wordsearch;

import user.rishi.wordsearch.matrix.CharMatrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WordSearch {

    private CharMatrix matrix;

    public WordSearch(char[][] data) {
        this.matrix = new CharMatrix(data);
    }

    public Stream<String> findMatchingWords(List<String> words) {
        if (words == null) {
            return Stream.empty();
        }

        // filter out the words that match
        return words.stream().filter(this.matrix::isWordFound);
    }

    public static void main(String[] args) {
        char[][] data = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'a', 'e', 'i'}
        };

        List<String> inputWords = Arrays.asList("efi", "edh", "fii", "fih", "ghebc", "z");

        WordSearch wordSearch = new WordSearch(data);
        wordSearch.findMatchingWords(inputWords).forEach(System.out::println);
    }

}
