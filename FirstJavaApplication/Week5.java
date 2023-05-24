import javax.swing.JOptionPane;

public class Week5 {
    private static void getGrade(int mark) {
        if (mark <= 49)
            JOptionPane.showMessageDialog(null, "Student has failed:(F)");
        else if (mark <= 64)
            JOptionPane.showMessageDialog(null, "Student has passed:(P)");
        else if (mark <= 74)
            JOptionPane.showMessageDialog(null, "Student has recieved a Credit:(C)");
        else if (mark <= 84)
            JOptionPane.showMessageDialog(null, "Student has recieved a Distinction:(D)");
        else
            JOptionPane.showMessageDialog(null, "Student has recieved a High Distinction:(HD)");
    }

    public static void main(String[] args) {
        int mark, tMark = 0, count;
        final int N = 3;
        float avgMark;

        JOptionPane.showMessageDialog(null, "Welcome to the Mark Entry System");
        for (count = N; count > 0; count--) {
            mark = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Student mark (0-100)"));
            getGrade(mark);
            tMark = tMark + mark;
        }
        avgMark = (float) tMark / N;
        JOptionPane.showMessageDialog(null, String.format("Average student mark is %.2f", avgMark));
    }
}
