public class InsertionSort {
    public int[] sort(int[] arr, int c) {
        if (c <= 1) {
            return arr;
        } else {
            sort(arr, c - 1);
            int current = arr[c - 1];
            int i = c - 2;
            while (i >= 0 && arr[i] > current) {
                arr[i + 1] = arr[i];
                i--;
                arr[i + 1] = current;
            }
            return arr;
        }
    }
}
