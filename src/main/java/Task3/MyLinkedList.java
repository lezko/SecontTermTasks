package Task3;

public class MyLinkedList<T> {
    public static class MyLinkedListException extends Exception {
        public MyLinkedListException(String message) {
            super(message);
        }
    }

    private class MyLinkedListNode {
        public T value;
        public MyLinkedListNode next;

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

    public void addFirst(T value) {
        head = new MyLinkedListNode(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
        if (size == 0) {
            head = tail = new MyLinkedListNode(value);
        } else {
            tail.next = new MyLinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws MyLinkedListException {
        if (size == 0) {
            throw new MyLinkedListException("Empty list");
        }
    }

    private MyLinkedListNode getNode(int index) {
        MyLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeFirst() throws MyLinkedListException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws MyLinkedListException {
        checkEmptyError();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    public void remove(int index) throws MyLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new MyLinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            MyLinkedListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            size--;
        }
    }

    public int size() {
        return size;
    }

    public T get(int index) throws MyLinkedListException {
        checkEmptyError();
        return getNode(index).value;
    }

    public T getFirst() throws MyLinkedListException {
        checkEmptyError();
        return head.value;
    }

    public T getLast() throws MyLinkedListException {
        checkEmptyError();
        return tail.value;
    }
}
