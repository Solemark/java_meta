package assignment.pkg2;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
@NamedQuery(name="Orders.find", query="SELECT o FROM Orders o INNER JOIN Customer c1 ON o.customerID = c1.customerID WHERE c1.customerName = :cn")
public class Orders implements Serializable {

    @Id
    private String referenceNumber;
    private int customerID;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Car car;

    public String getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    } 

    @Override
    public String toString() {
        return "Reference Number: " + referenceNumber + "\nCustomer ID: " + customerID;
    }
}
