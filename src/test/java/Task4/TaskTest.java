package Task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    static Stream<Arguments> sortTestProvider() throws Exception {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3}, new int[]{3, 2, 1}, 1000),
                Arguments.arguments(new int[]{1, 4, 7, 8, 20}, new int[]{20, 4, 8, 1, 7}, 1000),
                Arguments.arguments(new int[]{0, 0, 0, 2, 4, 10}, new int[]{10, 0, 4, 0, 2, 0}, 1000),
                Arguments.arguments(new int[]{20, 20, 20, 40, 40, 50}, new int[]{50, 20, 40, 20, 40, 20}, 1000)
        );
    }

    @ParameterizedTest
    @MethodSource("sortTestProvider")
    void sortTest(int[] expected, int[] actual, int m) {
        Task.sort(actual, m);
        assertArrayEquals(expected, actual);
    }
}