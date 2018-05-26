package user.rishi.wordsearch.matrix;

public class Position {
    private int row;
    private int col;

    Position(int row, int col) {
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

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Position p = (Position) o;

        return this.row == p.row && this.col == p.col;
    }
}
