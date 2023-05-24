package bmi.model;
import java.util.List;
public class BmiModel implements IModel{
    private BmiQueries queries;
    private Boolean queryStatus;
    private Bmi current;
    private List<Bmi> results;
    
    public BmiModel(BmiQueries queries){
        this.queries=queries;
        queryStatus=false;
        current=null;
        results=null;
    }
    public List<Bmi> list(){
        return results;
    }
    public void getAllStudents(){
        results=queries.getAllStudents();
    }
    public void getSpecificStudent(String sID){
        results=queries.getSpecificStudent(sID);
    }
    public void getBmiRange(double minBMI,double maxBMI){
        results=queries.getBmiRange(minBMI,maxBMI);
    }
    public void getAtRisk(){
        results=queries.getAtRisk();
    }
    public void close(){
        queries.close();
    }
    public void updateStudent(String sID,double height,double weight,String rating){
        queries.updateStudent(sID,height,weight,rating);
    }
    public void addNewStudent(String sID, double height, double weight, String rating) {
        results=queries.addNewStudent(sID,height,weight,rating);
    }
    public void updateAll(){
        queries.updateAll();
    }
    public Bmi current(){
        return results.get(0);
    }
    public boolean queryStatus(){
        if(list()==null){
            System.out.println("Query failed!");
            queryStatus=false;
        }
        else{
            queryStatus=true;
        }
        return queryStatus;
    }
}
