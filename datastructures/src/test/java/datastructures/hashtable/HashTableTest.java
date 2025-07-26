package datastructures.hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

  @Test
  void testAddAndGet() {
    HashTable<String, Integer> map = new HashTable<>();
    map.add("one", 1);
    map.add("two", 2);
    map.add("three", 3);

    assertEquals(1, map.get("one"));
    assertEquals(2, map.get("two"));
    assertEquals(3, map.get("three"));
  }

  @Test
  void testOverwriteValue() {
    HashTable<String, String> map = new HashTable<>();
    map.add("key", "A");
    map.add("key", "B");
    assertEquals("B", map.get("key"));
  }

  @Test
  void testExists() {
    HashTable<String, String> map = new HashTable<>();
    map.add("x", "val");
    assertTrue(map.exists("x"));
    assertFalse(map.exists("y"));
  }

  @Test
  void testRemove() {
    HashTable<String, String> map = new HashTable<>();
    map.add("foo", "bar");
    assertTrue(map.exists("foo"));

    map.remove("foo");
    assertFalse(map.exists("foo"));
    assertThrows(IllegalArgumentException.class, () -> map.get("foo"));
  }

  @Test
  void testLinearProbingCollision() {
    HashTable<Integer, String> map = new HashTable<>();

    // Force hash collision by custom keys with same hash
    Integer key1 = 16;
    Integer key2 = 0;

    map.add(key1, "a");
    map.add(key2, "b");

    assertEquals("a", map.get(key1));
    assertEquals("b", map.get(key2));
  }

  @Test
  void testRehashing() {
    HashTable<Integer, String> map = new HashTable<>();

    for (int i = 0; i < 20; i++) {
      map.add(i, "val" + i);
    }

    for (int i = 0; i < 20; i++) {
      assertEquals("val" + i, map.get(i));
    }
  }
}
