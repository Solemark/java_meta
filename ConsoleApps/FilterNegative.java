public class FilterNegative {
    /**
     * removes values < 0 from array
     * 
     * @param input
     * @return int[]
     */
    public int[] filter(int[] input) {
        int[] output = {};
        for (int i = 0; i < input.length; i++) {
            if (0 < input[i]) {
                output[i] = input[i];
            }
        }
        return output;
    }
}
