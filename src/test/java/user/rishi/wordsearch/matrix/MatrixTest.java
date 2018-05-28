package user.rishi.wordsearch.matrix;

import org.junit.jupiter.api.Test;
import user.rishi.wordsearch.exception.InvalidDataException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void testDataTypes() {
        // test integer data
        Integer[][] intData = {{1, 2}, {3, 4}};
        Matrix<Integer> intMatrix = new Matrix<>(intData);

        assertEquals(4, intMatrix.elementIndex.size());
        assertTrue(Stream.of(1, 2, 3, 4).allMatch(i -> intMatrix.elementIndex.containsKey(i)));
        assertTrue(Stream.of(5, 6, 7, 8).noneMatch(i -> intMatrix.elementIndex.containsKey(i)));

        // test strings
        String[][] stringData = {{"one", "two"}, {"three", "four"}};
        Matrix<String> stringMatrix = new Matrix<>(stringData);

        assertEquals(4, stringMatrix.elementIndex.size());
        assertTrue(Stream.of("one", "two", "three", "four").allMatch(i -> stringMatrix.elementIndex.containsKey(i)));
        assertTrue(Stream.of("five", "six", "seven", "eight").noneMatch(i -> stringMatrix.elementIndex.containsKey(i)));
    }

    @Test
    void testValidator() {
        Integer[][] data = {{1, 2}, {3, 4}};
        assertDoesNotThrow(() -> new Matrix<>(data, i -> i < 5));
        assertThrows(InvalidDataException.class, () -> new Matrix<>(data, i -> i < 3));
    }

    @Test
    void testDimensionMismatch() {
        Integer[][] data = {{1, 2}, {1, 2, 3}};
        assertThrows(InvalidDataException.class, () -> new Matrix<>(data));
    }

    @Test
    void testMultipleElementsInSameKey() {
        Integer[][] data = {{1,2,3,4}, {1,2,3,4}, {1,2,3,4}, {1,2,3,4}};
        Matrix<Integer> matrix = new Matrix<>(data);
        assertTrue(Stream.of(1,2,3,4)
            .map(i -> matrix.elementIndex.get(i))
            .allMatch(positionList -> positionList.size() == 4));
    }
}
