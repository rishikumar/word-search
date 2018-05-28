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
    public static <T> Stream<T> toFlatStream(T[][] data) {
        return Arrays.stream(data).flatMap(row -> {
            List<T> records = new ArrayList<>(Arrays.asList(row));
            return records.stream();
        });
    }

    public static Stream<Character> toCharacterStream(String data) {
        return data.chars().mapToObj(c -> (char) c);
    }

}
