package Task3;

import java.util.LinkedList;
import java.util.Queue;

class Task {
    public static Queue<String> reverse(Queue<String> source) {
        Queue<String> target = new LinkedList<>();
        return reverse(source, target);
    }

    private static Queue<String> reverse(Queue<String> source, Queue<String> target) {
        if (!source.isEmpty()) {
            String elem = source.remove();
            reverse(source, target);
            target.add(elem);
        }
        return target;
    }
}
