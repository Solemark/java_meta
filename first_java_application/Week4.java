import java.util.Scanner;

public class Week4 {
    public static void main(String[] args) {
        final int N = 3;
        int grade, count, tMark = 0;
        String name;
        float avgMark;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("\t\tMark Entry System\n");
            for (count = N; count > 0; count--) {
                System.out.print("Enter student name: ");
                name = input.next();

                System.out.print("Enter student grade (0-100): ");
                grade = input.nextInt();
                if (grade <= 49)
                    System.out.printf("%s has failed:(F)\n", name);
                else if (grade <= 64)
                    System.out.printf("%s has passed:(P)\n", name);
                else if (grade <= 74)
                    System.out.printf("%s has recieved a Credit:(C)\n", name);
                else if (grade <= 84)
                    System.out.printf("%s has recieved a Distinction:(D)\n", name);
                else
                    System.out.printf("%s has recieved a High Distinction:(HD)\n", name);
                tMark = grade + tMark;
            }
        }
        avgMark = (float) tMark / N;
        System.out.printf("The average student mark is %.2f\n", avgMark);
    }
}
