package assignment.pkg2;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Car")
public class CarUsed extends Car implements Serializable {

    //attributes
    @OneToOne
    private Car car;
    private String odometer;
    private String regoNo;
    private String serviceHistory;
    private String vehicleIdentificationNumber;
    

    public String getOdometer() {
        return odometer;
    }
    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getRegoNo() {
        return regoNo;
    }
    public void setRegoNo(String regoNo) {
        this.regoNo = regoNo;
    }

    public String getServiceHistory() {
        return serviceHistory;
    }
    public void setServiceHistory(String serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }
    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }   

    @Override
    public String toString() {
        return "\nOdometer: " + odometer + "\nRego No: " + regoNo + "\nService History: " + serviceHistory + "\nVehicle Identification Number: " + vehicleIdentificationNumber;
    }
}
