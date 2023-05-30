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
			try (Scanner sc = new Scanner(System.in)) {
				String lH = "localhost";
				String str;
				int lP = 1189;
				int i = 1;
				int continueF;

				try (Socket s = new Socket(lH, lP)) {
					DataInputStream in = new DataInputStream(s.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

					while (true) { // Loop will allow user to enter multiple Members
						Member m = new Member();
						continueF = 1;

						// Prompts user and retrieves Member details
						System.out.println("Enter member number: " + i);
						i++;
						System.out.print("Enter First Name: ");
						String firstName = sc.nextLine();
						System.out.print("Enter Last Name: ");
						String lastName = sc.nextLine();
						System.out.print("Enter Address: ");
						String address = sc.nextLine();
						System.out.print("Enter Phone Number: ");
						String phoneNumber = sc.nextLine();

						// Checks for errors in Member send data to server.
						continueF = errorCheck(continueF, firstName, lastName, address, phoneNumber);

						// Sends data to server
						if (continueF == 1) {
							m.setFirstName(firstName);
							m.setLastName(lastName);
							m.setAddress(address);
							m.setNumber(phoneNumber);
							System.out.println("Sending data to server, please wait. . .");
							str = m.getFirstName() + ":" + m.getLastName() + ":" + m.getAddress() + ":" + m.getNumber();
							System.out.println(str);
							out.writeObject(m);

							// Retrieves server response
							String oS = in.readUTF();
							System.out.println(oS);
						}
						if (continueF == 0) {
							i--;
						}
					}
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("Socket:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		}
	}

	public static int errorCheck(int continueF, String sFN, String sLN, String sA, String sPN) {
		if (sFN.equalsIgnoreCase("")) {
			System.out.println("Error! Invalid First Name!");
			continueF = 0;
		}
		if (sLN == "") {
			System.out.println("Error! Invalid Last Name!");
			continueF = 0;
		}
		if (sA == "") {
			System.out.println("Error! Invalid Address!");
			continueF = 0;
		}
		if (sPN == "") {
			System.out.println("Error! Invalid Phone Number!");
			continueF = 0;
		}
		return continueF;
	}
}
