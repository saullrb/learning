package datastructures.stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void testPushAndTop() {
        Stack<Integer> stack = new Stack<>(3);
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.top());
    }

    @Test
    void testPop() {
        Stack<String> stack = new Stack<>(2);
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.pop());
        assertEquals("a", stack.top());
    }

    @Test
    void testEmpty() {
        Stack<Integer> stack = new Stack<>(1);
        assertTrue(stack.isEmpty());
        stack.push(10);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testOverflow() {
        Stack<Integer> stack = new Stack<>(1);
        stack.push(1);
        assertThrows(IllegalStateException.class, () -> stack.push(2));
    }

    @Test
    void testUnderflow() {
        Stack<Integer> stack = new Stack<>(1);
        assertThrows(IllegalStateException.class, stack::pop);
        assertThrows(IllegalStateException.class, stack::top);
    }
}
