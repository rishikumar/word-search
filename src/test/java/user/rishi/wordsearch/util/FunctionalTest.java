package user.rishi.wordsearch.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FunctionalTest {

    @Test
    void testToFlatCharStream() {
        Character[][] data = {
            {'a', 'b'},
            {'c', 'd'}
        };

        Stream<Character> stream = Functional.toFlatStream(data);

        List<Character> l = stream.collect(Collectors.toList());

        assertEquals(4, l.size());
    }

}
