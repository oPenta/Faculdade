public class InsertionSort {
    public static void sort(int[][] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i][0];
            int j = i - 1;
            while (j >= 0 && array[j][0] > key) {
                array[j + 1][0] = array[j][0];
                j--;
            }
            array[j + 1][0] = key;
        }
    }
}
