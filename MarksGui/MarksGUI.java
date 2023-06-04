import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class MarksGUI extends JFrame implements ActionListener {
    String studentName, searchName;
    int studentMark, totalMark;
    final int maxStudents = 10;
    private int currentStudent = 0;
    float avgMark;

    private Mark[] markArray = new Mark[maxStudents];

    private JLabel titleLabel = new JLabel("CQUniversity Mark Management System");

    private JPanel inputPanel = new JPanel();
    private JLabel nameLabel = new JLabel("Student name:");
    private JLabel markLabel = new JLabel("Student mark:");
    private JTextField nameField = new JTextField(10);
    private JTextField markField = new JTextField(5);

    private JTextArea displayTextArea = new JTextArea("", 15, 56);
    private JScrollPane scrollPane = new JScrollPane(displayTextArea);

    private JPanel buttonPanel = new JPanel();
    private JButton enterButton = new JButton("Enter");
    private JButton displayButton = new JButton("Display All");
    private JButton searchButton = new JButton("Search");
    private JButton exitButton = new JButton("Exit");

    private final JPanel error = new JPanel();

    /**
     * Constructor for MarksGUI
     */
    public MarksGUI() {
        inputPanel.setLayout(new GridLayout(1, 4));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(markLabel);
        inputPanel.add(markField);

        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(enterButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(exitButton);

        displayTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayTextArea.setEditable(false);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        enterButton.addActionListener(this);
        displayButton.addActionListener(this);
        searchButton.addActionListener(this);
        exitButton.addActionListener(this);

        this.setLayout(new FlowLayout());
        add(titleLabel);
        add(inputPanel);
        add(scrollPane);
        add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.compareTo("Enter") == 0)
            getStudentDetails();
        else if (command.compareTo("Display All") == 0)
            displayAll();
        else if (command.compareTo("Search") == 0)
            search();
        else if (command.compareTo("Exit") == 0)
            exit();
        else {
            JOptionPane.showMessageDialog(error, "Unknown button press!", "Mark Management System",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * retrieve student details from the frontend and save them
     * 
     * @return void
     */
    private void getStudentDetails() {
        if (currentStudent == markArray.length) {
            JOptionPane.showMessageDialog(error, "Maximum number of students has been reached",
                    "Mark Management System", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nameField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(error, "You must enter a student name", "Mark Management System",
                    JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }
        if (markField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(error, "You must enter a mark", "Mark Management System",
                    JOptionPane.ERROR_MESSAGE);
            markField.requestFocus();
            return;
        }
        studentName = nameField.getText();
        studentMark = Integer.parseInt(markField.getText());
        markArray[currentStudent] = new Mark(studentName, studentMark);

        displayHeading();

        displayTextArea.append(String.format("%-29s%-17s%9s\n", studentName, studentMark, Mark.getGrade(studentMark)));
        nameField.setText("");
        markField.setText("");
        nameField.requestFocus();
        totalMark += studentMark;
        currentStudent++;
    }

    /**
     * adds a heading to the TextBox
     * 
     * @return void
     */
    private void displayHeading() {
        displayTextArea.setText(String.format("%-29s%-17s%9s\n", "Student Name", "Student Mark", "Grade"));
        appendLine();
    }

    /**
     * adds a linebreak to the TextBox
     * 
     * @return void
     */
    private void appendLine() {
        displayTextArea.append("-------------------------------------------------------\n");
    }

    /**
     * display all student records
     * 
     * @return void
     */
    private void displayAll() {
        if (currentStudent == 0) {
            JOptionPane.showMessageDialog(error, "No students entered", "Mark Management System",
                    JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }
        displayHeading();
        for (int i = 0; i < currentStudent; i++) {
            displayTextArea.append(String.format("%-29s%-17s%9s\n", markArray[i].getName(),
                    markArray[i].getMark(), Mark.getGrade(markArray[i].getMark())));
        }
        appendLine();
        avgMark = (float) totalMark / currentStudent;
        displayTextArea.append(String.format("Average mark: " + "%.2f", avgMark));
    }

    /**
     * searches for the student and displays record
     * 
     * @return void
     */
    private void search() {
        searchName = JOptionPane.showInputDialog(null, "Enter a student name to search");
        for (int i = 0; i <= currentStudent; i++) {
            if (i == currentStudent) {
                break;
            }
            if (markArray[i].getName().equalsIgnoreCase(searchName)) {
                displayTextArea.setText("");
                displayTextArea.setText(String.format("%-29s%-17s%9s\n", "Student Name", "Student Mark", "Grade"));
                appendLine();
                displayTextArea.append(String.format("%-29s%-17s%9s\n", markArray[i].getName(),
                        markArray[i].getMark(), Mark.getGrade(markArray[i].getMark())));
                return;
            }
        }
        JOptionPane.showMessageDialog(error, searchName + " not found", "Mark Management System",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * closes application
     * 
     * @return void
     */
    private void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        MarksGUI f = new MarksGUI();

        f.setBounds(200, 100, 480, 425);
        f.setTitle("Marks application");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
