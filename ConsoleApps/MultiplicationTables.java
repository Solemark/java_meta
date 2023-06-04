public class MultiplicationTables {
    /**
     * calculates multiplication table for number upto max
     * 
     * @param number
     * @param max
     * @return String[]
     */
    public String[] getTable(int number, int max) {
        String output[] = new String[max + 1];
        for (int i = 0; i <= max; i++) {
            output[i] = String.format("%d x %d = %d", i, number, (i * number));
        }
        return output;
    }
}
