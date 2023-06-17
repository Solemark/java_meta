import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookingGUI extends JFrame implements ActionListener {
    ArrayList<Luxury> bookingList = new ArrayList<Luxury>();
    boolean poolMaintenance = false;
    boolean securityAlarmCheck = false;
    int gardenArea = 0;
    int numWeeks = 0;
    int totalCost = 0;
    int rooms = 0;
    final int luxuryCost = 50;
    String address = "";
    String bookingDate = "";
    String bookingID = "";
    String contactNumber = "";
    String propertyOwnerName = "";

    int bookingC = 0;
    int cBooking = 0;
    int i = 0;
    int numberOfWeeks = 0;
    final int MAX_BOOKING = 9;
    String searchID = "";

    JTextArea displayArea = new JTextArea("", 15, 65);

    // checkboxes
    JCheckBox securityCheckBox = new JCheckBox("Security system check $" + luxuryCost, false);
    JCheckBox poolMaintenanceBox = new JCheckBox("Pool cleaning/maintenance $" + luxuryCost, false);

    // buttons
    JButton clearButton = new JButton("Clear");
    JButton bookingButton = new JButton("Booking");
    JButton exitButton = new JButton("Exit");
    JButton searchButton = new JButton("Search");
    JButton editButton = new JButton("Edit");
    JButton editCButton = new JButton("Edit(c)");

    // labels/text
    JLabel bookingIDLabel = new JLabel("Booking ID");
    JLabel nameLabel = new JLabel("Property Owner Name");
    JLabel contactNumberLabel = new JLabel("Contact Number");
    JLabel addressLabel = new JLabel("Address");
    JLabel bookingDateLabel = new JLabel("Booking Date");
    JLabel numWeeksLabel = new JLabel("# of weeks of service");
    JLabel roomsLabel = new JLabel("# of rooms");
    JLabel gardenAreaLabel = new JLabel("Area of Garden m2");
    JLabel priceLabel = new JLabel("The final cost is: ");

    // fields for labels
    JTextField bookingIDField = new JTextField(5);
    JTextField nameField = new JTextField(20);
    JTextField contactNumberField = new JTextField(11);
    JTextField addressField = new JTextField(15);
    JTextField bookingDateField = new JTextField(12);
    JTextField numberOfWeeksField = new JTextField(9);
    JTextField roomsField = new JTextField(14);
    JTextField gardenAreaField = new JTextField(10);
    JTextField priceField = new JTextField(4);

    /**
     * BookingGUI constructor
     */
    public BookingGUI() {
        super("NQ-RE Services Calculator");

        setLayout(new FlowLayout());
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
        add(numberOfWeeksField);
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

        priceField.setText("$" + totalCost);

        clearButton.addActionListener(this);
        bookingButton.addActionListener(this);
        exitButton.addActionListener(this);
        searchButton.addActionListener(this);
        editButton.addActionListener(this);
        editCButton.addActionListener(this);

        readAndDisplayData();

        bookingIDField.setText("B" + bookingC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.compareTo("Clear") == 0) {
            clear();
        } else if (command.compareTo("Booking") == 0) {
            saveData();
        } else if (command.compareTo("Exit") == 0) {
            exit();
        } else if (command.compareTo("Search") == 0) {
            search();
        } else if (command.compareTo("Edit") == 0) {
            edit();
        } else if (command.compareTo("Edit(c)") == 0) {
            editConfirm();
        }
    }

    /**
     * read in data from booking.txt
     * 
     * @return void
     */
    public void readAndDisplayData() {
        try {
            Scanner input = new Scanner(new FileReader("Booking.txt"));

            if (!input.hasNext()) {
                System.out.println("No existing records");
            }

            while (input.hasNext() && input.hasNextLine()) {
                String entry = input.nextLine();
                StringTokenizer inputToken = new StringTokenizer(entry, ",");

                while (inputToken.hasMoreTokens()) {
                    try {
                        bookingID = inputToken.nextToken();
                        bookingDate = inputToken.nextToken();
                        numberOfWeeks = Integer.parseInt(inputToken.nextToken());
                        propertyOwnerName = inputToken.nextToken();
                        contactNumber = inputToken.nextToken();
                        address = inputToken.nextToken();
                        rooms = Integer.parseInt(inputToken.nextToken());
                        gardenArea = Integer.parseInt(inputToken.nextToken());
                        securityAlarmCheck = Boolean.parseBoolean(inputToken.nextToken());
                        poolMaintenance = Boolean.parseBoolean(inputToken.nextToken());
                        saveBooking();
                    } catch (Exception error) {
                        System.out.println(error);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("file load failed!");
        }
    }

    /**
     * clear all GUI fields
     * 
     * @return void
     */
    public void clear() {
        roomsField.setText("");
        gardenAreaField.setText("");
        addressField.setText("");
        contactNumberField.setText("");
        bookingDateField.setText("");
        bookingIDField.setText("B" + bookingC);
        nameField.setText("");
        numberOfWeeksField.setText("");
        securityAlarmCheck = false;
        poolMaintenance = false;
    }

    /**
     * saves input data from the gui
     * 
     * @return void
     */
    public void saveData() {
        // error code for maximum bookings
        if (cBooking >= MAX_BOOKING) {
            JOptionPane.showMessageDialog(
                    null,
                    "Maximum saved booking reached",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // error code for blank booking ID field
        if (bookingIDField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "No booking ID",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            bookingIDField.requestFocus();
            return;
        }

        // error code for blank property owner name field
        if (nameField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null,
                    "No property owner name entered",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }

        // error code for blank contact number field
        if (contactNumberField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "No contact number entered",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            contactNumberField.requestFocus();
            return;
        }

        // error code for blank address field
        if (addressField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "No address entered",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            addressField.requestFocus();
            return;
        }

        // error code for blank booking date field
        if (bookingDateField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "No booking date entered",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            bookingDateField.requestFocus();
            return;
        }

        // error code for blank rooms field
        if (numberOfWeeksField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Number of weeks of service invalid",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            numberOfWeeksField.requestFocus();
            return;
        }

        // error code for blank rooms field
        if (roomsField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid room numbers entered",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            roomsField.requestFocus();
            return;
        }

        // error code for blank garden area
        if (gardenAreaField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid garden area entered",
                    "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            gardenAreaField.requestFocus();
            return;
        }

        // saves data from the GUI
        bookingID = bookingIDField.getText();
        propertyOwnerName = nameField.getText();
        contactNumber = contactNumberField.getText();
        address = addressField.getText();
        bookingDate = bookingDateField.getText();
        numberOfWeeks = Integer.parseInt(numberOfWeeksField.getText());
        rooms = Integer.parseInt(roomsField.getText());
        gardenArea = Integer.parseInt(gardenAreaField.getText());

        // checks if luxury services are selected and saves appropriately
        if (securityCheckBox.isSelected()) {
            securityAlarmCheck = true;
        }
        if (poolMaintenanceBox.isSelected()) {
            poolMaintenance = true;
        }
        if (searchID.equals("")) {
            saveBooking();
        }
        calculation();
    }

    /**
     * performs cost calculations
     * 
     * @return void
     */
    public void calculation() {
        totalCost = 0;
        totalCost += (rooms * 5);
        totalCost += (gardenArea * 2);

        // checks if luxuries have been selected and adds to cost
        if (securityCheckBox.isSelected()) {
            totalCost += luxuryCost;
        }
        if (poolMaintenanceBox.isSelected()) {
            totalCost += luxuryCost;
        }

        // multiplies cost by number of weeks and displays it on GUI
        totalCost = (totalCost * numberOfWeeks);
        priceField.setText("$" + totalCost);
    }

    /**
     * saves booking data
     * 
     * @return void
     */
    public void saveBooking() {
        i = cBooking;
        bookingList.add(i, new Luxury(gardenArea, numberOfWeeks, rooms, address, bookingDate, bookingID, contactNumber,
                propertyOwnerName, poolMaintenance, securityAlarmCheck));

        cBooking++;
        bookingC = (cBooking + 1);

        displayAll();
    }

    /**
     * search for student and display their details
     * 
     * @return void
     */
    public void search() {
        searchID = JOptionPane.showInputDialog(null, "Enter a customer ID or name");

        for (i = 0; i < cBooking; i++) {
            if (i == cBooking) {
                break;
            }
            if (bookingList.get(i).getBookingID().equalsIgnoreCase(searchID)) {
                displayArea.setText("");
                heading();
                displayArea.append(bookingList.get(i).toString());
                return;
            }
            if (bookingList.get(i).getPropertyOwnerName().equalsIgnoreCase(searchID)) {
                displayArea.setText("");
                heading();
                displayArea.append(bookingList.get(i).toString());
                return;
            }
        }
        // invalid search error message
        JOptionPane.showMessageDialog(
                null,
                String.format("%s not found", searchID),
                "NQ-RE Services Calculator",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * edit a booking's details
     * 
     * @return void
     */
    public void edit() {
        i = 0;
        search();
        roomsField.setText("" + bookingList.get(i).getRooms());
        gardenAreaField.setText("" + bookingList.get(i).getGardenArea());
        addressField.setText("" + bookingList.get(i).getAddress());
        contactNumberField.setText("" + bookingList.get(i).getContactNumber());
        bookingDateField.setText("" + bookingList.get(i).getBookingDate());
        bookingIDField.setText(bookingList.get(i).getBookingID());
        nameField.setText("" + bookingList.get(i).getPropertyOwnerName());
        numberOfWeeksField.setText("" + bookingList.get(i).getNumberOfWeeks());
        securityAlarmCheck = bookingList.get(i).getSecurityAlarmCheck();
        poolMaintenance = bookingList.get(i).getPoolMaintenance();

        JOptionPane.showMessageDialog(
                null,
                "Press 'Edit(c)' to confirm changes to data",
                "NQ-RE Services Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        searchID = "";
    }

    /**
     * saves edited data
     * 
     * @return void
     */
    public void editConfirm() {
        // saves booking data
        bookingID = bookingIDField.getText();
        propertyOwnerName = nameField.getText();
        contactNumber = contactNumberField.getText();
        address = addressField.getText();
        bookingDate = bookingDateField.getText();
        numberOfWeeks = Integer.parseInt(numberOfWeeksField.getText());
        rooms = Integer.parseInt(roomsField.getText());
        gardenArea = Integer.parseInt(gardenAreaField.getText());

        // checks if luxury services are selected and saves appropriately
        if (securityCheckBox.isSelected()) {
            securityAlarmCheck = true;
        }
        if (poolMaintenanceBox.isSelected()) {
            poolMaintenance = true;
        }

        bookingList.set(i, new Luxury(
                gardenArea,
                numberOfWeeks,
                rooms,
                address,
                bookingDate,
                bookingID,
                contactNumber,
                propertyOwnerName,
                poolMaintenance,
                securityAlarmCheck));

        displayAll();
    }

    /**
     * display TextBox heading
     * 
     * @return void
     */
    public void heading() {
        displayArea.setText(
                String.format("%1s%15s%10s%10s%20s%15s%10s%20s%20s", "BookingID,", "Booking Date,", "#Weeks,", "Name,",
                        "Contact Number,", "Address,", "Rooms,", "Garden Area,", "Security Check,", "Pool Maint."));
    }

    /**
     * display all user data in TextBox
     * 
     * @return void
     */
    public void displayAll() {
        displayArea.setText("");
        heading();
        for (i = 0; i < bookingList.size(); i++) {
            displayArea.append("\n" + bookingList.get(i));
        }
    }

    /**
     * saves the data and exits the program
     * 
     * @return void
     */
    public void exit() {
        try {
            Formatter out = new Formatter("Booking.txt");

            for (Luxury booking : bookingList) {
                out.format("%s\n", booking.toString());
            }
            out.close();
            clear();
            System.out.println("Data successfully saved");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR! Unable to save file!");
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        BookingGUI f = new BookingGUI();
        final int width = 735;
        final int height = 430;
        f.setSize(width, height);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
