package user.rishi.wordsearch.matrix;

import java.util.Objects;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public int distanceTo(Position p) {
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
