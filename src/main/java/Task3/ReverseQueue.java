package Task3;

import java.util.LinkedList;

class ReverseQueue<T> extends LinkedList<T> implements MyQueue<T> {
    public void reverse() {
        if (!isEmpty()) {
            T first = remove();
            reverse();
            add(first);
        }
    }
}
