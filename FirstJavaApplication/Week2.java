import java.util.Scanner;

public class Week2 {
    public static void main(String[] args) {
        final int maxMarks = 65;
        String name;
        int mark, total;

        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the name of the student: ");
            name = input.next();

            System.out.printf("Enter of mark of %s out of %d: ", name, maxMarks);
            mark = input.nextInt();
        }
        total = (mark * 100) / maxMarks;
        System.out.printf("%s recieved %d %% of total marks.\n", name, total);
    }
}
