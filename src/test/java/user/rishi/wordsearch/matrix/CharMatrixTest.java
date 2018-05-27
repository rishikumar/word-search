package user.rishi.wordsearch.matrix;

import org.junit.jupiter.api.Test;
import user.rishi.wordsearch.exception.InvalidDataException;

import static org.junit.jupiter.api.Assertions.*;


class CharMatrixTest {

    @Test
    void testEmptyMatrix() {
        char[][] data = {};

        CharMatrix matrix = new CharMatrix(data);
        assertFalse(matrix.matchByMatrixDistance(null));
        assertFalse(matrix.matchByMatrixDistance("blah"));
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
        assertFalse(matrix.matchByMatrixDistance("z"));
        assertFalse(matrix.matchByMatrixDistance("zzzz"));
        assertFalse(matrix.matchByMatrixDistance("wxyz"));
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
        assertTrue(matrix.matchByMatrixDistance("def"));

        // right to left
        assertTrue(matrix.matchByMatrixDistance("cba"));

        // top to bottom
        assertTrue(matrix.matchByMatrixDistance("cfi"));

        // bottom to top
        assertTrue(matrix.matchByMatrixDistance("gda"));

        // mixed directions
        assertTrue(matrix.matchByMatrixDistance("ade"));
        assertTrue(matrix.matchByMatrixDistance("bed"));
        assertTrue(matrix.matchByMatrixDistance("cfe"));
        assertTrue(matrix.matchByMatrixDistance("befc"));
        assertTrue(matrix.matchByMatrixDistance("feda"));
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
        assertTrue(matrix.matchByMatrixDistance("abe"));
        assertTrue(matrix.matchByMatrixDistance("bef"));
        assertTrue(matrix.matchByMatrixDistance("fjf"));
        assertTrue(matrix.matchByMatrixDistance("febe"));

        // not found
        assertFalse(matrix.matchByMatrixDistance("aej"));
        assertFalse(matrix.matchByMatrixDistance("jfbdbcz"));
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
        assertTrue(matrix.matchByMatrixDistance("biic"));
        assertTrue(matrix.matchByMatrixDistance("beiii"));

        // not found
        assertFalse(matrix.matchByMatrixDistance("biib"));
    }
}
