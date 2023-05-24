import java.util.Scanner;

public class Week2 {
    public static void main(String[] args) {
        final int max_marks = 65;
        String name;
        int mark, total;

        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the name of the student: ");
            name = input.next();

            System.out.print("Enter of mark of " + name + " out of " + max_marks + ": ");
            mark = input.nextInt();
        }
        total = (mark * 100) / max_marks;
        System.out.printf("%s recieved %d %% of total marks.\n", name, total);
    }
}
