package user.rishi.wordsearch.matrix;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import user.rishi.wordsearch.exception.InvalidDataException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixTest {
    @Test
    void testDataTypes() {
        throw new NotImplementedException();
    }

    @Test
    void testDimensionMismatch() {
        Integer[][] data = {{1, 2}, {1,2,3}};
        assertThrows(InvalidDataException.class, () -> new Matrix<>(data));
    }

    @Test
    void testMultipleKeys() {
        // test adding multiple elements which has to different keys
        throw new NotImplementedException();
    }

    @Test
    void testMultipleElementsInSameKey() {
        // test adding multiple elements which hash to the same key
        throw new NotImplementedException();
    }
}
