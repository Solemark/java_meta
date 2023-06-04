public class InsertionSort {
    /**
     * performs an insertion sort
     * 
     * @param input
     * @param count
     * @return int[]
     */
    public int[] sort(int[] input, int count) {
        if (count <= 1) {
            return input;
        } else {
            sort(input, count - 1);
            int current = input[count - 1];
            int i = count - 2;
            while (i >= 0 && input[i] > current) {
                input[i + 1] = input[i];
                i--;
                input[i + 1] = current;
            }
            return input;
        }
    }
}
