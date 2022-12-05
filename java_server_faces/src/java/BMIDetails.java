import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
@Named(value = "bMIDetails")
@SessionScoped
public class BMIDetails implements Serializable{
    Double height;
    Double weight;
    Double bmi;
    String output;

    public BMIDetails(){
        height=0.00;
        weight=0.00;
        bmi=0.00;
        output="";
    }
    
    public void setHeight(Double height){
        this.height = (height/100);
    }
    public Double getHeight(){
        return this.height;
    }
    
    public void setWeight(Double weight){
        this.weight = weight;
    }
    public Double getWeight(){
        return this.weight;
    }
 
    public void setBMI(Double height, Double weight){
        this.bmi = (weight/(height*height));
    }
    public Double getBmi() {
        setBMI(height, weight);
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble((df.format(bmi)));
    }
    
    public void setOutput(){
        if(bmi <= 14.99){
            output="Very severely underweight";
        }
        else if(bmi >= 15 && bmi <= 15.99){
            output="Severely underweight";
        }
        else if(bmi >= 16 && bmi <= 18.49){
            output="Underweight";
        }
        else if(bmi >= 15.5 && bmi <= 24.99){
            output="Normal (healthy weight)";
        }
        else if(bmi >= 25 && bmi <= 29.99){
            output="Overweight";
        }
        else if(bmi >= 30 && bmi <= 34.99){
            output="Obese Class I (Moderately obese)";
        }
        else if(bmi >= 35 && bmi <= 39.99){
            output = "Obese Class II (Severely obese)";
        }
        else{
            output = "Obese Class III (Very severely obese)";
        }
    }
    public String getOutput(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        setOutput();
        return this.output;
    }
}
