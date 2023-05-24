import java.io.*;
import java.net.*;
public class ServerCoordinator {
	public static void main (String args[]) {
		try{
			int bookPort = 8911;
			int moviePort = 8921;
			int exitPort = 8931;
			int eFlag = 0;
			ServerSocket bookSocket = new ServerSocket(bookPort);
			ServerSocket movieSocket = new ServerSocket(moviePort);
			while(true) {
				Socket bClientSocket = bookSocket.accept();
				Connection bC = new Connection(bClientSocket, bookPort);
				
				Socket mClientSocket = movieSocket.accept();
				Connection mC = new Connection(mClientSocket, moviePort);			
			}
		} catch(IOException e) {
			System.out.println("Listen socket: " + e.getMessage());
		}
	}
}

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	String host = "localhost";
	
	Socket bSocket;
	Socket mSocket;
	
	ObjectOutputStream bout;
	ObjectInputStream bin;
	ObjectOutputStream mout;
	ObjectInputStream min;
	
	int sbPort = 8912;
	int smPort = 8922;
	int ebPort = 8932;
	int emPort = 8933;
	int incPort;
	int count = 0;

	public Connection (Socket ClientSocket, int port) {
		try {
			clientSocket = ClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			incPort = port;
			this.start();
		} catch(IOException e) {
			System.out.println("Connection: " + e.getMessage());
		}
	}
	
	public void run() {
		while(true) {
			try {
				if(incPort == 8911) {
					count++;
					//Retrieve data sent from OrderClient
					BookOrder b = (BookOrder) in.readObject();
					System.out.println("ServerCoordinator recieved client object number" + count);
					
					//Send to ServerBook
					bSocket = new Socket(host, sbPort);
					bout = new ObjectOutputStream(bSocket.getOutputStream());
					System.out.println("Sending to server for book. . .");
					bout.writeObject(b);
					
					//Receive from ServerBook
					bin = new ObjectInputStream(bSocket.getInputStream());
					BookOrder bA = (BookOrder) bin.readObject();
					
					//Forward ServerBook Response to OrderClient
					System.out.println("Return order back to original client. . .");
					out.writeObject(bA);
				}
				
				if(incPort == 8921) {
					count++;
					//Retrieve data sent from OrderClient
					MovieOrder m = (MovieOrder) in.readObject();
					System.out.println("ServerCoordinator recieved client object number" + count);
					
					//Send to ServerMovie
					mSocket = new Socket(host, smPort);
					mout = new ObjectOutputStream(mSocket.getOutputStream());
					System.out.println("Sending to server for movie. . .");
					mout.writeObject(m);
					
					//Receive from ServerMovie
					min = new ObjectInputStream(mSocket.getInputStream());
					MovieOrder mA = (MovieOrder) min.readObject();

					//Forward ServerMovie Response to OrderClient
					System.out.println("Return order back to original client. . .");
					out.writeObject(mA);	
				}
			} catch (EOFException e) {
				try {
					bSocket.close();
					mSocket.close();
					System.out.println("Client has disconnected!");
				} 	catch (IOException e1) {}
					catch (NullPointerException e2) {}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		}
	}
}