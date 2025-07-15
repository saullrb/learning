package ds;

public class ArrayList<T> {
    private Object[] data;
    private int size = 0;
    private int capacity = 4;

    public ArrayList() {
        this(4);
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        checkIndex(index);

        return data(index);
    }

    public void set(int index, T item) {
        checkIndex(index);

        data[index] = item;
    }

    public void append(T item) {
        ensureCapacity();

        data[size++] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Can't pop on an empty ArrayList");
        }

        T item = data(--size);
        data[size] = null;

        return item;
    }

    public void insert(int index, T item) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        ensureCapacity();

        size++;

        for (int i = size - 2; i >= index; i--) {
            data[i + 1] = data[i];
        }

        set(index, item);
    }

    public void prepend(T item) {
        insert(0, item);
    }

    public void delete(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null;
    }

    public int find(T item) {
        for (int i = 0; i < size; i++) {
            if (data[i] == item) {
                return i;
            }

        }

        return -1;
    }

    public void remove(T item) {
        int index = find(item);
        if (index == -1) {
            throw new IllegalArgumentException("Item not found on the list");
        }

        delete(index);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    private void ensureCapacity() {
        if (size == capacity) {
            resize();
        }
    }

    private void resize() {
        capacity *= 2;
        Object[] newList = new Object[capacity];

        for (int i = 0; i < size; i++) {
            newList[i] = data[i];
        }

        data = newList;
    }

    @SuppressWarnings("unchecked")
    private T data(int index) {
        return (T) data[index];
    }
}
