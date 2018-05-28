package user.rishi.wordsearch.matrix;

import user.rishi.wordsearch.exception.InvalidDataException;
import user.rishi.wordsearch.util.Functional;

import java.util.*;
import java.util.function.Predicate;

/**
 * A two dimensional matrix (m x n) that can support any type of java object as its elements.
 * It checks to see that each of rows of the input data have the same length and has the ability to run a custom
 * validator on each element.
 * @param <T>
 */
public class Matrix<T> {
    private T[][] data;

    // This map is used to keep track of the positions of each letter in the matrix. this is used to query the matrix
    // for the positions of letters
    // key  : an element of the input data
    // value: List of Position objects, where each Position keeps track a location of the key in data.
    Map<T, List<Position>> elementIndex;

    /**
     * A constructor that creates a matrix that does not enforce element validation
     * @param data two dimensional array
     */
    Matrix(T[][] data) {
        this(data, null);
    }

    /**
     * A constructor that takes in an optional element validator
     * @param data two dimensional array
     * @param elementValidator predicate used to check the validity of each element.
     */
    Matrix(T[][] data, Predicate<T> elementValidator) {
        this.data = data;

        validateData(data, elementValidator);
        this.elementIndex = buildElementIndex(data);
    }

    /**
     * Performs validation in the input data. It enforces two things:
     * 1) The input rows all have the same number of columns
     * 2) Each element confirms to the specified elementValidator, if supplied
     * @param two dimensional array
     * @param elementValidator predicate used to check the validity of each element.
     */
    private void validateData(T[][] data, Predicate<T> elementValidator) {
        if (data == null || data.length == 0) {
            return;
        }

        int numColumns = data[0].length;

        // check for mismatch in number of columns on each row
        boolean isMismatch = Arrays.stream(data).anyMatch(a -> a.length != numColumns);
        if (isMismatch) {
            throw new InvalidDataException("Dimension mismatch: Found an inconsistent number of columns");
        }

        // execute the elementValidator, if one is supplied
        if (elementValidator != null) {
            boolean allMatchPattern = Functional.toFlatStream(data).allMatch(elementValidator);

            if (!allMatchPattern) {
                throw new InvalidDataException("Invalid element passed into the Matrix.");
            }
        }
    }

    /**
     * Creates an internal map - key: data element, value: List<Position> objects, representing the locations of that
     * element in the matrix.
     * @param data two dimensional array
     * @return the map of elements and their positions
     */
    private Map<T, List<Position>> buildElementIndex(T[][] data) {
        Map<T, List<Position>> result = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                Position position = new Position(i, j);
                result.computeIfAbsent(data[i][j], k -> new ArrayList<>()).add(position);
            }
        }

        return result;
    }


    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("\n");

        for (T[] row : data) {
            for (T el : row) {
                msg.append(String.format("%5s", el));
            }
            msg.append("\n");
        }

        return msg.toString();
    }
}
