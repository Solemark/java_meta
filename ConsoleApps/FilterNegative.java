public class FilterNegative {
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
