import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookingGUI extends JFrame implements ActionListener {
    ArrayList<Booking> bookingList = new ArrayList<Booking>();
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

    // checkboxes
    JCheckBox securityCheckBox = new JCheckBox("Security system check $" + luxuryCost, false);
    JCheckBox poolMaintenanceBox = new JCheckBox("Pool cleaning/maintenance $" + luxuryCost, false);

    // buttons
    JButton clearButton = new JButton("Clear");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    // labels/text
    JLabel titleLabel = new JLabel("NQ-RE Services Calculator");
    JLabel bookingIDLabel = new JLabel("Booking ID");
    JLabel nameLabel = new JLabel("Property Owner Name");
    JLabel contactNumberLabel = new JLabel("Contact Number");
    JLabel addressLabel = new JLabel("Address");
    JLabel bookingDateLabel = new JLabel("Booking Date");
    JLabel numWeeksLabel = new JLabel("# of weeks of service");
    JLabel roomsLabel = new JLabel("# of rooms");
    JLabel gardenAreaLabel = new JLabel("Area of Garden m�");
    JLabel priceLabel = new JLabel("The final cost is: ");

    // fields for labels
    JTextField bookingIDField = new JTextField(5);
    JTextField nameField = new JTextField(20);
    JTextField contactNumberField = new JTextField(11);
    JTextField addressField = new JTextField(15);
    JTextField bookingDateField = new JTextField(12);
    JTextField numWeeksField = new JTextField(9);
    JTextField roomsField = new JTextField(14);
    JTextField gardenAreaField = new JTextField(10);
    JTextField priceField = new JTextField(4);

    /**
     * BookingGUI class constructor
     */
    public BookingGUI() {
        // display title on window top
        super("NQ-RE Services calculator");
        setLayout(new FlowLayout());

        // format title
        titleLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        // display java elements
        add(titleLabel);
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
        add(saveButton);
        add(exitButton);
        add(priceLabel);
        add(priceField);

        // will display starting price, $0
        priceField.setText("$" + totalCost);

        // allows button pushes to be detected
        clearButton.addActionListener(this);
        saveButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    /**
     * handles all buttonclicks
     * 
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Clear":
                clear();
                break;
            case "Save":
                save();
                break;
            case "Exit":
                exit();
                break;
            default:
                break;
        }
    }

    /**
     * clears all fields and sets luxury services to false
     * 
     * @return void
     */
    public void clear() {
        roomsField.setText("");
        gardenAreaField.setText("");
        addressField.setText("");
        contactNumberField.setText("");
        bookingDateField.setText("");
        numWeeksField.setText("");
        bookingIDField.setText("");
        nameField.setText("");
        priceField.setText("$" + 0);
        securityCheckBox.setSelected(false);
        poolMaintenanceBox.setSelected(false);

        securityAlarmCheck = false;
        poolMaintenance = false;
    }

    /**
     * validate and save data
     * 
     * @return void
     */
    public void save() {
        // error code for blank booking ID field
        if (bookingIDField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "No booking ID", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            bookingIDField.requestFocus();
            return;
        }

        // error code for blank property owner name field
        if (nameField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "No property owner name entered", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return;
        }

        // error code for blank contact number field
        if (contactNumberField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "No contact number entered", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            contactNumberField.requestFocus();
            return;
        }

        // error code for blank address field
        if (addressField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "No address entered", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            addressField.requestFocus();
            return;
        }

        // error code for blank booking date field
        if (bookingDateField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "No booking date entered", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            bookingDateField.requestFocus();
            return;
        }

        // error code for blank rooms field
        if (numWeeksField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "Number of weeks of service invalid", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            numWeeksField.requestFocus();
            return;
        }

        // error code for blank rooms field
        if (roomsField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "Invalid room numbers entered", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            roomsField.requestFocus();
            return;
        }

        // error code for blank garden area
        if (gardenAreaField.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, "Invalid garden area entered", "NQ-RE Services Calculator",
                    JOptionPane.ERROR_MESSAGE);
            gardenAreaField.requestFocus();
            return;
        }

        // saves data
        bookingID = bookingIDField.getText();
        propertyOwnerName = nameField.getText();
        contactNumber = contactNumberField.getText();
        address = addressField.getText();
        bookingDate = bookingDateField.getText();
        numWeeks = Integer.parseInt(numWeeksField.getText());
        rooms = Integer.parseInt(roomsField.getText());
        gardenArea = Integer.parseInt(gardenAreaField.getText());

        poolMaintenance = poolMaintenanceBox.isSelected();
        securityAlarmCheck = securityCheckBox.isSelected();

        // checks if luxury services are selected and saves appropriately
        if (!poolMaintenance && !securityAlarmCheck) {
            bookingList.add(createNewBooking());
        } else {
            bookingList.add(createNewLuxury());
        }
        calculation();
        for (Booking booking : bookingList) {
            System.out.println(booking);
        }
        clear();
    }

    /**
     * performs cost calculation
     * 
     * @return void
     */
    public void calculation() {
        totalCost = 0;
        totalCost += (rooms * 5);
        totalCost += (gardenArea * 2);

        if (securityCheckBox.isSelected()) {
            totalCost += luxuryCost;
        }
        if (poolMaintenanceBox.isSelected()) {
            totalCost += luxuryCost;
        }

        totalCost = (totalCost * numWeeks);
        priceField.setText("$" + totalCost);
    }

    /**
     * create new booking object
     * 
     * @return Booking
     */
    public Booking createNewBooking() {
        return new Booking(
                gardenArea,
                numWeeks,
                rooms,
                address,
                bookingDate,
                bookingID,
                contactNumber,
                propertyOwnerName);
    }

    /**
     * create a new luxury object
     * 
     * @return Luxury
     */
    public Luxury createNewLuxury() {
        return new Luxury(
                gardenArea,
                numWeeks,
                rooms,
                address,
                bookingDate,
                bookingID,
                contactNumber,
                propertyOwnerName,
                poolMaintenance,
                securityAlarmCheck);
    }

    /**
     * exit's the application without error
     * 
     * @return void
     */
    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        BookingGUI f = new BookingGUI(); // Create class instance
        final int width = 250; // Window width variable
        final int height = 400; // Window height variable
        f.setSize(width, height); // Define size of window
        f.setVisible(true); // Make the application visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow the code to close the program
    }
}
