public class PrintEvensOrOdds {
    /**
     * if (flag = true) find evens else find odds
     * 
     * @param int     max
     * @param boolean flag
     * @return String output
     */
    public static String findEvenOrOdds(int max, boolean flag) {
        String output = "";
        for (int i = 0; i <= max; i++) {
            if (flag) {
                if (i % 2 == 0) {
                    output += String.format("%d\n", i);
                }
            } else {
                if (i % 2 != 0) {
                    output += String.format("%d\n", i);
                }
            }
        }
        return output;
    }
}
