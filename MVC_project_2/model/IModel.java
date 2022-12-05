package bmi.model;
import java.util.List;
public interface IModel{
    List<bmi.model.Bmi> list();
    public void getAllStudents();
    public void getSpecificStudent(String s);
    public void getBmiRange(double b1,double b2);
    public void getAtRisk();
    public void close();
    public void updateStudent(String sID,double height,double weight,String rating);
    public void addNewStudent(String sID,double height,double weight,String rating);
    public void updateAll();
    public Bmi current();
    public boolean queryStatus();
}
