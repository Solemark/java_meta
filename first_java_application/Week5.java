/*
Programmer: Mason Larcombe s0257989
File: Week5
Date: 09/04/2017
Purpose: COIT11222 assignment one question one for Term 1 2017
using a secondary method, determine 9 student grades and the average mark.
*/
import javax.swing.JOptionPane;																				//Imported the popup dialog boxes used throughout the program
public class Week5
{
    private static String getGrade(int mark)																//The getGrade method
	{
	    if (mark<=49)                                                                               		//If student scores 49 or lower, next line will execute
        	JOptionPane.showMessageDialog(null, "Student has failed:(F)");
	    else if (mark<=64)                                                                          		//If student scores between 50 and 64, next line will execute
	        JOptionPane.showMessageDialog(null, "Student has passed:(P)");
	    else if (mark<=74)                                                                          		//If student scores between 65 and 74, next line will execute
	        JOptionPane.showMessageDialog(null, "Student has recieved a Credit:(C)");
	    else if (mark<=84)                                                                         			//If student scores between 75 and 84, next line will execute
	        JOptionPane.showMessageDialog(null, "Student has recieved a Distinction:(D)");
	    else                                                                                        		//If student scores 85 of above, next line will execute
            JOptionPane.showMessageDialog(null, "Student has recieved a High Distinction:(HD)");

            return (String)(""+mark);																		//Turns the mark from an integer to a string
        }

   public static void main(String[] args)
    {
        int mark,tMark=0,count;																				//students mark, total mark and loop count variables
        final int N=3;																						//Final variable, largest number of student ID
        float avgMark;																						//Average student marks variable, float for decimal points

        JOptionPane.showMessageDialog(null, "Welcome to the Mark Entry System");

        for (count=N;count>0;count--)																		//A loop for the amount of student marks that need to be entered
        {
            mark=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Student mark (0-100)"));			//Popup dialog, asks for students mark between 0 and 100
            getGrade(mark);																					//Sends the mark to the getGrade method
            tMark=tMark+mark;																				//calculates the total mark, by adding the last entered mark to the total mark
        }
        avgMark=(float)tMark/N;																				//Calculates average mark by dividing total mark by number of student results entered
        JOptionPane.showMessageDialog(null, String.format("Average student mark is "+"%.2f",avgMark));		//Outputs a dialog box, showing average student mark upto two decimal places.
    }
}