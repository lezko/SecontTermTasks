package Task3;

public interface MyQueue<T> {
    boolean add(T value);
    T remove() throws Exception;
    T element() throws Exception;
    int size();
    boolean isEmpty();
    void reverse() throws Exception;
}
