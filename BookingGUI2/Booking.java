public class Booking {
    private String bookingID;
    private String bookingDate;
    private int numberOfWeeks;
    private String propertyOwnerName;
    private String contactNumber;
    private String address;
    private int rooms;
    private int roomsCost = 0;
    private int gardenArea;
    private int gardenAreaCost = 0;

    /**
     * Booking constructor
     * 
     * @param bookingID
     * @param bookingDate
     * @param numberOfWeeks
     * @param propertyOwnerName
     * @param contactNumber
     * @param address
     * @param rooms
     * @param gardenArea
     */
    public Booking(String bookingID, String bookingDate, int numberOfWeeks, String propertyOwnerName,
            String contactNumber,
            String address, int rooms, int gardenArea) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.numberOfWeeks = numberOfWeeks;
        this.propertyOwnerName = propertyOwnerName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.rooms = rooms;
        this.gardenArea = gardenArea;

        setRoomsCost(rooms);
        setGardenAreaCost(gardenArea);
    }

    /**
     * set current room cost based on number of rooms
     * 
     * @param rooms
     * @return void
     */
    public void setRoomsCost(int rooms) {
        this.roomsCost = rooms * 5;
    }

    /**
     * get the rooms cost
     * 
     * @return int
     */
    public int getRoomsCost() {
        return this.roomsCost;
    }

    /**
     * set current garden area cost based on the garden area
     * 
     * @param gardenArea
     * @return void
     */
    public void setGardenAreaCost(int gardenArea) {
        this.gardenAreaCost = gardenArea * 2;
    }

    /**
     * get the garden area cost
     * 
     * @return int
     */
    public int getGardenAreaCost() {
        return this.gardenAreaCost;
    }

    /**
     * set current booking id
     * 
     * @param bookingID
     * @return void
     */
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * get current booking id
     * 
     * @return String
     */
    public String getBookingID() {
        return this.bookingID;
    }

    /**
     * set current booking date
     * 
     * @param bookingDate
     * @return void
     */
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * get current booking date
     * 
     * @return String
     */
    public String getBookingDate() {
        return this.bookingDate;
    }

    /**
     * set current property owner name
     * 
     * @param propertyOwnerName
     * @return void
     */
    public void setPropertyOwnerName(String propertyOwnerName) {
        this.propertyOwnerName = propertyOwnerName;
    }

    /**
     * get current property owner name
     * 
     * @return String
     */
    public String getPropertyOwnerName() {
        return this.propertyOwnerName;
    }

    /**
     * set current contact number
     * 
     * @param contactNumber
     * @return void
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * get current contact number
     * 
     * @return String
     */
    public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * set current address
     * 
     * @param address
     * @return void
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get current address
     * 
     * @return String
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * set current rooms
     * 
     * @param rooms
     * @return void
     */
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    /**
     * get current rooms
     * 
     * @return int
     */
    public int getRooms() {
        return this.rooms;
    }

    /**
     * set current garden area
     * 
     * @param gardenArea
     * @return void
     */
    public void setGardenArea(int gardenArea) {
        this.gardenArea = gardenArea;
    }

    /**
     * get current garden area
     * 
     * @return int
     */
    public int getGardenArea() {
        return this.gardenArea;
    }

    /**
     * set current number of weeks
     * 
     * @param numberOfWeeks
     * @return void
     */
    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    /**
     * get current number of weeks
     * 
     * @return int
     */
    public int getNumberOfWeeks() {
        return this.numberOfWeeks;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %s %s %s %d %d %d %d",
                bookingID,
                bookingDate,
                numberOfWeeks,
                propertyOwnerName,
                contactNumber,
                address,
                rooms,
                gardenArea,
                roomsCost,
                gardenAreaCost);
    }
}

class Luxury extends Booking {
    private boolean securityAlarmCheck;
    private boolean poolMaintenance;
    private int luxuryCost = 0;

    /**
     * Luxury constructor
     * 
     * @param bookingID
     * @param bookingDate
     * @param numWeeks
     * @param propertyOwnerName
     * @param contactNumber
     * @param address
     * @param rooms
     * @param gardenArea
     * @param securityAlarmCheck
     * @param poolMaintenance
     */
    public Luxury(String bookingID, String bookingDate, int numWeeks, String propertyOwnerName, String contactNumber,
            String address, int rooms, int gardenArea, boolean securityAlarmCheck, boolean poolMaintenance) {
        super(bookingID, bookingDate, numWeeks, propertyOwnerName, contactNumber, address, rooms, gardenArea);
        this.securityAlarmCheck = securityAlarmCheck;
        this.poolMaintenance = poolMaintenance;
    }

    /**
     * set current security alarm check status
     * 
     * @param securityAlarmCheck
     * @return void
     */
    public void setSecurityAlarmCheck(boolean securityAlarmCheck) {
        if (this.securityAlarmCheck = true) {
            luxuryCost = luxuryCost + 50;
        }
    }

    /**
     * get current security alarm check status
     * 
     * @return boolean
     */
    public boolean getSecurityAlarmCheck() {
        return this.securityAlarmCheck;
    }

    /**
     * set pool maintenance status
     * 
     * @param poolMaintenance
     * @return void
     */
    public void setPoolMaintenance(boolean poolMaintenance) {
        if (this.poolMaintenance = true) {
            luxuryCost = luxuryCost + 50;
        }
    }

    /**
     * get pool maintenance status
     * 
     * @return boolean
     */
    public boolean getPoolMaintenance() {
        return this.poolMaintenance;
    }

    @Override
    public String toString() {
        return String.format("%b %b", securityAlarmCheck, poolMaintenance);
    }
}
