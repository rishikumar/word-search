package user.rishi.wordsearch.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class PositionTest {

    @Test
    void testEquality() {

        Position p1 = new Position(0, 0);

        assertNotEquals(p1, new Object());
        assertNotEquals(p1, new Position(1, 1));

        assertEquals(p1, p1);

        Position p2 = new Position(0, 0);
        assertEquals(p1, p2);

        Position p3 = new Position(0, 1);
        assertNotEquals(p1, p3);
    }

    @Test
    void testDistance() {
        Position p1 = new Position(0,1);
        Position p2 = new Position(1, 1);

        // test that same distance is produced in both directions
        assertEquals(1, p1.distanceTo(p2));
        assertEquals(1, p2.distanceTo(p1));

        Position p3 = new Position(1, 2);
        assertEquals(2, p1.distanceTo(p3));
        assertEquals(1, p2.distanceTo(p3));
    }

}
