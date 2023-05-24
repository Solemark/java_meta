package assignment.pkg2;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
@NamedQuery(name="Customer.find", query="SELECT c FROM Customer c WHERE c.customerName = :cn")
public class Customer implements Serializable {
    
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerID;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    @OneToMany
    @JoinColumn(name = "customerID")
    private List<Orders> orders;
    
    public int getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + "\nCustomer Name: " + customerName + "\nCustomer Email: " + customerEmail + "\nCustomer Phone: " + customerPhone + "\nCustomer Address: " + customerAddress;
    }
}
