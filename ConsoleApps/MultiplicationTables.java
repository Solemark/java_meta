public class MultiplicationTables {
    public static String[] getTable(int number, int max) {
        String output[] = new String[max + 1];
        for (int i = 0; i <= max; i++) {
            output[i] = String.format("%d x %d = %d", i, number, (i * number));
        }
        return output;
    }

    public static void main(String args[]) {
        String[] data = getTable(7, 12);
        for (String line : data) {
            System.out.println(line);
        }
    }
}
