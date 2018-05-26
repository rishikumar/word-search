package user.rishi.wordsearch.matrix;

import org.junit.jupiter.api.Test;
import user.rishi.wordsearch.exception.InvalidDataException;

import static org.junit.jupiter.api.Assertions.*;


class CharMatrixTest {

    @Test
    void testEmptyMatrix() {
        char[][] data = {};

        CharMatrix matrix = new CharMatrix(data);
        assertFalse(matrix.isWordFound(null));
        assertFalse(matrix.isWordFound("blah"));
    }

    @Test
    void testInvalidMatrixData() {
        final char[][] data1 = {{'A', 'B'}};
        assertThrows(InvalidDataException.class, () -> new CharMatrix(data1));

        // test mismatch in the number of columns - this results in an invalid matrix
        final char[][] data2 = new char[][]{
            {'a', 'b'},
            {'a', 'b', 'c'}
        };

        assertThrows(InvalidDataException.class, () -> new CharMatrix(data2));
    }

    @Test
    void testMissingChars() {
        char[][] data = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };

        CharMatrix matrix = new CharMatrix(data);
        assertFalse(matrix.isWordFound("z"));
        assertFalse(matrix.isWordFound("zzzz"));
        assertFalse(matrix.isWordFound("wxyz"));
    }

    @Test
    void testNoDuplicateChars() {
        char[][] data = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'}
        };

        CharMatrix matrix = new CharMatrix(data);

        // left to right
        assertTrue(matrix.isWordFound("def"));

        // right to left
        assertTrue(matrix.isWordFound("cba"));

        // top to bottom
        assertTrue(matrix.isWordFound("cfi"));

        // bottom to top
        assertTrue(matrix.isWordFound("gda"));

        // mixed directions
        assertTrue(matrix.isWordFound("ade"));
        assertTrue(matrix.isWordFound("bed"));
        assertTrue(matrix.isWordFound("cfe"));
        assertTrue(matrix.isWordFound("befc"));
        assertTrue(matrix.isWordFound("feda"));
    }

    @Test
    void testDuplicateCharsInMatrix() {
        char[][] data = {
            {'a', 'b', 'c'},
            {'b', 'e', 'f'},
            {'e', 'f', 'j'}
        };

        CharMatrix matrix = new CharMatrix(data);

        // found
        assertTrue(matrix.isWordFound("abe"));
        assertTrue(matrix.isWordFound("bef"));
        assertTrue(matrix.isWordFound("fjf"));
        assertTrue(matrix.isWordFound("febe"));

        // not found
        assertFalse(matrix.isWordFound("aej"));
        assertFalse(matrix.isWordFound("jfbdbcz"));
    }

    @Test
    void testSameConsecutiveChars() {
        char[][] data = {
            {'a', 'b', 'c'},
            {'b', 'i', 'i'},
            {'e', 'i', 'j'}
        };

        CharMatrix matrix = new CharMatrix(data);

        // found
        assertTrue(matrix.isWordFound("biic"));
        assertTrue(matrix.isWordFound("beiii"));

        // not found
        assertFalse(matrix.isWordFound("biib"));
    }
}
