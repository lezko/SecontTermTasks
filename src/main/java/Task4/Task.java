package Task4;

public class Task {
    public static void sort(int[] arr, int m) {
        int[] n = new int[m + 1];
        for (int i = 0; i < arr.length; i++) {
            n[arr[i]]++;
        }

        int k = 0;
        for (int i = 0; i < m + 1; i++) {
            if (n[i] != 0) {
                for (int j = 0; j < n[i]; j++) {
                    arr[k] = i;
                    k++;
                }
            }
        }
    }
}
