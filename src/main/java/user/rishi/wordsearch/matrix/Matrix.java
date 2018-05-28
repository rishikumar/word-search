package user.rishi.wordsearch.matrix;

import user.rishi.wordsearch.exception.InvalidDataException;
import user.rishi.wordsearch.util.Functional;

import java.util.*;
import java.util.function.Predicate;


public class Matrix<T> {
    private T[][] data;

    // This map is used to keep track of the positions of each letter in the matrix. this is used to query the matrix
    // for the positions of letters
    Map<T, List<Position>> elementIndex;

    public Matrix(T[][] data) {
        this(data, null);
    }

    public Matrix(T[][] data, Predicate<T> elementValidator) {

        this.data = data;

        validateData(data, elementValidator);
        this.elementIndex = buildElementIndex(data);
    }

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

        if (elementValidator != null) {
            boolean allMatchPattern = Functional.toFlatStream(data).allMatch(elementValidator);

            if (!allMatchPattern) {
                throw new InvalidDataException("Invalid element passed into the Matrix.");
            }
        }
    }

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
