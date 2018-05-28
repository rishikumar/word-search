package user.rishi.wordsearch.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an ordered list of elements in the Matrix. Used by search routines to capture routes in the matrix.
 * This is an immutable structure. Adding a new element to a path via fromPath results in a new path object.
 */
class Path {
    private List<Position> positions;

    /**
     * Constructor for creating a new path object with an initial starting point (one position)
     * @param position a location in the matrix
     */
    Path(Position position) {
        List<Position> positions = new ArrayList<>();
        positions.add(position);
        this.positions = Collections.unmodifiableList(positions);
    }

    /**
     * Constructor that builds a path from an existing list of positions
     * @param positions a sequence of positions
     */
    Path(List<Position> positions) {
        this.positions = Collections.unmodifiableList(positions);
    }

    /**
     * Builds a new path object from an existing path and a new position.
     * @param path an existing path
     * @param position a new position to add to the path
     * @return a new path object which contains the existing path's positions and the new position
     */
    static Path fromPath(Path path, Position position) {
        List<Position> existingPositions = path.getPositions();

        // build a new list of positions from the existing path and the new entry
        List<Position> positions = new ArrayList<>(existingPositions);
        positions.add(position);

        return new Path(positions);
    }

    List<Position> getPositions() {
        return positions;
    }

    /**
     * Computes the distance between a path and the new position. If the position already exists in the path,
     * its distance is 0 since its already part of the path. Otherwise, it checks the position of the last item
     * in the path and compares it against the new position.
     * @param position the new position to compare against the path (this)
     * @return an integer value representing the distance between the path and the position.
     */
    int distanceTo(Position position) {
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
