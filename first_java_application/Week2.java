/*
Programmer: Mason Larcombe s0257989
File: Week2
Date: 13/03/2017
Purpose: COIT11222 assignment one question two for Term 1 2017
using basic input/output create a program to calculate a students' percentage mark.
*/
import java.util.Scanner;														//Imported object/utility scanner for keyboard prompt
public class Week2
{
    public static void main(String[] args)
    {
        final int max_marks = 65;												//Maximium mark variable
        String name;															//Student name variable
        int mark,p;																//Student mark and percentage mark variables

        Scanner kb=new Scanner(System.in);										//Activates keyboard prompt (kb)

        System.out.print("Enter the name of the student: ");					//Asks for student name
        name=kb.nextLine();														//Retrieves student name as string
		System.out.println("");													//Line break

        System.out.print("Enter of mark of "+name+" out of "+max_marks+": ");	//Asks for the students mark out of integer "max_marks"
        mark=kb.nextInt();														//Retrieves student mark as integer
		System.out.println("");													//Line break

        p=(mark*100)/max_marks;													//Calculates students percentage

        System.out.println(name+" recieved "+p+"% of total marks.");			//Outputs student name and percentage marks
        System.out.println("");													//Line break
    }
}