package Task2;

import java.util.Comparator;

public class MyLinkedList<T> {
    public static class MyLinkedListException extends Exception {
        public MyLinkedListException(String message) {
            super(message);
        }
    }

    protected class MyLinkedListNode {
        public T value;
        public MyLinkedListNode next = null;

        public MyLinkedListNode(T value, MyLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public MyLinkedListNode(T value) {
            this(value, null);
        }
    }

    private MyLinkedListNode head = null;
    private MyLinkedListNode tail = null;
    private int size = 0;


    public MyLinkedList(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    public void bubbleSort(Comparator<T> c) throws MyLinkedListException {
        if (size < 2) {
            return;
        }

        boolean isSorted = false;
        MyLinkedListNode prev, next, node1, node2;
        while (!isSorted) {
            isSorted = true;

            prev = null;
            node1 = head;
            node2 = node1.next;
            next = node2.next;

            for (int i = 0; i < size() - 1; i++) {
                if (c.compare(node1.value, node2.value) > 0) {
                    swapNodes(prev, node1, node2, next);
                    isSorted = false;
                }
                if (next != null) {
                    prev = prev == null ? head : prev.next;
                    node1 = prev.next;
                    node2 = node1.next;
                    next = next.next;
                }
            }
        }
    }

    public T[] toArray() throws MyLinkedListException {
        T[] array = (T[]) new Object[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = get(i);
        }
        return array;
    }

    private void swapNodes(
            MyLinkedListNode prev,
            MyLinkedListNode node1,
            MyLinkedListNode node2,
            MyLinkedListNode next
    ) throws MyLinkedListException {

        if (node2.next == null) {
            tail = node1;
            node1.next = null;
        } else {
            node1.next = next;
        }
        node2.next = node1;
        if (node1 == head) {
            head = node2;
        } else {
            prev.next = node2;
        }
    }

    private void checkEmptyError() throws MyLinkedListException {
        if (size == 0) {
            throw new MyLinkedListException("Empty list");
        }
    }

    public void add(T value) {
        if (size == 0) {
            head = tail = new MyLinkedListNode(value);
        } else {
            tail.next = new MyLinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    public T get(int index) throws MyLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new MyLinkedListException("Index out of range");
        }
        return getNode(index).value;
    }

    protected MyLinkedListNode getNode(int index) {
        MyLinkedListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public int size() {
        return size;
    }
}
