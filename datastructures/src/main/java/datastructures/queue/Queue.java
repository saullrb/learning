package datastructures.queue;

class Queue<T> {
    private Node<T> head;
    private Node<T> tail;

    public Queue() {
        head = tail = null;
    }

    public void enqueue(T value) {
        Node<T> node = new Node<>(value);

        if (tail != null) {
            tail.setNext(node);
        }

        tail = node;

        if (head == null) {
            head = tail;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T value = head.getValue();
        head = head.getNext();

        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }

}

class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}
