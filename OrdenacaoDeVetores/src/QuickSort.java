public class QuickSort {
    public static void sort(int[][] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            sort(array, low, pivotIndex - 1);
            sort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[][] array, int low, int high) {
        int pivot = array[high][0];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array[j][0] < pivot) {
                i++;
                int temp = array[i][0];
                array[i][0] = array[j][0];
                array[j][0] = temp;
            }
        }

        int temp = array[i + 1][0];
        array[i + 1][0] = array[high][0];
        array[high][0] = temp;

        return i + 1;
    }
}
