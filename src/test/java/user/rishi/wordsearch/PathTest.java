package user.rishi.wordsearch;

import org.junit.jupiter.api.Test;
import user.rishi.wordsearch.matrix.Path;
import user.rishi.wordsearch.matrix.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PathTest {

    @Test
    void testImmutablePath() {
        Position p1 = new Position(1, 1);

        Path path = new Path(p1);

        List<Position> positions = path.getPositions();

        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            positions.add(new Position(1, 3));
        });

        assertEquals(UnsupportedOperationException.class, exception.getClass());
    }

    @Test
    void testPathConstruction() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 2);
        Position pos3 = new Position(3, 3);

        Path path1 = new Path(pos1);
        assertEquals(1, path1.getPositions().size());

        Path path2 = Path.fromPath(path1, pos2);
        assertEquals(2, path2.getPositions().size());

        Path path3 = Path.fromPath(path2, pos3);
        assertEquals(3, path3.getPositions().size());

        List<Position> positions = path3.getPositions();
        assertEquals(pos1, positions.get(0));
        assertEquals(pos2, positions.get(1));
        assertEquals(pos3, positions.get(2));
    }

    @Test
    void testDistance() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);
        Position pos3 = new Position(2, 2);

        List<Position> positions = new ArrayList<>();
        positions.add(pos1);
        positions.add(pos2);
        positions.add(pos3);

        Path path = new Path(positions);

        // test an item already in the path - both the exact object and another object equal to it
        assertEquals(0, path.distanceTo(pos2));
        assertEquals(0, path.distanceTo(new Position(2, 1)));

        // test items that aren't in the path to confirm their distance
        assertEquals(1, path.distanceTo(new Position(3, 2)));
        assertEquals(1, path.distanceTo(new Position(2, 3)));
        assertEquals(2, path.distanceTo(new Position(3, 3)));
    }


}
