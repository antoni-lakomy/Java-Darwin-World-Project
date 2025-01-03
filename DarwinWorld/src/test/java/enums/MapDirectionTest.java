package enums;

import agh.ics.oop.enums.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    void rotateTest() {
        //given
        MapDirection v0 = MapDirection.NORTH;

        //when
        MapDirection v1 = v0.rotate(0);
        MapDirection v2 = v0.rotate(1);
        MapDirection v3 = v0.rotate(2);
        MapDirection v4 = v0.rotate(3);
        MapDirection v5 = v0.rotate(4);
        MapDirection v6 = v0.rotate(5);
        MapDirection v7 = v0.rotate(6);
        MapDirection v8 = v0.rotate(7);

        MapDirection v9 = v0.rotate(8);

        //then
        assertEquals(MapDirection.NORTH,v1);
        assertEquals(MapDirection.NORTH_EAST,v2);
        assertEquals(MapDirection.EAST,v3);
        assertEquals(MapDirection.SOUTH_EAST,v4);
        assertEquals(MapDirection.SOUTH,v5);
        assertEquals(MapDirection.SOUTH_WEST,v6);
        assertEquals(MapDirection.WEST,v7);
        assertEquals(MapDirection.NORTH_WEST,v8);

        assertEquals(MapDirection.NORTH,v9);


    }

}
