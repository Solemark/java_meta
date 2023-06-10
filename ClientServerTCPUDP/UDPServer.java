import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class UDPServer {
	public static void main(String args[]) {
		DatagramSocket socket = null;
		try {
			final int port = 2289;
			socket = new DatagramSocket(port);
			byte[] buffer = new byte[1000];
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			ArrayList<Member> memberArrayList = new ArrayList<Member>();

			while (true) {
				memberArrayList.clear();
				// Server receives client request and starts the server process.
				socket.receive(request);
				String file = new String(request.getData(), 0, request.getLength());
				System.out.println(file);

				// Section retrieves data from memberlistObject
				Member member = null;
				Object object = new Object();
				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
					int i = 0;

					while (true) {
						if (object != null) {
							object = input.readObject();
							member = (Member) object;
							memberArrayList.add(member);
						} else {
							break;
						}
						i++;
					}
					input.close();
					i = memberArrayList.size() - 1;
					memberArrayList.remove(i);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Sends data stored in the Member ArrayList to the UDPClient
				byte[] send;
				DatagramPacket reply;
				for (int i = 0; i < memberArrayList.size(); i++) {
					send = new byte[1000];
					String out = String.format(
							"%s~SPLIT~%s~SPLIT~%s~SPLIT~%s",
							memberArrayList.get(i).getFirstName(),
							memberArrayList.get(i).getLastName(),
							memberArrayList.get(i).getAddress(),
							memberArrayList.get(i).getNumber());
					System.out.println(out);
					send = out.getBytes();
					reply = new DatagramPacket(send, send.length, request.getAddress(), request.getPort());
					socket.send(reply);
				}
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (socket != null)
				socket.close();
		}
	}
}
