import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 1189; 
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {
			System.out.println("Listen socket: " + e.getMessage());
		}
	}
}

class Connection extends Thread {
	ObjectInputStream in;
	DataOutputStream out;
	FileOutputStream fout;
	ObjectOutputStream oos;
	Socket clientSocket;
	Timer tm;
	String data;
	int interval = 2000;
	ArrayList<Member> mA = new ArrayList<Member>();
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}
	
	public void run(){
		File file = new File("memberlist.txt");	//Creates memberlist.txt
		while(true) {
			try {
				Member m = (Member)in.readObject();
				tm = new Timer();
				
				tm.schedule(new TimerTask() {	//TimerTask() writes to memberlistObject
					@Override
					public void run() {
						try {
							fout = new FileOutputStream("memberlistObject");
							oos = new ObjectOutputStream(fout);
							
							for(int i=0; i < mA.size(); i++) {
								Member ma = new Member();
								ma = mA.get(i);
								oos.writeObject(ma);
							}
							oos.writeObject(null);
							oos.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
						tm.cancel();	//Closes the timer when completed
					}
				}, interval, interval);
				
				//FileWriter writes memberlist.txt
				try {
					FileWriter fr = new FileWriter(file, true);
					mA.add(m);
					data = m.getFName() + ":" + m.getLName() + ":" + m.getAddress() + ":" + m.getPNumber();
					System.out.println(data);
					fr.write(data + System.lineSeparator());
					fr.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				out.writeUTF("Saved data of server!");	//Sends server response to client
			} catch(EOFException e) {
				System.out.println("EOF:" + e.getMessage());
			} catch(IOException e) {
				System.out.println("readline:" + e.getMessage());
			} catch(ClassNotFoundException e){
					 e.printStackTrace();
			} 
		}
	}
}
