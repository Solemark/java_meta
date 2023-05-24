/*
COIT11134 - Assignment 2
File 2 of 2
Student ID: s0257989
Student Name: Mason Larcombe
*/
public class Booking
{
    private String bookingID;
    private String bookingDate;
    private int numWeeks;
    private String propertyOwnerName;
    private String contactNumber;
    private String address;
    private int rooms;
    private int roomsCost=0;
    private int gardenArea;
    private int gardenAreaCost=0;

    public Booking(String bookingID, String bookingDate,int numWeeks, String propertyOwnerName, String contactNumber, String address, int rooms, int gardenArea)    //paramatised constructor for bookings
    {
        this.bookingID=bookingID;
        this.bookingDate=bookingDate;
        this.numWeeks=numWeeks;
        this.propertyOwnerName=propertyOwnerName;
        this.contactNumber=contactNumber;
        this.address=address;
        this.rooms=rooms;
        this.gardenArea=gardenArea;

        setRoomsCost(rooms);        //begins room cost calculation
        setGardenAreaCost(gardenArea);      //begins garden area cost calculation
    }

    public void setRoomsCost(int rooms)     //room cost calculation, mutator method
    {
        this.roomsCost=rooms*5;
    }

    public int getRoomsCost()               //room cost accessor method
    {
        return this.roomsCost;
    }

    public void setGardenAreaCost(int gardenArea)       //garden area cost calculation, mutator method
    {
        this.gardenAreaCost=gardenArea*2;
    }

    public int getGardenAreaCost()                      //garden area cost, accessor method
    {
        return this.gardenAreaCost;
    }

    public void setBookingID(String bookingID)          //allows change of booking ID, mutator
    {
        this.bookingID=bookingID;
    }

    public String getBookingID()                        //retrieves booking ID, accessor
    {
        return this.bookingID;
    }

    public void setBookingDate(String bookingDate)      //allows booking date change, mutator
    {
        this.bookingDate=bookingDate;
    }

    public String getBookingDate()                      //retrieves booking date, accessor
    {
        return this.bookingDate;
    }

    public void setPropertyOwnerName(String propertyOwnerName)      //allows property owner name change, mutator
    {
        this.propertyOwnerName=propertyOwnerName;
    }

    public String getPropertyOwnerName()                            //retrieves property owner name, accessor
    {
        return this.propertyOwnerName;
    }

    public void setContactNumber(String contactNumber)              //allows contact number change, mutator
    {
        this.contactNumber=contactNumber;
    }

    public String getContactNumber()                                //retrieves contact number, accessor
    {
        return this.contactNumber;
    }

    public void setAddress(String address)                          //allows address change, mutator
    {
        this.address=address;
    }

    public String getAddress()                                      //retrieves address, accessor
    {
        return this.address;
    }

    public void setRooms(int rooms)                                 //allows number of rooms change, mutator
    {
        this.rooms=rooms;
    }

    public int getRooms()                                           //retrieves number of rooms, accessor
    {
        return this.rooms;
    }

    public void setGardenArea(int gardenArea)                       //allows garden area change, mutator
    {
        this.gardenArea=gardenArea;
    }

    public int getGardenArea()                                      //retrieves garden area, accessor
    {
        return this.gardenArea;
    }

    public void setNumWeeks(int numWeeks)                       //allows garden area change, mutator
    {
        this.numWeeks=numWeeks;
    }

    public int getNumWeeks()                                      //retrieves garden area, accessor
    {
        return this.numWeeks;
    }

    @Override                                                       //converts data to string for output
    public String toString()
    {
        return getClass().getName()+"\n"+bookingID+" "+bookingDate+" "+numWeeks+" "+propertyOwnerName+" "+contactNumber+" "+address+" "+rooms+" "+gardenArea+" "+roomsCost+" "+gardenAreaCost;
    }
}

class Luxury extends Booking            //subclass of booking
{
    private boolean securityAlarmCheck;
    private boolean poolMaintenance;
    private int luxuryCost=0;

    public Luxury(String bookingID, String bookingDate,int numWeeks, String propertyOwnerName, String contactNumber, String address, int rooms, int gardenArea, boolean securityAlarmCheck, boolean poolMaintenance)
    {       //constructor also takes true/false for program checkboxes
        super(bookingID,bookingDate,numWeeks,propertyOwnerName,contactNumber,address,rooms,gardenArea);
        this.securityAlarmCheck=securityAlarmCheck;
        this.poolMaintenance=poolMaintenance;
    }

    public void setSecurityAlarmCheck(boolean securityAlarmCheck)       //if security alarm checkbox is checked, adds $50 to luxuryCost variable
    {
        if(this.securityAlarmCheck=true)
        {
            luxuryCost=luxuryCost+50;
        }
    }

    public boolean getSecurityAlarmCheck()                              //retrieves securityAlarmCheck (true/false)
    {
        return this.securityAlarmCheck;
    }

    public void setPoolMaintenance(boolean poolMaintenance)             //if pool maintenance checkbox is checked, adds $50 to luxuryCost variable
    {
        if(this.poolMaintenance=true)
        {
            luxuryCost=luxuryCost+50;
        }
    }

    public boolean getPoolMaintenance()                                 //retrieves poolMaintenance (true/false)
    {
        return this.poolMaintenance;
    }

    @Override                   //converts data to string for output
    public String toString()
    {
        return getClass().getName()+"\n"+securityAlarmCheck+" "+poolMaintenance;
    }
}