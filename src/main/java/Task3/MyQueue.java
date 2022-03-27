package Task3;

public interface MyQueue<T> {
    void add(T value);
    T remove() throws Exception;
    T element() throws Exception;
    int count();
    boolean isEmpty();
    void reverse() throws Exception;
}
