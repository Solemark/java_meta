import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
	public static void main (String args[]) {
		try{
			Scanner sc = new Scanner(System.in);
			String lH = "localhost";
			String str;
			int lP = 1189;
			int i = 1;
			int continueF;
			
			Socket s = new Socket(lH, lP);
			DataInputStream in = new DataInputStream(s.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			
			while(true) {	//Loop will allow user to enter multiple Members
				Member m = new Member();
				continueF = 1;
				
				//Prompts user and retrieves Member details
				System.out.println("Enter member number: " + i);
				i++;
				System.out.print("Enter First Name: ");
				String sFN = sc.nextLine();
				System.out.print("Enter Last Name: ");
				String sLN = sc.nextLine();
				System.out.print("Enter Address: ");
				String sA = sc.nextLine();		
				System.out.print("Enter Phone Number: ");
				String sPN = sc.nextLine();
				
				//Checks for errors in Member details and controls whether data will be sent to server.
				continueF = errorCheck(continueF, sFN, sLN, sA, sPN);
				
				//Sends data to server
				if(continueF == 1) {
					m.setFName(sFN);
					m.setLName(sLN);
					m.setAddress(sA);
					m.setPNumber(sPN);
					System.out.println("Sending data to server, please wait. . .");
					str = m.getFName()	+ ":" + m.getLName() + ":" + m.getAddress() + ":" + m.getPNumber();
					System.out.println(str);
					out.writeObject(m);

					//Retrieves server response
					String oS = in.readUTF();
					System.out.println(oS);
				}
				if(continueF == 0) {
					i--;
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("Socket:"+e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:"+e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:"+e.getMessage());
		} 
    }
	
	public static int errorCheck(int continueF, String sFN, String sLN, String sA, String sPN) {
		if(sFN.equalsIgnoreCase("")) {
			System.out.println("Error! Invalid First Name!");
			continueF = 0;
		}
		if(sLN == "") {
			System.out.println("Error! Invalid Last Name!");
			continueF = 0;
		}
		if(sA == "") {
			System.out.println("Error! Invalid Address!");
			continueF = 0;
		}
		if(sPN == "") {
			System.out.println("Error! Invalid Phone Number!");
			continueF = 0;
		}
		return continueF;
	}
}
