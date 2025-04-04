public class bubbleSort {
    public static void sort(int[][] array) {
        int n = array.length;
        boolean troca;
        for (int i = 0; i < n - 1; i++) {
            troca = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j][0] > array[j + 1][0]) {
                    int temp = array[j][0];
                    array[j][0] = array[j + 1][0];
                    array[j + 1][0] = temp;
                    troca = true;
                }
            }
            if (!troca) {
                break;
            }
        }
    }
}
