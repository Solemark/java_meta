public class Booking {
    protected int gardenArea = 0;
    protected int gardenAreaCost = 0;
    protected int numberOfWeeks = 0;
    protected int rooms = 0;
    protected int roomsCost = 0;
    protected String address = "";
    protected String bookingDate = "";
    protected String bookingID = "";
    protected String contactNumber = "";
    protected String propertyOwnerName = "";

    /**
     * Booking class constructor
     * 
     * @param gardenArea
     * @param numWeeks
     * @param rooms
     * @param address
     * @param bookingDate
     * @param bookingID
     * @param contactNumber
     * @param propertyOwnerName
     */
    public Booking(
            int gardenArea,
            int numWeeks,
            int rooms,
            String address,
            String bookingDate,
            String bookingID,
            String contactNumber,
            String propertyOwnerName) {
        this.gardenArea = gardenArea;
        this.numberOfWeeks = numWeeks;
        this.rooms = rooms;
        this.address = address;
        this.bookingDate = bookingDate;
        this.bookingID = bookingID;
        this.contactNumber = contactNumber;
        this.propertyOwnerName = propertyOwnerName;

        setGardenAreaCost(gardenArea);
        setRoomsCost(rooms);
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
     * Luxury class constructor
     * 
     * @param gardenArea
     * @param numberOfWeeks
     * @param rooms
     * @param address
     * @param bookingDate
     * @param bookingID
     * @param contactNumber
     * @param propertyOwnerName
     * @param poolMaintenance
     * @param securityAlarmCheck
     */
    public Luxury(
            int gardenArea,
            int numberOfWeeks,
            int rooms,
            String address,
            String bookingDate,
            String bookingID,
            String contactNumber,
            String propertyOwnerName,
            boolean poolMaintenance,
            boolean securityAlarmCheck) {
        super(gardenArea, numberOfWeeks, rooms, address, bookingDate, bookingID, contactNumber, propertyOwnerName);
        this.poolMaintenance = poolMaintenance;
        this.securityAlarmCheck = securityAlarmCheck;
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
