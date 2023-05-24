package assignment.pkg2;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class Main {
    public static void main (String args[]){
        Scanner in = new Scanner(System.in);
        Scanner ip = new Scanner(System.in);
        Query query;
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("CarDB");
        EntityManager em = ef.createEntityManager();
        Car car;
        String search;

        while(true){
            int i = 0;
            System.out.println("Enter 1 to retrieve car data."
                                + "\nEnter 2 to retrieve customer data."
                                + "\nEnter 3 to retrieve order data."
                                + "\nEnter 4 to load test data."
                                + "\nEnter any other character to close system."
            );
            try{    //collects user input.
                i = in.nextInt();
            }catch(InputMismatchException e){   //catches letter input instead of number
                closeApp();
            }  
            switch(i){
                case 1: //If input is 1. . .
                    System.out.print("Enter the Car's reference number: ");
                    search = ip.nextLine();
                    car = em.find(Car.class, search.toUpperCase()); //takes car class to determine if new or used car.
                    query = em.createNamedQuery("Car.find").setParameter("rn", search.toUpperCase());   //query's db.
                    List<Car> carL = query.getResultList(); //saves query results.
                    for(Car c:carL){
                        lineBreak();
                        if(car instanceof CarNew){  //determines car class
                            System.out.println("*****NEW CAR*****");
                        }
                        else{
                            System.out.println("*****USED CAR*****");
                        }
                        System.out.println("Reference Number: " + c.getReferenceNumber()    //output car details
                                            + "\nMake: " + c.getMake()
                                            + "\nModel: " + c.getModel()
                                            + "\nDrive Type: " + c.getDriveType()
                                            + "\nColour: " + c.getColour()
                                            + "\nTransmission:" + c.getTransmission()
                                            + "\nEngine Type: " + c.getEngineType()
                                            + "\nFuel Type:" + c.getFuelType()
                                            + "\nDoors: " + c.getDoors()
                                            + "\nSeats: " + c.getSeats()
                                            + "\nPrice: " + c.getPrice()
                                            + c
                        );
                    }
                    lineBreak();
                break;
                case 2:
                    System.out.print("Enter a Customer's Name: ");
                    search = ip.nextLine();
                    query = em.createNamedQuery("Customer.find").setParameter("cn", search); //retrieve searched details
                    List<Customer> cusL = query.getResultList();    //save searched details
                    for(Customer c:cusL){   //output searched details
                        System.out.println(c);
                        lineBreak();
                    }
                break;
                case 3:
                    System.out.print("Enter a Customer's Name: ");
                    search = ip.nextLine();
                    query = em.createNamedQuery("Orders.find").setParameter("cn", search);  //retrieve searched details
                    List<Orders> ordL = query.getResultList();  //save searched details
                    int c = 0;
                    for(Orders o:ordL){ //display customer, car and order details
                        Customer customer = em.find(Customer.class, ordL.get(c).getCustomerID());
                        car = em.find(Car.class, ordL.get(c).getReferenceNumber());
                        lineBreak();
                        System.out.println("Customer ID: " + o.getCustomerID()
                                            + "\nCustomer Name: " + customer.getCustomerName()
                                            + " <==> "
                                            + "Car Make: " + car.getMake() + ", Car Model:" + car.getModel());
                        c++;
                    }
                    lineBreak();
                break;
                case 4:
                    lineBreak();
                    try{    //attempt to load test data
                        testData.populate();
                        System.out.println("Test data loaded successfully.");
                    }
                    catch(Exception e){ //catches error from search data already being loaded
                        System.out.println("Error, Data is already loaded into database.\nClear the database and try again.");
                    }
                    lineBreak();
                break;
                default: closeApp();
            }
        }
    }
    public static void lineBreak(){
        System.out.println("-------------------------");
    }
    public static void closeApp(){
        System.out.println("Did not enter a recognised character. Closing the application.");
        System.exit(0);
    }
}