package Task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListQueueTest {
    static Stream<Arguments> queueTestProvider() throws Exception {
        MyQueue<String> q = new MyLinkedListQueue<>();
        q.add("one");
        q.add("two");
        q.add("three");
        q.add("four");
        q.add("five");
        return Stream.of(
            Arguments.arguments("one", q.element()),
            Arguments.arguments("one", q.remove()),
            Arguments.arguments("two", q.remove()),
            Arguments.arguments("three", q.remove())
        );
    }

    @ParameterizedTest
    @MethodSource("queueTestProvider")
    void queueTest(String expected, String actual) {
        assertEquals(expected, actual);
    }



    static Stream<Arguments> reverseTestProvider() throws Exception {
        MyQueue<String> q = new MyLinkedListQueue<>();

        q.add("one");
        q.add("two");
        q.add("three");
        q.add("four");
        q.add("five");
        q.reverse();
        return Stream.of(
                Arguments.arguments("five", q.element()),
                Arguments.arguments("five", q.remove()),
                Arguments.arguments("four", q.remove()),
                Arguments.arguments("three", q.remove()),
                Arguments.arguments("two", q.remove()),
                Arguments.arguments("one", q.remove())
        );
    }

    @ParameterizedTest
    @MethodSource("reverseTestProvider")
    void reverseTest(String expected, String actual) {
        assertEquals(expected, actual);
    }
}