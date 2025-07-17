package datastructures.queue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testEnqueueAndDequeue() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        assertEquals(10, q.dequeue());
        assertEquals(20, q.dequeue());
        assertEquals(30, q.dequeue());
    }

    @Test
    void testEmpty() {
        Queue<Integer> q = new Queue<>();
        assertTrue(q.empty());

        q.enqueue(1);
        assertFalse(q.empty());

        q.dequeue();
        assertTrue(q.empty());
    }

    @Test
    void testDequeueFromEmpty() {
        Queue<Integer> q = new Queue<>();
        assertThrows(IllegalStateException.class, q::dequeue);
    }
}
