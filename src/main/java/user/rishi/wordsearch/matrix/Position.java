package user.rishi.wordsearch.matrix;

import java.util.Objects;

/**
 * Represents the row number and column coordinates of an element in the matrix
 */
public class Position {
    private int row;
    private int col;

    /**
     * Constructor that builds a position from row and column coordinates
     * @param row a zero-based row number
     * @param col a zero based column number
     */
    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Computes the distance between two positions.
     * @param p the position to compare against this object
     * @return the distance represented as an int
     */
    int distanceTo(Position p) {
        // distance is the sum of the absolute values of the row and column differences
        int rowDistance = Math.abs(this.row - p.row);
        int colDistance = Math.abs(this.col - p.col);

        return rowDistance + colDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
