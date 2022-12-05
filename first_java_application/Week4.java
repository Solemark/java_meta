/*
Programmer: Mason Larcombe s0257989
File: Week4
Date: 28/03/2017
Purpose: COIT11222 assignment one question one for Term 1 2017
using loops, determine 9 student grades and the average mark.
*/
import java.util.Scanner;																//Imported object/utility scanner for keyboard prompt
public class Week4
{
    public static void main(String[] args)
    {
		final int N=3; 																	//Final variable, largest number of student ID
        int grade,count,tMark=0;														//student grade, loop count and total mark variables
        String name;																	//Student name variable
        float avgMark;																	//Average student marks variable, float for decimal points

        Scanner namekb=new Scanner(System.in);											//Activates keyboard prompt (student name)
        Scanner gradekb=new Scanner(System.in);											//Activates keyboard prompt (student grade)

        System.out.println("                    Mark Entry System\n");					//Program title

        for(count=N;count>0;count--)													//loop, uses variable N to determine number of loops
        {
            System.out.print("Enter student name: ");									//Asks for student name
            name=namekb.nextLine();														//Retrieves student name

            System.out.print("Enter student grade (0-100): ");							//Asks for student grade
            grade=gradekb.nextInt();													//Retrieves student grade
            System.out.println("");														//Line break
            if (grade<=49)																//If grade is 49 or below, executes next line of code
                System.out.printf(name+" has failed:(F)\n\n");
            else if (grade<=64)															//If grade is between 50 and 64, executes next line of code
                System.out.printf(name+" has passed:(P)\n\n");
            else if (grade<=74)															//If grade is between 65 and 74, executes next line of code
                System.out.printf(name+" has recieved a Credit:(C)\n\n");
            else if (grade<=84)															//If grade is between 75 and 84, executes next line of code
                System.out.printf(name+" has recieved a Distinction:(D)\n\n");
            else																		//If grade is 85 or above, executes next line of code
                System.out.printf(name+" has recieved a High Distinction:(HD)\n\n");
            tMark=grade+tMark;															//Adds input grade to previous total
        }
        avgMark=(float)tMark/N;															//Calculates average mark and stores as float
        System.out.printf("The average student mark is "+"%.2f",avgMark);				//Displays average student mark to two decimal places.
        System.out.println("");															//Line Break
    }
}