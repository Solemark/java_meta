import java.io.*;
import java.net.*;
public class ServerMovie {
	public static void main (String args[]) {
		try{
			int port = 8922;
			int i = 0;
			ServerSocket socket = new ServerSocket(port);
			while(true) {
				Socket clientSocket = socket.accept();
				movieProcessing m = new movieProcessing(clientSocket);
				
				i++;
				System.out.println("ServerBook recieved movie object number: " + i);
			}
		} catch(IOException e) {
			System.out.println("Listen socket: " + e.getMessage());
		}
	}
}

class movieProcessing extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	String data;
	int incPort;

	public movieProcessing (Socket ClientSocket) {
		try {
			clientSocket = ClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {
			System.out.println("Connection: " + e.getMessage());
		}
	}
	
	public void run() {
		try {
			//Retrieve data sent from OrderClient
			MovieOrder m = (MovieOrder) in.readObject();
			System.out.println("Data Recieved: " + m);
				
			m.executeTask();
			System.out.println("Computed total bill for movie order, sending back to client. . .");

			//Sends ServerMovie response to ServerCoordinator
			out.writeObject(m);
		} catch(EOFException e) {
			try {
				movieProcessing.currentThread().interrupt();
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
