package bmi.model;
public class Bmi{
    String sID,rating;
    double height,weight,tRating;
    
    public void bmi(){
        sID="";
        height=0;
        weight=0;
        rating="";
        tRating=0;
    }
    
    public void bmi(String sID,double height,double weight){
        this.sID=sID;
        this.height=height;
        this.weight=weight;
        this.rating=rating;
    }
    
    public void setStudentID(String sID){
        this.sID=sID;
    }
    public String getStudentID(){
        return this.sID;
    }
    
    public void setHeight(int height){
        this.height=height;
    }
    public double getHeight(){
        return this.height;
    }
    
    public void setWeight(int weight){
        this.weight=weight;
    }
    public double getWeight(){
        return this.weight;
    }
    
    public void setRating(String rating){
        this.rating=(calculateRating(this.height,this.weight));
    }
    public String calculateRating(double height,double weight){
        tRating=(height/100);
        tRating=(tRating*tRating);
        tRating=(weight/tRating);
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
        return rating;
    }
    public String getRating(){
        return this.rating;
    }
    
    @Override
    public String toString()
    {
        return getClass().getName()+"\n"+sID+" "+height+" "+weight+" "+rating;
    }
}
