import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class UDPServer {
	public static void main(String args[]){ 
    	DatagramSocket aSocket = null;
		try{
			
			int serverPort = 2289;
	    	aSocket = new DatagramSocket(serverPort);
			byte[] buffer = new byte[1000];
 			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
 			ArrayList<Member> mA = new ArrayList<Member>();
 			
 			while(true) {
 				mA.clear();
 				//Server receives client request and starts the server process.
 				aSocket.receive(request);
 				String file = new String(request.getData(), 0, request.getLength());
 				System.out.println(file);
 			
 				//Section retrieves data from memberlistObject
 				Member m = null;
 				Object obj = new Object();
 				try {
 					FileInputStream fIn = new FileInputStream(file);
 					ObjectInputStream in = new ObjectInputStream(fIn);
 					int i = 0;

 					while(true) {
 						if(obj != null) {
 							obj = in.readObject();
 	 	 					m = (Member) obj;
 	 	 					mA.add(m);
 						}
 						else {
 							break;
 						}
 						i++;
 					}
 					in.close();
	 				fIn.close();
 					i = mA.size()-1;
 					mA.remove(i);
 				} catch(Exception e) {
 					e.printStackTrace();
 				}

 				//Sends data stored in the Member ArrayList to the UDPClient
 				byte[] send;
 				DatagramPacket reply;
 				for(int i = 0; i < mA.size(); i++) {
 					send = new byte[1000];
 					String out = mA.get(i).getFName() + "~SPLIT~"	//"~SPLIT~" will allow for the string received by the client to be broken into variables.
 									+ mA.get(i).getLName() + "~SPLIT~"
 									+ mA.get(i).getAddress() + "~SPLIT~"
 									+ mA.get(i).getPNumber();
 	 				System.out.println(out);
 	 				send = out.getBytes();
 	 				reply = new DatagramPacket(send, send.length, request.getAddress(), request.getPort());
 	 				aSocket.send(reply);
 				}
 			}
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null)
				aSocket.close();
		}
    }
}
