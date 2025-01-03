package records;

import agh.ics.oop.records.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void getXYTest(){
        //given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(-2,8);
        Vector2d v3 = new Vector2d(3,-9);

        //then
        assertEquals(0,v1.x());
        assertEquals(0,v1.y());
        assertEquals(-2,v2.x());
        assertEquals(8,v2.y());
        assertEquals(3,v3.x());
        assertEquals(-9,v3.y());
    }

    @Test
    public void equalsTest(){
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = v1;
        Vector2d v3 = new Vector2d(1 ,2);
        Vector2d v4 = new Vector2d(2,1);
        Vector2d v5 = new Vector2d(1,4);
        Object o1 = new Object();

        //then
        assertEquals(v1,v2);
        assertEquals(v1,v3);
        assertEquals(v3,v1);
        assertNotEquals(v1,v4);
        assertNotEquals(v1,v5);
        assertNotEquals(v1,o1);
    }

    @Test
    public void toStringTest(){
        //given
        Vector2d v1 = new Vector2d(4,-8);
        Vector2d v2 = new Vector2d(-2,0);
        //then
        assertEquals("(4,-8)",v1.toString());
        assertEquals("(-2,0)",v2.toString());
    }

    @Test
    public void precedesTest(){
        //given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(0,1);
        Vector2d v3 = new Vector2d(16,-1);
        Vector2d v4 = new Vector2d(-1,-1);
        Vector2d v5 = new Vector2d(2,1);
        //then
        assertTrue(v1.precedes(v1));
        assertTrue(v1.precedes(v2));
        assertFalse(v1.precedes(v3));
        assertFalse(v1.precedes(v4));
        assertTrue(v4.precedes(v1));
        assertTrue(v1.precedes(v5));
    }

    @Test
    public void followsTest(){
        //given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(0,1);
        Vector2d v3 = new Vector2d(16,-1);
        Vector2d v4 = new Vector2d(-1,-1);
        Vector2d v5 = new Vector2d(2,1);
        //then
        assertTrue(v1.follows(v1));
        assertTrue(v2.follows(v1));
        assertFalse(v1.follows(v2));
        assertFalse(v3.follows(v1));
        assertFalse(v4.follows(v1));
        assertTrue(v1.follows(v4));
        assertTrue(v2.follows(v1));
    }

    @Test
    public void upperRightTest(){
        //given
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(-2,-3);
        Vector2d v3 = new Vector2d(4,-4);
        Vector2d v4 = new Vector2d(1,5);
        //then
        assertEquals(v1,v1.upperRight(v1));
        assertEquals(v1,v2.upperRight(v1));
        assertEquals(new Vector2d(4,-3),v2.upperRight(v3));
        assertEquals(v4,v1.upperRight(v4));
        assertEquals(new Vector2d(4,5),v3.upperRight(v4));
        assertEquals(new Vector2d(4,1),v3.upperRight(v1));
    }

    @Test
    public void lowerLeftTest(){
        //given
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(-2,-3);
        Vector2d v3 = new Vector2d(4,-4);
        Vector2d v4 = new Vector2d(1,5);
        //then
        assertEquals(v1,v1.lowerLeft(v1));
        assertEquals(v2,v2.lowerLeft(v1));
        assertEquals(new Vector2d(-2,-4),v2.lowerLeft(v3));
        assertEquals(v1,v1.lowerLeft(v4));
        assertEquals(new Vector2d(1,-4),v3.lowerLeft(v4));
        assertEquals(new Vector2d(1,-4),v3.lowerLeft(v1));
    }

    @Test
    public void addTest(){
        //given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(2,-4);
        Vector2d v3 = new Vector2d(-8,20);
        Vector2d v4 = new Vector2d(1,1);
        //then
        assertEquals(v1,v1.add(v1));
        assertEquals(v2,v2.add(v1));
        assertEquals(new Vector2d(-6,16),v2.add(v3));
        assertEquals(new Vector2d(-7,21),v3.add(v4));
    }

    @Test
    public void subtractTest(){
        //given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(2,-4);
        Vector2d v3 = new Vector2d(-8,20);
        Vector2d v4 = new Vector2d(1,1);
        //then
        assertEquals(v1,v1.subtract(v1));
        assertEquals(new Vector2d(-2,4),v1.subtract(v2));
        assertEquals(new Vector2d(10,-24),v2.subtract(v3));
        assertEquals(new Vector2d(-9,19),v3.subtract(v4));
    }

    @Test
    public void oppositeTest(){
        //given
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(2,-4);
        Vector2d v3 = new Vector2d(-8,20);
        Vector2d v4 = new Vector2d(1,1);
        //then
        assertEquals(v1,v1.opposite());
        assertEquals(new Vector2d(-2,4),v2.opposite());
        assertEquals(new Vector2d(8,-20),v3.opposite());
        assertEquals(new Vector2d(-1,-1),v4.opposite());
    }
}
