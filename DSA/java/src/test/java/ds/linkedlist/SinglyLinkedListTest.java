package ds.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class SimplyLinkedListTest {
    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        assertEquals(null, list.front());
        assertEquals(null, list.back());
    }

    @Test
    void testPushFront() {
        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        list.pushFront(5);

        assertEquals(5, list.size());
        assertEquals(5, list.front());
        assertEquals(1, list.back());
    }

    @Test
    void testPushBack() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);

        assertEquals(5, list.size());
        assertEquals(1, list.front());
        assertEquals(5, list.back());
    }

    @Test
    void testPopFrontAndBack() {
        list.pushBack(1);

        assertEquals(1, list.size());
        assertEquals(1, list.front());
        assertEquals(1, list.back());

        Integer lastValue = list.popBack();
        assertEquals(0, list.size());
        assertEquals(null, list.front());
        assertEquals(null, list.back());
        assertEquals(1, lastValue);

        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);

        assertEquals(5, list.size());
        assertEquals(1, list.front());
        assertEquals(5, list.back());

        Integer firstTalue = list.popFront();
        lastValue = list.popBack();

        assertEquals(1, firstTalue);
        assertEquals(5, lastValue);
        assertEquals(3, list.size());
        assertEquals(2, list.front());
        assertEquals(4, list.back());
    }

    @Test
    void testValueAt() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);

        Integer value = list.valueAt(3);
        assertEquals(4, value);
    }

    @Test
    void testInsert() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);
        list.insert(3, 9);
        assertEquals(9, list.valueAt(3));
        assertEquals(6, list.size());
    }

    @Test
    void testErase() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);
        list.erase(3);
        assertEquals(5, list.valueAt(3));
        assertEquals(4, list.size());
    }

    @Test
    void testRemoveValue() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);
        list.removeValue(4);
        assertEquals(5, list.valueAt(3));
        assertEquals(4, list.size());
    }

    @Test
    void testReverse() {
        list.pushBack(1);
        list.pushBack(2);
        list.pushBack(3);
        list.pushBack(4);
        list.pushBack(5);
        list.reverse();
        assertEquals(5, list.valueAt(0));
        assertEquals(4, list.valueAt(1));
        assertEquals(3, list.valueAt(2));
        assertEquals(2, list.valueAt(3));
        assertEquals(1, list.valueAt(4));
        assertEquals(5, list.front());
        assertEquals(1, list.back());
    }

    @AfterEach
    void tearDown() {
        list = null;
    }
}
