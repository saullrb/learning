package ds.linkedlist;

import java.security.InvalidParameterException;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public T front() {
        if (head == null) {
            return null;
        }

        return head.getValue();
    }

    public boolean empty() {
        return size == 0;
    }

    public T back() {
        if (tail == null) {
            return null;
        }

        return tail.getValue();
    }

    public void pushFront(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }

        node.setNext(head);
        head = node;
        size++;
    }

    public void pushBack(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
            size++;
            return;
        }

        tail.setNext(node);
        tail = node;
        size++;
    }

    public T popFront() {
        checkListEmpty();

        Node<T> node = head;
        head = head.getNext();
        size--;

        return node.getValue();
    }

    public T popBack() {
        checkListEmpty();

        Node<T> node = tail;

        if (size == 1) {
            head = null;
            tail = null;
            size--;

            return node.getValue();
        }

        Node<T> newTail = head;

        while (newTail.getNext() != tail) {
            newTail = newTail.getNext();
        }

        tail = newTail;
        size--;

        return node.getValue();
    }

    public T valueAt(int index) {
        checkListEmpty();

        Node<T> currentNode = head;
        int count = 0;

        while (count < index) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }

            currentNode = currentNode.getNext();
            count++;
        }

        return currentNode.getValue();
    }

    public void insert(int index, T value) {
        checkListEmpty();

        Node<T> node = new Node<>(value);
        if (index == 0) {
            pushFront(value);
            return;
        }

        if (index == size - 1) {
            pushBack(value);
            return;
        }

        Node<T> currentNode = head;

        for (int i = 0; i < index - 1; i++) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }
            currentNode = currentNode.getNext();
        }

        node.setNext(currentNode.getNext());
        currentNode.setNext(node);

        size++;
    }

    public void erase(int index) {
        checkListEmpty();

        if (index == 0) {
            popFront();
            return;
        }

        Node<T> currentNode = head;
        for (int i = 0; i < index - 1; i++) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }

            currentNode = currentNode.getNext();
        }

        currentNode.setNext(currentNode.getNext().getNext());

        size--;
    }

    public void removeValue(T value) {
        checkListEmpty();

        if (value.equals(head.getValue())) {
            popFront();
            return;
        }

        Node<T> currentNode = head;
        while (currentNode.getNext() != null && currentNode.getNext().getValue().equals(value)) {

            currentNode = currentNode.getNext();
        }

        if (currentNode.getNext() == null) {
            throw new InvalidParameterException("Value " + value + " not found in the list");
        }

        currentNode.setNext(currentNode.getNext().getNext());
        size--;
    }

    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }

        Node<T> previousNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode = head.getNext();

        head = tail;
        tail = currentNode;

        while (currentNode != null) {
            nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);

            previousNode = currentNode;
            currentNode = nextNode;
        }
    }

    private void checkListEmpty() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
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
