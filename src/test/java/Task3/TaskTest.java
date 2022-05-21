package Task3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    static Stream<Arguments> reverseTestProvider() throws Exception {
        ReverseQueue<String> q = new ReverseQueue<>();

        q.add("one");
        q.add("two");
        q.add("three");
        q.add("four");
        q.add("five");
        q.reverse();
        return Stream.of(
                Arguments.arguments("five", q.peek()),
                Arguments.arguments("five", q.poll()),
                Arguments.arguments("four", q.poll()),
                Arguments.arguments("three", q.poll()),
                Arguments.arguments("two", q.poll()),
                Arguments.arguments("one", q.poll())
        );
    }

    @ParameterizedTest
    @MethodSource("reverseTestProvider")
    void reverseTest(String expected, String actual) {
        assertEquals(expected, actual);
    }
}