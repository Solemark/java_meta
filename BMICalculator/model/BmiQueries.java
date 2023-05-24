package bmi.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;
import bmi.view.BMIView;
import static bmi.view.BMIView.dbOut;
import static bmi.view.BMIView.sID;
import static bmi.view.BMIView.height;
import static bmi.view.BMIView.weight;
import static bmi.view.BMIView.rating;
import static bmi.view.BMIView.tRating;

public class BmiQueries {
    private static double tRating;
    private static DecimalFormat df=new DecimalFormat("#.00");
    private static String host="jdbc:derby://localhost:1527/BmiDB";
    private static String allStudents="SELECT * FROM STATS";
    private static String specificStudent="SELECT * FROM STATS WHERE STUDENTID="+"'"+BMIView.sID+"'";
    private static String bmiRange="SELECT *FROM STATS WHERE (weight/((height/100)*2))>="+BMIView.minBMI+" AND (weight/((height/100)*2))<="+BMIView.maxBMI;
    public static ArrayList<String> list=new ArrayList<String>();
    
    public static String getHost(){
        return host;
    }
    
    public static String getAllStudents(){
        return allStudents;
    }
    
    public static String getSpecificStudent(String sID){
        return ("SELECT * FROM STATS WHERE STUDENTID="+"'"+sID+"'");
    }
    
    public static String getBMIRange(Double minBMI, double maxBMI){
        return ("SELECT *FROM STATS WHERE (weight/((height/100)*2))>="+BMIView.minBMI+" AND (weight/((height/100)*2))<="+BMIView.maxBMI);
    }
    
    public static void getAtRisk(){
        try{
            Connection con=DriverManager.getConnection(BmiQueries.getHost());
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery(BmiQueries.getAllStudents());
            
            rs.beforeFirst();
            while(rs.next()){
                sID=rs.getString("STUDENTID");
                height=rs.getDouble("HEIGHT");
                weight=rs.getDouble("WEIGHT");
                rating=rs.getString("RATING");
                dbOut=sID+" "+height+" "+weight+" "+rating;
        
                height=height/100;
                tRating=(height*height);
                tRating=(weight/tRating);
                rating=df.format(tRating);
                
                if(tRating<16.00){                    
                    rating="Bulimic";
                    System.out.println(dbOut);
                    list.add(dbOut);
                }
                else if(tRating>34.99){
                    rating="Morbid";
                    System.out.println(dbOut);
                    list.add(dbOut);
                } 
            }
            System.out.println("Done!");
            con.close();
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
    
    public static void updateStudent(String sID,double height,double weight,String rating){
        try{
            Connection con=DriverManager.getConnection(getHost());
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery(getSpecificStudent(sID));
            
            rs.beforeFirst();
            while(rs.next()){
                rs.updateString("STUDENTID", sID);
                rs.updateDouble("HEIGHT", height);
                rs.updateDouble("WEIGHT", weight);
                rs.updateString("RATING", rating);
                rs.updateRow();
            }
            System.out.println("Done!");
            con.close();
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
    
    public static void addNewStudent(String sID,double height,double weight,String rating){
        try{
            Connection con=DriverManager.getConnection(BmiQueries.getHost());
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery(BmiQueries.getAllStudents());

            rs.moveToInsertRow();
            
                rs.updateString("STUDENTID",sID);
                rs.updateDouble("HEIGHT",height);
                rs.updateDouble("WEIGHT",weight);
                rs.updateString("RATING",rating);
                dbOut=sID+" "+height+" "+weight+" "+rating;
        
                System.out.println(dbOut);
                //jTextArea1.append(dbOut+"\n");
                
            rs.insertRow();
            rs.close();
            System.out.println("Done!");
            con.close();
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,"Error!\n"+err.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static String retrieveDBOut(){
        return dbOut;
    }
}