public class Mark {
    private String name;
    private int mark;

    /**
     * Mark Constructor
     * 
     * @param name
     * @param mark
     */
    public Mark(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * update the student's name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the student's name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * update the student's mark
     * 
     * @param mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * get the student's mark
     * 
     * @return
     */
    public int getMark() {
        return this.mark;
    }

    /**
     * get the student's grade from their mark
     * 
     * @param mark
     * @return String
     */
    public static String getGrade(int mark) {
        if (mark < 50)
            return "F";
        else if (mark < 65)
            return "P";
        else if (mark < 75)
            return "C";
        else if (mark < 85)
            return "D";
        else
            return "HD";
    }
}
