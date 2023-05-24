import java.util.Scanner;

public class Week3 {
    public static void main(String[] args) {
        int grade;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter student grade (0-100): ");
            grade = input.nextInt();
        }
        if (grade <= 49)
            System.out.println("Student has failed:(F)");
        else if (grade <= 64)
            System.out.println("Student has passed:(P)");
        else if (grade <= 74)
            System.out.println("Student has recieved a Credit:(C)");
        else if (grade <= 84)
            System.out.println("Student has recieved a Distinction:(D)");
        else
            System.out.println("Student has recieved a High Distinction:(HD)");
    }
}
