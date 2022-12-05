/*
COIT11134 - Assignment 2
File 1 of 2
Student ID: s0257989
Student Name: Mason Larcombe
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

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
    final int MAX_BOOKING=9;
    int i=0;
    int cBooking=0;
    int bookingC;
    String strNumWeeks;
    String strRooms;
    String strGardenArea;
    String strSecurityAlarmCheck="";
    String strPoolMaintenance="";
    String entry = "";
    String bAS="";
    String searchID="";

    Luxury[] bookingArray=new Luxury[MAX_BOOKING];
    Booking[] searchArray=new Booking[MAX_BOOKING];
    ArrayList<String> bookingList=new ArrayList<>();

    JTextArea displayArea=new JTextArea("",15,65);

    JCheckBox securityCheckBox=new JCheckBox("Security system check $"+luxuryCost,false);           //checkboxes
    JCheckBox poolMaintenanceBox=new JCheckBox("Pool cleaning/maintenance $"+luxuryCost,false);

    JButton clearButton=new JButton("Clear");           //buttons
    JButton bookingButton=new JButton("Booking");
    JButton exitButton=new JButton("Exit");
    JButton searchButton=new JButton("Search");
    JButton editButton=new JButton("Edit");
    JButton editCButton=new JButton("Edit(c)");

    JLabel bookingIDLabel=new JLabel("Booking ID");                 //labels/text
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
        super("NQ-RE Services Calculator");                         //display title on window top

        setLayout(new FlowLayout());                                //display java elements
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

        add(displayArea);
        add(bookingButton);
        add(searchButton);
        add(editButton);
        add(editCButton);
        add(priceLabel);
        add(priceField);
        add(clearButton);
        add(exitButton);

        priceField.setText("$"+totalCost);          //will display starting price, $0

        clearButton.addActionListener(this);        //allows button pushes to be detected
        bookingButton.addActionListener(this);
        exitButton.addActionListener(this);
        searchButton.addActionListener(this);
        editButton.addActionListener(this);
        editCButton.addActionListener(this);

        readAndDisplayData();

        bookingIDField.setText("B"+bookingC);
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
        else if(command.compareTo("Search")==0)
            search();
        else if(command.compareTo("Edit")==0)
            edit();
        else if(command.compareTo("Edit(c)")==0)
            editConfirm();
    } // actionPerformed

    public void readAndDisplayData()
    {
        try
        {
            Scanner in=new Scanner(new FileReader("Booking.txt"));

            if(!in.hasNext())
            {
                System.out.println("No existing records");
            }

            while(in.hasNext() && in.hasNextLine())
            {
                entry=in.nextLine();
                System.out.println(entry);
                StringTokenizer st=new StringTokenizer(entry,",");

                while(st.hasMoreTokens())
                {
                    bookingID=st.nextToken();
                    bookingDate=st.nextToken();
                    strNumWeeks=st.nextToken();
                    propertyOwnerName=st.nextToken();
                    contactNumber=st.nextToken();
                    address=st.nextToken();
                    strRooms=st.nextToken();
                    strGardenArea=st.nextToken();
                    strSecurityAlarmCheck=st.nextToken();
                    strPoolMaintenance=st.nextToken();

                    try
                    {
                        numWeeks=Integer.parseInt(strNumWeeks);
                        rooms=Integer.parseInt(strRooms);
                        gardenArea=Integer.parseInt(strGardenArea);
                        securityAlarmCheck=Boolean.parseBoolean(strSecurityAlarmCheck);
                        poolMaintenance=Boolean.parseBoolean(strPoolMaintenance);
                        saveBooking();
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("File reading error (integer/boolean)");
                    }
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println("file load failed!");
        }
    }

    public void clear()     //clears all fields and sets luxury services to false
    {
        roomsField.setText("");
        gardenAreaField.setText("");
        addressField.setText("");
        contactNumberField.setText("");
        bookingDateField.setText("");
        bookingIDField.setText("B"+bookingC);
        nameField.setText("");
        numWeeksField.setText("");
        securityAlarmCheck=false;
        poolMaintenance=false;
    }

    public void saveData()
    {
        if(cBooking>=MAX_BOOKING)       //error code for maximum bookings
        {
            JOptionPane.showMessageDialog(null,"Maximum saved booking reached","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(bookingIDField.getText().compareTo("")==0) //error code for blank booking ID field
        {
            JOptionPane.showMessageDialog(null,"No booking ID","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            bookingIDField.requestFocus();
            return;
        }

        if(nameField.getText().compareTo("")==0) //error code for blank property owner name field
        {
            JOptionPane.showMessageDialog(null,"No property owner name entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }

        if(contactNumberField.getText().compareTo("")==0) //error code for blank contact number field
        {
            JOptionPane.showMessageDialog(null,"No contact number entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            contactNumberField.requestFocus();
            return;
        }

        if(addressField.getText().compareTo("")==0) //error code for blank address field
        {
            JOptionPane.showMessageDialog(null,"No address entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            addressField.requestFocus();
            return;
        }

        if(bookingDateField.getText().compareTo("")==0) //error code for blank booking date field
        {
            JOptionPane.showMessageDialog(null,"No booking date entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            bookingDateField.requestFocus();
            return;
        }

        if(numWeeksField.getText().compareTo("")==0) //error code for blank rooms field
        {
            JOptionPane.showMessageDialog(null,"Number of weeks of service invalid","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            numWeeksField.requestFocus();
            return;
        }

        if(roomsField.getText().compareTo("")==0) //error code for blank rooms field
        {
            JOptionPane.showMessageDialog(null,"Invalid room numbers entered","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);
            roomsField.requestFocus();
            return;
        }

        if(gardenAreaField.getText().compareTo("")==0) //error code for blank garden area
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
           securityAlarmCheck=true;
        }
        if(poolMaintenanceBox.isSelected())
        {
            poolMaintenance=true;
        }

        if(searchID.equals(""))
            {
                saveBooking();
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

    public void saveBooking()      //saves booking data
    {
        i=cBooking;
        searchArray[i]=new Booking(bookingID,bookingDate,numWeeks,propertyOwnerName,contactNumber,address,rooms,gardenArea);
        bookingArray [i]=new Luxury(bookingID,bookingDate,numWeeks,propertyOwnerName,contactNumber,address,rooms,gardenArea,securityAlarmCheck,poolMaintenance);

        bAS=(bookingID+","+bookingDate+","+strNumWeeks+","+propertyOwnerName+","+contactNumber+","+address+","+strRooms+","+strGardenArea+","+strSecurityAlarmCheck+","+strPoolMaintenance);
        bookingList.add(i, bAS);

        cBooking++;
        bookingC=(cBooking+1);

        displayAll();
    }

    public void search()
    {
        searchID=JOptionPane.showInputDialog(null, "Enter a customer ID or name");     //student search prompt

        for(i=0;i<cBooking;i++)   //checks database for stored student data
        {
            if(i==cBooking)       //if unable to find stored student data loop ends
            {
                break;
            }
            if(searchArray[i].getBookingID().equalsIgnoreCase(searchID))
            {
                displayArea.setText("");
                heading();
                displayArea.append(bookingList.get(i));
                return;
            }
            if(searchArray[i].getPropertyOwnerName().equalsIgnoreCase(searchID))
            {
                displayArea.setText("");
                heading();
                displayArea.append(bookingList.get(i));
                return;
            }
        }
        JOptionPane.showMessageDialog(null,searchID+" not found","NQ-RE Services Calculator",JOptionPane.ERROR_MESSAGE);    //invalid search error message
    } // search

    public void edit()
    {
        i=0;
        search();
        roomsField.setText(""+searchArray[i].getRooms());
        gardenAreaField.setText(""+searchArray[i].getGardenArea());
        addressField.setText(""+searchArray[i].getAddress());
        contactNumberField.setText(""+searchArray[i].getContactNumber());
        bookingDateField.setText(""+searchArray[i].getBookingDate());
        bookingIDField.setText(searchArray[i].getBookingID());
        nameField.setText(""+searchArray[i].getPropertyOwnerName());
        numWeeksField.setText(""+searchArray[i].getNumWeeks());
        securityAlarmCheck=bookingArray[i].getSecurityAlarmCheck();
        poolMaintenance=bookingArray[i].getPoolMaintenance();

        JOptionPane.showMessageDialog(null, "Press 'Edit(c)' to confirm changes to data","NQ-RE Services Calculator",JOptionPane.INFORMATION_MESSAGE);
        searchID="";
    }

    public void editConfirm()
    {
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
           securityAlarmCheck=true;
        }
        if(poolMaintenanceBox.isSelected())
        {
            poolMaintenance=true;
        }

        bAS=(bookingID+","+bookingDate+","+numWeeks+","+propertyOwnerName+","+contactNumber+","+address+","+rooms+","+gardenArea+","+securityAlarmCheck+","+poolMaintenance);
        bookingList.set(i, bAS);

        searchArray[i].setBookingID(bookingID);
        searchArray[i].setBookingDate(bookingDate);
        searchArray[i].setNumWeeks(numWeeks);
        searchArray[i].setPropertyOwnerName(propertyOwnerName);
        searchArray[i].setContactNumber(contactNumber);
        searchArray[i].setAddress(address);
        searchArray[i].setRooms(rooms);
        searchArray[i].setGardenArea(gardenArea);

        bookingArray[i].setBookingID(bookingID);
        bookingArray[i].setBookingDate(bookingDate);
        bookingArray[i].setNumWeeks(numWeeks);
        bookingArray[i].setPropertyOwnerName(propertyOwnerName);
        bookingArray[i].setContactNumber(contactNumber);
        bookingArray[i].setAddress(address);
        bookingArray[i].setRooms(rooms);
        bookingArray[i].setGardenArea(gardenArea);
        bookingArray[i].setSecurityAlarmCheck(securityAlarmCheck);
        bookingArray[i].setPoolMaintenance(poolMaintenance);

        displayAll();
    }

    public void heading()
    {
        displayArea.setText(String.format("%1s%15s%10s%10s%20s%15s%10s%20s%20s","BookingID,","Booking Date,","#Weeks,","Name,","Contact Number,","Address,","Rooms,","Garden Area,","Security Check,","Pool Maint."));
    }

    public void displayAll()
    {
        displayArea.setText("");
        heading();
        for(i=0;i<bookingList.size();i++){
            displayArea.append("\n"+bookingList.get(i));
        }
    }

    public void exit()  //closes the program
    {
        try
        {
            Formatter out=new Formatter("Booking.txt");

            for(String c:bookingList)
            {
                out.format("%s\n",c.toString());
            }
            out.close();
            clear();
            System.out.println("Data successfully saved");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("ERROR! Unable to save file!");
        }

        System.exit(0);
    }

    public static void main(String[] args)
    {
        BookingGUI f=new BookingGUI();                      //Create class instance
	final int width=735;                                //Window width variable
	final int height=430;                               //Window height variable
	f.setSize(width,height);                            //Define size of window
	f.setVisible(true);                                 // Make the application visible
        f.setResizable(false);                              // User cannot mess with window size
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // allow the code to close the program
    }
}
