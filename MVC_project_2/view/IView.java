package bmi.view;
public interface IView{
    public void setBrowsing(boolean flag);
    public void displayMessage(String m);
    public void displayRecord(bmi.model.Bmi b);
}
