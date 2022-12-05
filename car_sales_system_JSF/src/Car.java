package assignment.pkg2;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Car")
@NamedQuery(name="Car.find", query="SELECT c FROM Car c WHERE c.referenceNumber = :rn")
public class Car implements Serializable {

    //Attributes
    @Id
    private String referenceNumber; //Reference Number (Plate Number)
    private String make;            //Brand
    private String model;
    private String driveType;       //4x4
    private String colour;
    private String transmission;    //Auto/manual
    private String engineType;      //V6, V8
    private String fuelType;        //Unleaded, Diesel
    private String doors;
    private String seats;
    private String price;
    @OneToOne(mappedBy = "Car")
    private CarNew carNew;
    @OneToOne(mappedBy = "Car")       
    private CarUsed carUsed;
    @OneToOne(mappedBy = "Car")
    @JoinColumn(name = "referenceNumber")
    private Orders order;
    
    public String getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;   
    }

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getDriveType() {
        return driveType;
    }
    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngineType() {
        return engineType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getFuelType() {
        return fuelType;
    }
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getDoors() {
        return doors;
    }
    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getSeats() {
        return seats;
    }
    public void setSeats(String seats) {
        this.seats = seats;
    }
    
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reference Number: " + referenceNumber + "\nMake: " + make + "\nModel: " + model + "\nDrive Type: " + driveType + "\nColour: " + colour + "\nTransmission: " + transmission + "\nEngine Type: " + engineType + "\nFuel Type: " + fuelType + "\nDoors: " + doors + "\nSeats: " + seats + "\nPrice: " + price;
    }    
}
