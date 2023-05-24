package bmi.presenter;
import bmi.model.IModel;
import bmi.view.IView;
public class BMIPresenter{
    private IView view;
    private IModel model;
    private int current,total,i;
    private String sID;
    public BMIPresenter(){}      
    public BMIPresenter(IModel model){
        this.model=model;
        view=null;
        current=0;
        total=0;
        i=0;
        sID=null;
    }  
    public void getAllStudents(){
        model.getAllStudents();
        total=model.list().size();
        for(current=0;current<total;current++){
            view.displayRecord(model.list().get(current));
        }
    }
    public void getSpecificStudent(String sID){
        model.getSpecificStudent(sID);
        view.displayRecord(model.list().get(0));
    }
    public void getBmiRange(double minBMI,double maxBMI){
        model.getBmiRange(minBMI,maxBMI);
        total=model.list().size();
        for(current=0;current<total;current++){
            view.displayRecord(model.list().get(current));
        }
    }
    public void getAtRisk(){
        model.getAtRisk();
        total=model.list().size();
        for(current=0;current<total;current++){
            view.displayRecord(model.list().get(current));
        }
    }
    public void updateStudent(String sID,double height,double weight,String rating){
        model.updateStudent(sID,height,weight,rating);
    }
    public void addNewStudent(String sID,double height,double weight,String rating){
        model.addNewStudent(sID,height,weight,rating);
        view.displayRecord(model.list().get(0));
    }
    public void updateAll(){
        model.updateAll();
        view.displayMessage("Update complete");
    }
    public void next(){
        i++;
        model.getAllStudents();
        total=model.list().size();
        if(i>total){
            i=0;
            view.displayMessage("Returning to top");
        }
        else{
            if(i<10){
                sID=("S0"+i);
            }
            else if(i>=10){
                sID=("S"+i);
            }
            model.getSpecificStudent(sID);
            view.displayRecord(model.list().get((0)));
        }
    }
    public void previous(){
        i--;
        model.getAllStudents();
        total=model.list().size();
        if(i<=0){
            i=(total+1);
            view.displayMessage("Returning to bottom");
        }
        else{
            if(i<10){
                sID=("S0"+i);
            }
            else if(i>=10){
                sID=("S"+i);
            }
            model.getSpecificStudent(sID);
            view.displayRecord(model.list().get((0)));
        }
    }
    public void close(){
        model.close();
    }
    
    public void bind(IView v){
        view=v;
    }
}
