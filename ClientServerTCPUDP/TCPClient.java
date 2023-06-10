import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    public static void main(String args[]) {
        try {
            try (Scanner input = new Scanner(System.in)) {
                final String LOCALHOST = "localhost";
                final int PORT = 1189;
                int i = 0;
                String output = "";

                try (Socket s = new Socket(LOCALHOST, PORT)) {
                    DataInputStream in = new DataInputStream(s.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

                    // Loop will allow user to enter multiple Members
                    while (true) {
                        i++;
                        Member m = new Member();

                        // Prompts user and retrieves Member details
                        System.out.println("Enter member number: " + i);
                        System.out.print("Enter First Name: ");
                        String firstName = input.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = input.nextLine();
                        System.out.print("Enter Address: ");
                        String address = input.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = input.nextLine();

                        // Checks for errors in Member send data to server.
                        if (errorCheck(firstName, lastName, address, phoneNumber)) {
                            m.setFirstName(firstName);
                            m.setLastName(lastName);
                            m.setAddress(address);
                            m.setNumber(phoneNumber);
                            System.out.println("Sending data to server, please wait. . .");
                            output = String.format(
                                    "%s:%s:%s:%s",
                                    m.getFirstName(),
                                    m.getLastName(),
                                    m.getAddress(),
                                    m.getNumber());
                            System.out.println(output);
                            out.writeObject(m);

                            // Retrieves server response
                            String oS = in.readUTF();
                            System.out.println(oS);
                        } else {
                            i--;
                        }
                    }
                } catch (UnknownHostException error) {
                    System.out.println("Socket:" + error.getMessage());
                }
            }
        } catch (EOFException e) {
            System.out.println("End Of File:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        }
    }

    public static boolean errorCheck(String firstName, String lastName, String address, String number) {
        boolean flag = true;
        if (firstName.equalsIgnoreCase("")) {
            System.out.println("Error! Invalid First Name!");
            flag = false;
        }
        if (lastName == "") {
            System.out.println("Error! Invalid Last Name!");
            flag = false;
        }
        if (address == "") {
            System.out.println("Error! Invalid Address!");
            flag = false;
        }
        if (number == "") {
            System.out.println("Error! Invalid Phone Number!");
            flag = false;
        }
        return flag;
    }
}
