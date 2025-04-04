public class SelectionSort {
    public static void sort(int[][] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j][0] < array[minIndex][0]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i][0];
                array[i][0] = array[minIndex][0];
                array[minIndex][0] = temp;
            }
        }
    }
}