package Task3;

public class MyLinkedListQueue<T> extends MyLinkedList<T> implements MyQueue<T> {
    public void reverse() throws Exception {
        if (!isEmpty()) {
            T first = remove();
            reverse();
            add(first);
        }
    }

    @Override
    public boolean add(T value) {
        addLast(value);
        return true;
    }

    @Override
    public T remove() throws Exception {
        T result = element();
        removeFirst();
        return result;
    }

    @Override
    public T element() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty");
        }
        return getFirst();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
