package user.rishi.wordsearch.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TestFunctional {

    @Test
    void testToFlatCharStream() {
        char[][] data = {
            {'a', 'b'},
            {'c', 'd'}
        };

        Stream<Character> stream = Functional.toFlatCharStream(data);

        List<Character> l = stream.collect(Collectors.toList());

        assertEquals(4, l.size());
    }

}
