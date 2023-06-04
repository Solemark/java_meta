public class ReverseArray {
    /**
     * Reverse array algorithmically
     * 
     * @param input
     * @return
     */
    public int[] reverse(int[] input) {
        int c = input.length - 1;
        int x = 0;
        int y = 0;
        for (int i = 0; i <= c; i++, c--) {
            x = input[i];
            y = input[c];
            input[i] = y;
            input[c] = x;
        }
        return input;
    }
}
