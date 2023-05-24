import java.io.*;
import java.net.*;
public class ServerBook {
	public static void main (String args[]) {
		try{
			int port = 8912;
			int i = 0;
			ServerSocket socket = new ServerSocket(port);
			while(true) {
				Socket clientSocket = socket.accept();
				bookProcessing b = new bookProcessing(clientSocket);
				
				i++;
				System.out.println("ServerBook recieved book object number: " + i);
			}
		} catch(IOException e) {
			System.out.println("Listen socket: " + e.getMessage());
		}
	}
}

class bookProcessing extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	String data;
	int incPort;

	public bookProcessing (Socket ClientSocket) {
		try {
			clientSocket = ClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}
	
	public void run() {
		try {
			//Retrieve data sent from OrderClient
			BookOrder b = (BookOrder) in.readObject();
			
			b.executeTask();
			System.out.println("Computed total bill for book order, sending back to client. . .");

			//Sends ServerBook response to ServerCoordinator
			out.writeObject(b);
		} catch(EOFException e) {
			try {
				in.close();
				out.close();
				clientSocket.close();
				System.out.println("Client has disconnected!");
			} 	catch (IOException e1) {}
				catch (NullPointerException e2) {}
		} catch(IOException e) {
			System.out.println("readline:" + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
}
