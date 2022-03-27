package Task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    static Stream<Arguments> testGetFactory() {
        return Stream.of(
                Arguments.arguments(new double[]{1, 2, 3}, new double[]{1, 2, 3}),
                Arguments.arguments(new double[]{2, 1, 3}, new double[]{1, 2, 3}),
                Arguments.arguments(new double[]{7, 5, 9, 1}, new double[]{1, 5, 7, 9}),
                Arguments.arguments(new double[]{1}, new double[]{1}),
                Arguments.arguments(new double[]{ }, new double[]{ }),
                Arguments.arguments(new double[]{1, 1}, new double[]{1, 1}),
                Arguments.arguments(new double[]{5, 2}, new double[]{2, 5}),
                Arguments.arguments(new double[]{4, 3, 2, 1, 4, 3, 2, 1}, new double[]{1, 1, 2, 2, 3, 3, 4, 4}),
                Arguments.arguments(new double[]{4, 3, -2, 1, 4, 3, 2, -1}, new double[]{-2, -1, 1, 2, 3, 3, 4, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("testGetFactory")
    void testBubbleSort(double[] arr, double[] sortedArr) throws MyLinkedList.MyLinkedListException {
        Double[] objArr = new Double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            objArr[i] = arr[i];
        }
        Double[] objSortedArr = new Double[arr.length];
        for (int i = 0; i < sortedArr.length; i++) {
            objSortedArr[i] = sortedArr[i];
        }

        MyLinkedList<Double> list = new MyLinkedList(objArr);
        list.bubbleSort(Comparator.comparingDouble(a -> (double) a));
        System.out.printf("%-10s %s\n", "Input:", Arrays.toString(arr));
        System.out.printf("%-10s %s\n", "Expected:", Arrays.toString(sortedArr));
        System.out.printf("%-10s %s\n", "Output:", Arrays.toString(list.toArray()));
        if (Arrays.deepEquals(list.toArray(), objSortedArr)) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
        System.out.println("========================================================");

        assertTrue(Arrays.deepEquals(list.toArray(), objSortedArr));
    }
}