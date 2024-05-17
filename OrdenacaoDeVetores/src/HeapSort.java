public class HeapSort {
    public static void sort(int[][] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            buildHeap(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0][0];
            arr[0][0] = arr[i][0];
            arr[i][0] = temp;
            buildHeap(arr, i, 0);
        }
    }

    private static void buildHeap(int[][] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left][0] > arr[largest][0]) {
            largest = left;
        }

        if (right < n && arr[right][0] > arr[largest][0]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[i][0];
            arr[i][0] = arr[largest][0];
            arr[largest][0] = temp;
            buildHeap(arr, n, largest);
        }
    }
}