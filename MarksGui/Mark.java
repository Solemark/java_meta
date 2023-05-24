public class Mark
{
    private String studentName;
    private int studentMark;
    static String studentGrade;

    public Mark()   //default constructor
    {
    }

    public Mark(String sName, int sMark)    //parametised constructor
    {
        this.studentName = sName;
        this.studentMark = sMark;
    }

    public void setStudentName(String sName)    //student name set method (mutator)
    {
		studentName=sName;
    }
    public String getStudentName()  //student name get method (accessor)
    {
		return studentName;
    }
    public void setStudentMark(int sMark)   //student mark set method (mutator)
    {
		this.studentMark=sMark;
    }
    public int getStudentMark()     //student mark get method (accessor)
    {
		return this.studentMark;
    }

    public static String getGrade(int sMark)     //getGrade method
    {
        final int P=50;
        final int C=65;
        final int D=75;
        final int HD=85;

        if (sMark<P)
            studentGrade="F";
        else if (sMark<C)
	    	studentGrade="P";
        else if (sMark<D)
            studentGrade="C";
        else if (sMark<HD)
            studentGrade="D";
		else
            studentGrade="HD";

        return studentGrade;
    }
}

