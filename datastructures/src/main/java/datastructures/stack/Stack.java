package datastructures.stack;

class Stack<T> {
    Object[] data;
    int size;

    public Stack(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void push(T value) {
        if (size == data.length) {
            throw new IllegalStateException("The stack is full");
        }

        data[size++] = value;
    }

    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }

        return data(size - 1);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("The stack is empty");
        }

        T value = data(size - 1);
        data[--size] = null;

        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private T data(int index) {
        return (T) data[index];
    }

}
