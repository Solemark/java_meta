package assignment.pkg2;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Car")
public class CarNew extends Car implements Serializable{

    //attributes
    @OneToOne
    private Car car;
    private String warranty;
    private String extendedWarranty;
    private String roadsideAssistance;
    

    public String getWarranty() {
        return warranty;
    }
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getExtendedWarranty() {
        return extendedWarranty;
    }
    public void setExtendedWarranty(String extendedWarranty) {
        this.extendedWarranty = extendedWarranty;
    }

    public String getRoadsideAssistance() {
        return roadsideAssistance;
    }
    public void setRoadsideAssistance(String roadsideAssistance) {
        this.roadsideAssistance = roadsideAssistance;
    }

    @Override
    public String toString() {
        return "\nwarranty: " + warranty + "\nExtended Warranty: " + extendedWarranty + "\nRoadside Assistance: " + roadsideAssistance;
    }
}
