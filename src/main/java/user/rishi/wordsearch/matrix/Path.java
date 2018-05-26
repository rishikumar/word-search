package user.rishi.wordsearch.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {
    private List<Position> positions;

    public Path(Position position) {
        List<Position> positions = new ArrayList<>();
        positions.add(position);
        this.positions = Collections.unmodifiableList(positions);
    }

    public Path(List<Position> positions) {
        this.positions = Collections.unmodifiableList(positions);
    }

    public static Path fromPath(Path path, Position position) {
        List<Position> existingPositions = path.getPositions();

        // build a new list of positions from the existing path and the new entry
        List<Position> positions = new ArrayList<>(existingPositions);
        positions.add(position);

        return new Path(positions);
    }

    public List<Position> getPositions() {
        return positions;
    }

    public int distanceTo(Position position) {
        // if the position list of this object is empty, then the distance can only be 1.
        if (positions == null || positions.size() == 0) {
            return 1;
        }

        // check if the inbound position is already in the list - if it is, its distance is zero...
        boolean isAlreadyInPath = positions.stream().anyMatch(position::equals);
        if (isAlreadyInPath) {
            return 0;
        }

        // determine the distance of the last position in the list to the new position.
        Position lastPosition = positions.get(positions.size() - 1);

        return lastPosition.distanceTo(position);
    }


}
