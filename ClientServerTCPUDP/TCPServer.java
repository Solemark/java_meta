import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TCPServer {
	public static void main(String args[]) {
		try {
			int serverPort = 1189;
			try (ServerSocket listenSocket = new ServerSocket(serverPort)) {
				while (true) {
					Socket clientSocket = listenSocket.accept();
					new Connection(clientSocket);
				}
			}
		} catch (IOException e) {
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
	String data = "";
	int interval = 2000;
	ArrayList<Member> mA = new ArrayList<Member>();

	/**
	 * Connection class constructor
	 * 
	 * @param aClientSocket
	 */
	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	/**
	 * runs the server
	 * 
	 * @return void
	 */
	public void run() {
		// Creates memberlist.txt
		File file = new File("memberlist.txt");
		while (true) {
			try {
				Member m = (Member) in.readObject();
				tm = new Timer();

				// TimerTask() writes to memberlistObject
				tm.schedule(new TimerTask() {
					@Override
					public void run() {
						try {
							fout = new FileOutputStream("memberlistObject");
							oos = new ObjectOutputStream(fout);

							for (int i = 0; i < mA.size(); i++) {
								Member ma = new Member();
								ma = mA.get(i);
								oos.writeObject(ma);
							}
							oos.writeObject(null);
							oos.close();
							fout.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						// Closes the timer when completed
						tm.cancel();
					}
				}, interval, interval);

				// FileWriter writes memberlist.txt
				try {
					FileWriter fr = new FileWriter(file, true);
					mA.add(m);
					data = m.getFirstName() + ":" + m.getLastName() + ":" + m.getAddress() + ":" + m.getNumber();
					System.out.println(data);
					fr.write(data + System.lineSeparator());
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// Sends server response to client
				out.writeUTF("Saved data of server!");
			} catch (EOFException e) {
				System.out.println("End of File:" + e.getMessage());
			} catch (IOException e) {
				System.out.println("readline:" + e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
