public class Mark {
    private String name;
    private int mark;

    public Mark(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return this.mark;
    }

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
