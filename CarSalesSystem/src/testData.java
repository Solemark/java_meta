package assignment.pkg2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class testData {
    public static void populate(){
        //Create Entity -- Used Car
        CarUsed carU = new CarUsed();
        carU.setReferenceNumber("456DEF");
        carU.setMake("Toyota");
        carU.setModel("Carolla");
        carU.setDriveType("2 Wheel Drive");
        carU.setColour("White");
        carU.setTransmission("Manual");
        carU.setEngineType("v4");
        carU.setFuelType("U91 & E10");
        carU.setDoors("4");
        carU.setSeats("5");
        carU.setPrice("$100");
        carU.setOdometer("00001");
        carU.setServiceHistory("last service on Jan 1st 2020");
        carU.setVehicleIdentificationNumber("123456789");
        
        //Create Entity -- New Car
        CarNew carN = new CarNew();
        carN.setReferenceNumber("123ABC");
        carN.setMake("Lambo");
        carN.setModel("Fast");
        carN.setDriveType("4 Wheel Drive");
        carN.setColour("Red");
        carN.setTransmission("Automatic");
        carN.setEngineType("v8");
        carN.setFuelType("Diesel");
        carN.setDoors("2");
        carN.setSeats("2");
        carN.setPrice("$10000");
        carN.setWarranty("3 year");
        carN.setExtendedWarranty("2 year");
        carN.setRoadsideAssistance("Yes");
        
        //Create Customer
        Customer customer = new Customer();
        customer.setCustomerName("Mason");
        customer.setCustomerEmail("mason.larcombe@cqumail.com");
        customer.setCustomerPhone("0123 456 789");
        customer.setCustomerAddress("111 Nowhere rd.");
        
        //Create Order
        Orders order = new Orders();
        order.setReferenceNumber("456DEF");
        order.setCustomerID(1);
        
        Orders orderB = new Orders();
        orderB.setReferenceNumber("123ABC");
        orderB.setCustomerID(1);
        
        //Get an entity manager and a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarDB");
        EntityManager em = emf.createEntityManager();

        //Persist the entities into the db
        EntityTransaction tx = em.getTransaction();       
        tx.begin();
        em.persist(carU);
        em.persist(carN);
        em.persist(customer);
        em.persist(order);
        em.persist(orderB);
        tx.commit();

        em.close();
        emf.close();
    }
}
