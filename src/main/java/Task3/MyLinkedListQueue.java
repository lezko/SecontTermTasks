package Task3;

public class MyLinkedListQueue<T> extends MyLinkedList<T> implements MyQueue<T> {
    public void reverse() throws Exception {
        if (count() == 2) {
            T headValue = getFirst();
            removeFirst();
            add(headValue);
        } else {
            T first = remove();
            reverse();
            add(first);
        }
    }

    @Override
    public void add(T value) {
        addLast(value);
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
    public int count() {
        return size();
    }

    @Override
    public boolean isEmpty() {
        return count() == 0;
    }
}
