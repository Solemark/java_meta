/*
COIT11134 - Assignment 1
File 1 of 2
Student ID: s0257989
Student Name: Mason Larcombe
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookingGUI extends JFrame implements ActionListener
{
    String bookingID;
    String bookingDate;
    int numWeeks=0;
    String propertyOwnerName;
    String contactNumber;
    String address;
    int rooms;
    int gardenArea;
    boolean securityAlarmCheck=false;
    boolean poolMaintenance=false;
    int totalCost;
    final int luxuryCost=50;

    JCheckBox securityCheckBox=new JCheckBox("Security system check $"+luxuryCost,false);           //checkboxes
    JCheckBox poolMaintenanceBox=new JCheckBox("Pool cleaning/maintenance $"+luxuryCost,false);

    JButton clearButton=new JButton("Clear");           //buttons
    JButton bookingButton=new JButton("Booking");
    JButton exitButton=new JButton("Exit");

    JLabel titleLabel=new JLabel("NQ-RE Services Calculator");      //labels/text
    JLabel bookingIDLabel=new JLabel("Booking ID");
    JLabel nameLabel=new JLabel("Property Owner Name");
    JLabel contactNumberLabel=new JLabel("Contact Number");
    JLabel addressLabel=new JLabel("Address");
    JLabel bookingDateLabel=new JLabel("Booking Date");
    JLabel numWeeksLabel=new JLabel("# of weeks of service");
    JLabel roomsLabel=new JLabel("# of rooms");
    JLabel gardenAreaLabel=new JLabel("Area of Garden m²");
    JLabel priceLabel=new JLabel("The final cost is: ");

    JTextField bookingIDField=new JTextField(5);        //fields for labels
    JTextField nameField=new JTextField(20);
    JTextField contactNumberField=new JTextField(11);
    JTextField addressField=new JTextField(15);
    JTextField bookingDateField=new JTextField(12);
    JTextField numWeeksField=new JTextField(9);
    JTextField roomsField=new JTextField(14);
    JTextField gardenAreaField=new JTextField(10);
    JTextField priceField=new JTextField(4);

    public BookingGUI()
    {
        super("NQ-RE Services calculator");                         //display title on window top
        setLayout(new FlowLayout());

        titleLabel.setFont(new Font("Ariel", Font.BOLD, 15));       //format title
        add(titleLabel);                                            //display java elements
        add(bookingIDLabel);
        add(bookingIDField);
        add(nameLabel);
        add(nameField);
        add(contactNumberLabel);
        add(contactNumberField);
        add(addressLabel);
        add(addressField);

        add(bookingDateLabel);
        add(bookingDateField);
        add(numWeeksLabel);
        add(numWeeksField);
        add(roomsLabel);
        add(roomsField);
        add(gardenAreaLabel);
        add(gardenAreaField);

        add(securityCheckBox);
        add(poolMaintenanceBox);
        add(clearButton);
        add(bookingButton);
        add(exitButton);
        add(priceLabel);
        add(priceField);

        priceField.setText("$"+totalCost);          //will display starting price, $0

        clearButton.addActionListener(this);        //allows button pushes to be detected
        bookingButton.addActionListener(this);
        exitButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e)  // handles all buttonclicks
    {
	String command = e.getActionCommand();

        if(command.compareTo("Clear")==0)
            clear();
        else if(command.compareTo("Booking")==0)
            saveData();
        else if(command.compareTo("Exit")==0)
            exit();
    } // actionPerformed

    public void clear()     //clears all fields and sets luxury services to false
    {
        roomsField.setText("");
        gardenAreaField.setText("");
        addressField.setText("");
        contactNumberField.setText("");
        bookingDateField.setText("");
        bookingIDField.setText("");
        nameField.setText("");
        priceField.setText("$"+0);

        securityAlarmCheck=false;
        poolMaintenance=false;
    }

    public void saveData()
    {
        if (bookingIDField.getText().compareTo("")==0) //error code for blank booking ID field
        {
            JOptionPane.showMessageDialog(null,"No booking ID","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            bookingIDField.requestFocus();
            return;
        }

        if (nameField.getText().compareTo("")==0) //error code for blank property owner name field
        {
            JOptionPane.showMessageDialog(null,"No property owner name entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }

        if (contactNumberField.getText().compareTo("")==0) //error code for blank contact number field
        {
            JOptionPane.showMessageDialog(null,"No contact number entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            contactNumberField.requestFocus();
            return;
        }

        if (addressField.getText().compareTo("")==0) //error code for blank address field
        {
            JOptionPane.showMessageDialog(null,"No address entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            addressField.requestFocus();
            return;
        }

        if (bookingDateField.getText().compareTo("")==0) //error code for blank booking date field
        {
            JOptionPane.showMessageDialog(null,"No booking date entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            bookingDateField.requestFocus();
            return;
        }

        if (numWeeksField.getText().compareTo("")==0) //error code for blank rooms field
        {
            JOptionPane.showMessageDialog(null,"Number of weeks of service invalid","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            numWeeksField.requestFocus();
            return;
        }

        if (roomsField.getText().compareTo("")==0) //error code for blank rooms field
        {
            JOptionPane.showMessageDialog(null,"Invalid room numbers entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            roomsField.requestFocus();
            return;
        }

        if (gardenAreaField.getText().compareTo("")==0) //error code for blank garden area
        {
            JOptionPane.showMessageDialog(null,"Invalid garden area entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            gardenAreaField.requestFocus();
            return;
        }

        bookingID=bookingIDField.getText();                         //saves booking ID
        propertyOwnerName=nameField.getText();                      //saves property owner name
        contactNumber=contactNumberField.getText();                 //saves contact number
        address=addressField.getText();                             //saves address
        bookingDate=bookingDateField.getText();                     //saves booking date
        numWeeks=Integer.parseInt(numWeeksField.getText());         //saves number of weeks
        rooms=Integer.parseInt(roomsField.getText());               //saves number of rooms
        gardenArea=Integer.parseInt(gardenAreaField.getText());     //saves garden area

        if(securityCheckBox.isSelected())           //checks if luxury services are selected and saves appropriately
        {
            if(poolMaintenanceBox.isSelected())
            {
                securityAlarmCheck=true;
                poolMaintenance=true;
                luxury();
            }
            else
            {
                securityAlarmCheck=true;
                luxury();
            }
        }
        else if(poolMaintenanceBox.isSelected())
        {
            if(securityCheckBox.isSelected())
            {
                poolMaintenance=true;
                securityAlarmCheck=true;
                luxury();
            }
            else
            {
                poolMaintenance=true;
                luxury();
            }
        }
        else
        {
            bookingA();
        }

        calculation();
    }

    public void calculation()                   //performs cost calculation
    {
        totalCost=0;                            //resets total cost

        totalCost+=(rooms*5);                   //calculates room cost
        totalCost+=(gardenArea*2);              //calculates garden area cost

        if(securityCheckBox.isSelected())       //checks if security system checkbox has been selected
        {
            totalCost+=luxuryCost;
        }
        if(poolMaintenanceBox.isSelected())     //checks if pool maintenance checkbox has been selected
        {
            totalCost+=luxuryCost;
        }

        totalCost=(totalCost*numWeeks);         //calculates total cost of service
        priceField.setText("$"+totalCost);      //outputs total cost of service
    }

    public void bookingA()      //saves a normal booking
    {
        Booking b1=new Booking(bookingID,bookingDate,numWeeks,propertyOwnerName,contactNumber,address,rooms,gardenArea);
    }

    public void luxury()    //saves a luxury
    {
        Booking l1=new Luxury(bookingID,bookingDate,numWeeks,propertyOwnerName,contactNumber,address,rooms,gardenArea,securityAlarmCheck,poolMaintenance);
    }

    public void exit()  //closes the program
    {
        System.exit(0);
    }

    public static void main(String[] args)
    {
        BookingGUI f=new BookingGUI();                      //Create class instance
		final int width=250;                                //Window width variable
		final int height=400;                               //Window height variable
		f.setSize(width,height);                            //Define size of window
		f.setVisible(true);                                 // Make the application visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // allow the code to close the program
    }
}
