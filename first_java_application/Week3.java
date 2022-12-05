/*
Programmer: Mason Larcombe s0257989
File: Week3
Date: 22/03/2017
Purpose: COIT11222 assignment one question one for Term 1 2017
using if statements to determine a student's grade.
*/
import java.util.Scanner; 															//Imported object/utility scanner for keyboard prompt
public class Week3
{
    public static void main(String[] args)
    {
        int grade;																	//Student grade variable

        Scanner kb=new Scanner(System.in);											//Activates keyboard prompt (kb)

        System.out.print("Enter student grade (0-100): ");							//Prompts for student grade
        grade=kb.nextInt();															//Retrieves student grade as integer

        if (grade<=49)																//If student scores 49 or lower, next line will execute
            System.out.printf("Student has failed:(F)");
        else if (grade<=64)															//If student scores between 50 and 64, next line will execute
            System.out.printf("Student has passed:(P)");
        else if (grade<=74)															//If student scores between 65 and 74, next line will execute
            System.out.printf("Student has recieved a Credit:(C)");
        else if (grade<=84)															//If student scores between 75 and 84, next line will execute
            System.out.printf("Student has recieved a Distinction:(D)");
        else																		//If student scores 85 of above, next line will execute
            System.out.printf("Student has recieved a High Distinction:(HD)");

        System.out.println("\n");														//Linebreak
    }
}