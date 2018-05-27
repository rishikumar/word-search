package user.rishi.wordsearch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Functional {

    /***
     * Converts a 2D character array into a flat sequence of Characters.
     * @param data the input 2D array
     * @return A Stream<Character> objects representing each element in the array.
     */
    public static Stream<Character> toFlatCharStream(char[][] data) {
        return Arrays.stream(data).flatMap(row -> {
            List<Character> records = new ArrayList<>();

            for (char ch : row) {
                records.add(ch);
            }

            return records.stream();
        });
    }
}
