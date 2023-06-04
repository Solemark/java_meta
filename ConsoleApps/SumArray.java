public class SumArray {
    /**
     * Sum all the values of an array
     * 
     * @param input
     * @return
     */
    public int sum(int[] input) {
        int total = 0;
        for (int i : input) {
            total += i;
        }
        return total;
    }
}
