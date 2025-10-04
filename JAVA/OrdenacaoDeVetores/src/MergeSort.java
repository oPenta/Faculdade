public class    MergeSort {
    public static void sort(int[][] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[][] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    private static void merge(int[][] arr, int left, int middle, int right) {
        int leftLength = middle - left + 1;
        int rightLength = right - middle;

        int[][] leftArray = new int[leftLength][1];
        int[][] rightArray = new int[rightLength][1];

        for (int i = 0; i < leftLength; i++) {
            leftArray[i][0] = arr[left + i][0];
        }

        for (int i = 0; i < rightLength; i++) {
            rightArray[i][0] = arr[middle + 1 + i][0];
        }

        int i = 0, j = 0, k = left;

        while (i < leftLength && j < rightLength) {
            if (leftArray[i][0] <= rightArray[j][0]) {
                arr[k][0] = leftArray[i][0];
                i++;
            } else {
                arr[k][0] = rightArray[j][0];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k][0] = leftArray[i][0];
            i++;
            k++;
        }

        while (j < rightLength) {
            arr[k][0] = rightArray[j][0];
            j++;
            k++;
        }
    }
}
