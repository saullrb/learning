package ds;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void testAppendAndGet() {
        ArrayList list = new ArrayList();
        list.append(1);
        list.append(2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testSizeAndCapacity() {
        ArrayList list = new ArrayList();
        assertEquals(0, list.size());
        assertTrue(list.capacity() >= 4);
    }

    @Test
    void testInsertAndPrepend() {
        ArrayList list = new ArrayList();
        list.append(1);
        list.append(3);
        list.insert(1, 2); // between 1 and 3
        list.prepend(0); // at index 0

        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));
    }

    @Test
    void testDeleteAndRemove() {
        ArrayList list = new ArrayList();
        list.append("a");
        list.append("b");
        list.append("c");

        list.delete(1); // remove "b"
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals(2, list.size());

        list.remove("a");
        assertEquals("c", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveThrowsIfNotFound() {
        ArrayList list = new ArrayList();
        Exception e = assertThrows(IllegalArgumentException.class, () -> list.remove("x"));
        assertTrue(e.getMessage().contains("not found"));
    }

    @Test
    void testPop() {
        ArrayList list = new ArrayList();
        list.append(42);
        Object value = list.pop();

        assertEquals(42, value);
        assertTrue(list.isEmpty());
    }

    @Test
    void testPopThrowsIfEmpty() {
        ArrayList list = new ArrayList();
        assertThrows(IllegalStateException.class, list::pop);
    }

    @Test
    void testFind() {
        ArrayList list = new ArrayList();
        list.append("x");
        list.append("y");

        assertEquals(1, list.find("y"));
        assertEquals(-1, list.find("z"));
    }

    @Test
    void testResize() {
        ArrayList list = new ArrayList(2);
        list.append(1);
        list.append(2);
        list.append(3); // triggers resize

        assertEquals(3, list.size());
        assertTrue(list.capacity() >= 4);
        assertEquals(3, list.get(2));
    }

    @Test
    void testSetAndGet() {
        ArrayList list = new ArrayList();
        list.append(1);
        list.set(0, 10);
        assertEquals(10, list.get(0));
    }

    @Test
    void testSetThrowsOnInvalidIndex() {
        ArrayList list = new ArrayList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "fail"));
    }

    @Test
    void testGetThrowsOnInvalidIndex() {
        ArrayList list = new ArrayList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testDeleteThrowsOnInvalidIndex() {
        ArrayList list = new ArrayList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(0));
    }
}
