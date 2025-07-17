package datastructures.arraylist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;

class ArrayListTest {

    private ArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void testAppendAndGet() {
        list.append(1);
        list.append(2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testSizeAndCapacity() {
        assertEquals(0, list.size());
        assertTrue(list.capacity() >= 4);
    }

    @Test
    void testInsertAndPrepend() {
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
        list.append(1);
        list.append(2);
        list.append(3);

        list.delete(1); // remove "b"
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.size());

        list.remove(1);
        assertEquals(3, list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveThrowsIfNotFound() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> list.remove(1));
        assertTrue(e.getMessage().contains("not found"));
    }

    @Test
    void testPop() {
        list.append(42);
        Object value = list.pop();

        assertEquals(42, value);
        assertTrue(list.isEmpty());
    }

    @Test
    void testPopThrowsIfEmpty() {
        assertThrows(IllegalStateException.class, list::pop);
    }

    @Test
    void testFind() {
        list.append(1);
        list.append(2);

        assertEquals(1, list.find(2));
        assertEquals(-1, list.find(9));
    }

    @Test
    void testResize() {
        list.append(1);
        list.append(2);
        list.append(3); // triggers resize

        assertEquals(3, list.size());
        assertTrue(list.capacity() >= 4);
        assertEquals(3, list.get(2));
    }

    @Test
    void testSetAndGet() {
        list.append(1);
        list.set(0, 10);
        assertEquals(10, list.get(0));
    }

    @Test
    void testSetThrowsOnInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 999));
    }

    @Test
    void testGetThrowsOnInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testDeleteThrowsOnInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(0));
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    operands.push(operands.pop() + operands.pop());
                    break;
                case "-":
                    operands.push(operands.pop() - operands.pop());
                    break;
                case "*":
                    operands.push(operands.pop() * operands.pop());
                    break;
                case "/":
                    operands.push(operands.pop() / operands.pop());
                    break;
                default:
                    operands.push(Integer.getInteger(token));
            }
        }

        return operands.pop();
    }
}
