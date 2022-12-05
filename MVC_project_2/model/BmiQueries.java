package bmi.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class BmiQueries{
    private final String host="jdbc:derby://localhost:1527/BmiDB";
    private Connection con;
    private PreparedStatement allStudents;
    private PreparedStatement specificStudent;
    private PreparedStatement bmiRange;
    private double height,weight,tRating;
    private String sID,rating;
    
    
    public BmiQueries(){
        height=0;weight=0;tRating=0;sID="";rating="";
        try{
            con=DriverManager.getConnection(host);
            allStudents=con.prepareStatement("SELECT * FROM STATS",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);         
            specificStudent=con.prepareStatement("SELECT * FROM STATS WHERE STUDENTID= ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            bmiRange=con.prepareStatement("SELECT *FROM STATS WHERE (weight/((height/100)*2))>= ? AND (weight/((height/100)*2))<= ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }catch(Exception err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public List<Bmi> getAllStudents(){
        List<Bmi>results=null;
        results=new ArrayList<>();
        ResultSet rs;
        try{
            rs=allStudents.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                results.add(new Bmi(sID,height,weight,rating));
            }
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
        return results;
    }
    public List<Bmi> getSpecificStudent(String id){
        List<Bmi>results=null;
        results=new ArrayList<>();
        ResultSet rs;
        try{
            specificStudent.setString(1,id);
            rs=specificStudent.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                results.add(new Bmi(sID,height,weight,rating));
            } 
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
        return results;
    }
    public List<Bmi> getBmiRange(double minBMI,double maxBMI){
        List<Bmi>results=null;
        results=new ArrayList<>();
        ResultSet rs;
        try{
            bmiRange.setDouble(1,minBMI);
            bmiRange.setDouble(2,maxBMI);
            rs=bmiRange.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                results.add(new Bmi(sID,height,weight,rating));
            }  
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
        return results;
    }
    public List<Bmi> getAtRisk(){
        List<Bmi>results=null;
        results=new ArrayList<>();
        ResultSet rs;
        try{
            rs=allStudents.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");

                height=height/100;
                tRating=(weight/(height*height));
                height=height*100;
                if(tRating<16.00){                    
                    rating="Bulimic";
                    results.add(new Bmi(sID,height,weight,rating));
                }
                else if(tRating>34.99){
                    rating="Morbid";
                    results.add(new Bmi(sID,height,weight,rating));
                }
            }
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
        return results;
    }
    public void updateStudent(String sID,double height,double weight,String rating){
        ResultSet rs;
        try{
            specificStudent.setString(1,sID);
            rs=specificStudent.executeQuery();
            rs.moveToInsertRow();
            while(rs.next()){
                rs.updateString("STUDENTID",sID);
                rs.updateDouble("HEIGHT",height);
                rs.updateDouble("WEIGHT",weight);
                rs.updateString("RATING",rating);
                rs.updateRow();
            }
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
    }
    public List<Bmi> addNewStudent(String sID,double height,double weight,String rating){
        List<Bmi>results=null;
        results=new ArrayList<>();
        ResultSet rs;
        try{
            rs=allStudents.executeQuery();
            rs.moveToInsertRow();
            
                rs.updateString("STUDENTID",sID);
                rs.updateDouble("HEIGHT",height);
                rs.updateDouble("WEIGHT",weight);
                rs.updateString("RATING",rating);
                rs.insertRow();
                
                results.add(new Bmi(sID,height,weight,rating));
                rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
        return results;
    }
    public void close(){
        try{
            con.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
    }
    public void updateAll(){
        ResultSet rs;
        try{
            rs=allStudents.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                
                height=(height/100);
                tRating=(weight/(height*height));
                height=(height*100);
                
                if(tRating<16.00){
                    rating="Bulimic";
                }
                if(tRating>16.00&tRating<16.99){
                    rating="Lean";
                }
                if(tRating>17.00&tRating<18.49){
                    rating="Under";
                }
                if(tRating>18.50&tRating<24.99){
                    rating="Normal";
                }
                if(tRating>25.00&tRating<29.99){
                    rating="Over";
                }
                if(tRating>30.00&tRating<34.99){
                    rating="Obese";
                }
                if(tRating>34.99){
                    rating="Morbid";
                }
                
                rs.updateString("STUDENTID", sID);
                rs.updateDouble("HEIGHT", height);
                rs.updateDouble("WEIGHT", weight);
                rs.updateString("RATING", rating);
                rs.updateRow();
            }
            rs.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
}