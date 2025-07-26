package datastructures.hashtable;

class HashTable<K, V> {
  private final float MAX_LOAD_FACTOR = 0.7F;
  private int capacity = 16;
  private int size = 0;
  private Entry<K, V>[] table;

  @SuppressWarnings("unchecked")
  public HashTable() {
    table = (Entry<K, V>[]) new Entry[capacity];
  }

  public void add(K key, V value) {
    handleTableLoad();

    int hash = hash(key);

    while (table[hash] != null) {
      if (!table[hash].deleted && table[hash].key.equals(key)) {
        table[hash].value = value;
        return;
      }

      hash = (hash + 1) % capacity;
    }

    table[hash] = (Entry<K, V>) new Entry<>(key, value);
    size++;
  }

  public boolean exists(K key) {
    int hash = hash(key);

    while (table[hash] != null) {
      if (!table[hash].deleted && table[hash].key.equals(key)) {
        return true;
      }

      hash = (hash + 1) % capacity;
    }

    return false;
  }

  public V get(K key) {
    int hash = hash(key);

    while (table[hash] != null && !table[hash].deleted) {
      if (table[hash].key.equals(key)) {
        return table[hash].value;
      }

      hash = (hash + 1) % capacity;
    }

    throw new IllegalArgumentException(String.format("No item with key '%s'", key));
  }

  public void remove(K key) {
    int hash = hash(key);

    while (table[hash] != null) {
      if (table[hash].key.equals(key)) {
        table[hash].setDeleted(true);
        return;
      }

      hash = (hash + 1) % capacity;
    }

    throw new IllegalArgumentException(String.format("No item with key '%s'", key));
  }

  private int hash(K key) {
    // Clears negative sign
    return (key.hashCode() & 0x7fffffff) % capacity;
  }

  private int hash(K key, int m) {
    // Clears negative sign
    return (key.hashCode() & 0x7fffffff) % m;
  }

  private void handleTableLoad() {
    float loadFactor = size / (float) capacity;

    if (loadFactor >= MAX_LOAD_FACTOR) {
      size = 0;
      capacity *= 2;

      @SuppressWarnings("unchecked")
      Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[capacity];

      for (Entry<K, V> entry : table) {
        if (entry != null && !entry.deleted) {
          int hash = hash(entry.key, capacity);

          while (newTable[hash] != null) {
            hash = (hash + 1) % capacity;
          }

          newTable[hash] = entry;
        }
      }

      table = newTable;
    }
  }

}

class Entry<K, V> {
  K key;
  V value;
  boolean deleted;

  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}
