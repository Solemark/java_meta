import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
	public static void main(String args[]) {
		DatagramSocket aSocket = null;
		try {
			//Client to Server
			aSocket = new DatagramSocket();
			String str = "memberlistObject";
			byte[] out = str.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 2289;
			DatagramPacket request = new DatagramPacket(out, out.length, aHost, serverPort);
			aSocket.send(request);
			
			//Server to Client
			byte[] buffer = new byte[1000];
			
			//Waiting for reply
			DatagramPacket reply;
			
			header();
			while(true) {	
				reply = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(reply);

				//Convert reply to string
				String response = new String(reply.getData(), 0, reply.getLength());
				
				//Break string back into 4 parts
				String[] temp;
				String delimiter = "~SPLIT~";
				temp = response.split(delimiter);
				String fName = "|" + temp[0];
				String lName = "|" + temp[1];
				String Address = "|" + temp[2];
				String pNumber = "|" + temp[3];
				
				//Display String
				System.out.println(String.format("%-15s%-15s%-20s%-20s", fName, lName, Address, pNumber));	
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null) {
				aSocket.close();
			}
		}
	}
	
	//A method which sets out the first two lines of the client display
	public static void header() {
		System.out.println(String.format("%-15s%-15s%-20s%-20s", "|First Name", "|Last Name", "|Address", "|Phone Number"));
		for(int i = 0; i < 65; i++) {	//A small loop instead of writing out 65 = signs
			System.out.print("=");
		}
		System.out.println();	//A linebreak after the .print() functions
	}
}
