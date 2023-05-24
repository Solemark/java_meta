/*
Programmer: Mason Larcombe
Course: Programming Fundamentals COIT 11222
File: MarksGUI.java
Purpose: Assignment Two -- Marks windowed application shell
Date: 25 April 2017
*/

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class MarksGUI extends JFrame implements ActionListener
{
    String sName,searchName;	//sName and searchName variables
    int sMark,tMark,c=0;	//sMark, tMark and c variables
    final int maxStudents=10;	//maxStudents variable
    private int currentStudent=0;	//currentStudent variable
    float avgMark;	//avgMark variable

    private Mark[] markArray=new Mark[maxStudents]; //markArray, stores student name(sName and student mark (sMark)
    private String[] gradeArray=new String[maxStudents]; //gradeArray, stores student grade

    private JLabel titleLabel = new JLabel("CQUniversity Mark Management System"); // program title
    private JLabel nameLabel = new JLabel("Student name: ");// for prompt
    private JTextField nameField = new JTextField(26);      // for student name entry

    private JLabel markLabel = new JLabel("Student mark: ");// for prompt
    private JTextField markField = new JTextField(4);       // for student mark entry

    private JTextArea displayTextArea = new JTextArea("", 15, 56); // declare text area
    private JScrollPane scrollPane; // scroll pane for the text area
    private final JPanel error=new JPanel();	//error panel

    private JButton enterButton = new JButton("Enter"); // Enter button
    private JButton displayButton = new JButton("Display All"); //Display All button
    private JButton searchButton = new JButton("Search"); //Search button
    private JButton exitButton = new JButton("Exit"); //Exit button

    public MarksGUI()
    { // constructor create the Gui
    	this.setLayout(new FlowLayout());   // set JFrame to FlowLayout

		titleLabel.setFont(new Font("Ariel", Font.BOLD, 24));   //calls JFrame labels and fields
		add(titleLabel);
		add(nameLabel);
		add(nameField);
		add(markLabel);
		add(markField);

		// set text area to a monospaced font so the columns can be aligned using a format string
		displayTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		displayTextArea.setEditable(false);     // make text area read only
		scrollPane = new JScrollPane(displayTextArea);      // add text area to the scroll pane
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    // just need vertical scrolling

		add(scrollPane);    //adds scrollpane to JFrame, if required

		add(enterButton);   //adds buttons to JFrame
		add(displayButton);
		add(searchButton);
		add(exitButton);

		enterButton.addActionListener(this);    // add the action listener to the buttons
        displayButton.addActionListener(this);
		searchButton.addActionListener(this);
		exitButton.addActionListener(this);

			addWindowListener   //handles special reaction to closing window from top right [X]
        	( // override window closing method
				new WindowAdapter()
            	{
					public void windowClosing(WindowEvent e)
					{
        	            exit();     // Attempt to exit application
					}
        	    }
			);

    } // MarkGUI

    @Override
    public void actionPerformed(ActionEvent e)  // handles all buttonclicks
    {
		String command = e.getActionCommand();

		if (command.compareTo("Enter") == 0)
            enterStudentNameAndMark();
		else if (command.compareTo("Display All") == 0)
            displayAll();
		else if (command.compareTo("Search") == 0)
            search();
		else if (command.compareTo("Exit") == 0)
            exit();
    } // actionPerformed

    private void enterStudentNameAndMark()
    {
        if(currentStudent==markArray.length)    //error code for attempting to add an 11th student
        {
            JOptionPane.showMessageDialog(error,"Maximum number of students has been reached","Mark Management System",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nameField.getText().compareTo("")==0) //error code for black name field
        {
            JOptionPane.showMessageDialog(error,"You must enter a student name","Mark Management System",JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }
        if (markField.getText().compareTo("")==0) //error code for black mark field
        {
            JOptionPane.showMessageDialog(error,"You must enter a mark","Mark Management System",JOptionPane.ERROR_MESSAGE);
            markField.requestFocus();
            return;
        }

        sName=nameField.getText();  //saves namefield as sName
        sMark=Integer.parseInt(markField.getText());    //saves markfield as sMark

        markArray[currentStudent]=new Mark(sName, sMark);   //stores sName and sMark in markArray

        gradeArray[currentStudent]=String.format(Mark.getGrade(sMark));     //stores grade in gradeArray

        displayHeading();   //calls heading method
        displayTextArea.append(String.format("%-29s%-17s%9s\n", sName, sMark, Mark.studentGrade));  //displays entered student data

        nameField.setText("");  //clears name and mark fields then sets name as highlighted field
        markField.setText("");
        nameField.requestFocus();

        tMark=sMark+tMark;  //determines total mark

        ++currentStudent;   //increases currentStudent number
    } // enterStudentNameAndMark

    private void displayHeading()   //sets up the heading in the JFrame text area
    {
		displayTextArea.setText(String.format("%-29s%-17s%9s\n", "Student Name", "Student Mark", "Grade"));
		appendLine();
    } // displayHeading

    private void appendLine()   //linebreak
    {
		displayTextArea.append("-------------------------------------------------------\n");
    } // appendLine

    private void displayAll()   //displays all entered student data
    {
		if(currentStudent==0)   //no students entered error code
        {
            JOptionPane.showMessageDialog(error,"No students entered","Mark Management System",JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }

        displayHeading();   //adds file heading

        for (int i=0; i < currentStudent; i++)      //lists saved student data
        {
	    	displayTextArea.append(String.format("%-29s%-17s%9s\n", markArray[i].getStudentName(), markArray[i].getStudentMark(), gradeArray[i]));
        }

        appendLine();   //linebreak
        avgMark=(float)tMark/currentStudent;    //calculates average student mark
        displayTextArea.append(String.format("Average mark: "+"%.2f",avgMark));     //displays average student mark
    } // displayAll

    private void search()   //searches for entered student
    {
		searchName=JOptionPane.showInputDialog(null, "Enter a student name to search");     //student search prompt

        for(c=0;c<currentStudent;++c)   //checks database for stored student data
        {
            if(c==currentStudent)       //if unable to find stored student data loop ends
            {
                break;
            }
            if(markArray[c].getStudentName().equalsIgnoreCase(searchName))
            {
                displayTextArea.setText("");
                displayTextArea.setText(String.format("%-29s%-17s%9s\n", "Student Name", "Student Mark", "Grade"));
                appendLine();
                displayTextArea.append(String.format("%-29s%-17s%9s\n", markArray[c].getStudentName(), markArray[c].getStudentMark(), gradeArray[c]));
                return;
            }
        }
        JOptionPane.showMessageDialog(error,searchName+" not found","Mark Management System",JOptionPane.ERROR_MESSAGE);    //invalid search error message
    } // search

    private void exit() // display exit message and exit the app
    {
        JOptionPane.showMessageDialog(null,"Thank you for using the Mark Management System");
		System.exit(0);
    } // exit

    // Main method create instance of class
    public static void main(String[] args)
    {
		MarksGUI f = new MarksGUI();    // Create instance of class

		f.setBounds(200, 100, 480, 425);    // Define position and size of app
		f.setTitle("Marks application");    // Set the title of the app
		f.setVisible(true);     // Make the application visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // allow the code to close the program
    } // main
}