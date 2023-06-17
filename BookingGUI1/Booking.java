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
     * @param numberOfWeeks
     * @param rooms
     * @param address
     * @param bookingDate
     * @param bookingID
     * @param contactNumber
     * @param propertyOwnerName
     */
    public Booking(
            int gardenArea,
            int numberOfWeeks,
            int rooms,
            String address,
            String bookingDate,
            String bookingID,
            String contactNumber,
            String propertyOwnerName) {
        this.gardenArea = gardenArea;
        this.numberOfWeeks = numberOfWeeks;
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
     * @param gardenArea
     * @return void
     */
    public void setGardenArea(int gardenArea) {
        this.gardenArea = gardenArea;
        setGardenAreaCost(gardenArea);
    }

    /**
     * @return int
     */
    public int getGardenArea() {
        return this.gardenArea;
    }

    /**
     * @param gardenArea
     * @return void
     */
    public void setGardenAreaCost(int gardenArea) {
        this.gardenAreaCost = gardenArea * 2;
    }

    /**
     * @return int
     */
    public int getGardenAreaCost() {
        return this.gardenAreaCost;
    }

    /**
     * @param rooms
     * @return void
     */
    public void setRooms(int rooms) {
        this.rooms = rooms;
        setRoomsCost(rooms);
    }

    /**
     * @return int
     */
    public int getRooms() {
        return this.rooms;
    }

    /**
     * @param rooms
     * @return void
     */
    public void setRoomsCost(int rooms) {
        this.roomsCost = rooms * 5;
    }

    /**
     * @return int
     */
    public int getRoomsCost() {
        return this.roomsCost;
    }

    /**
     * @param address
     * @return void
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @param bookingDate
     * @return void
     */
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * @return String
     */
    public String getBookingDate() {
        return this.bookingDate;
    }

    /**
     * @param bookingID
     * @return void
     */
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @return String
     */
    public String getBookingID() {
        return this.bookingID;
    }

    /**
     * @param contactNumber
     * @return void
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * @return String
     */
    public String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * @param propertyOwnerName
     * @return void
     */
    public void setPropertyOwnerName(String propertyOwnerName) {
        this.propertyOwnerName = propertyOwnerName;
    }

    /**
     * @return String
     */
    public String getPropertyOwnerName() {
        return this.propertyOwnerName;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %d %s %s %s %d %d %d %d",
                getClass().getName(),
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
     * @param poolMaintenance
     * @return void
     */
    public void setPoolMaintenance(boolean poolMaintenance) {
        if (this.poolMaintenance = true) {
            luxuryCost = luxuryCost + 50;
        }
    }

    /**
     * @return boolean
     */
    public boolean getPoolMaintenance() {
        return this.poolMaintenance;
    }

    /**
     * @param securityAlarmCheck
     * @return void
     */
    public void setSecurityAlarmCheck(boolean securityAlarmCheck) {
        if (this.securityAlarmCheck = true) {
            luxuryCost = luxuryCost + 50;
        }
    }

    /**
     * @return boolean
     */
    public boolean getSecurityAlarmCheck() {
        return this.securityAlarmCheck;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %d %s %s %s %d %d %d %d %b %b",
                getClass().getName(),
                bookingID,
                bookingDate,
                numberOfWeeks,
                propertyOwnerName,
                contactNumber,
                address,
                rooms,
                gardenArea,
                roomsCost,
                gardenAreaCost,
                poolMaintenance,
                securityAlarmCheck);
    }
}
