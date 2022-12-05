import java.net.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class OrderClient {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		String [] tArray = {"Book", "Movie"};
		String host = "localhost";
		
		int bookPort = 8911;
		Socket bookSocket = null;
		ObjectOutputStream bout = null;
		ObjectInputStream bin = null;

		int moviePort = 8921;
		Socket movieSocket = null;
		ObjectOutputStream mout = null;
		ObjectInputStream min = null;
	
		BookOrder b;
		MovieOrder m;
		
		try {
			bookSocket = new Socket(host, bookPort);
			bout = new ObjectOutputStream(bookSocket.getOutputStream());
			bin = new ObjectInputStream(bookSocket.getInputStream());
			
			movieSocket = new Socket(host, moviePort);
			mout = new ObjectOutputStream(movieSocket.getOutputStream());
			min = new ObjectInputStream(movieSocket.getInputStream());		
				while(true) {
				clientPrompt();
				
				Integer cOption = null;
				System.out.print("Enter your option: ");
				try {
					cOption = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Error! You must enter a number!" + e);
					cOption = 0;
				}

				if(cOption == 1 || cOption == 2) {
					System.out.print("\nEnter the number of " + tArray[(cOption - 1)] + "s: ");
					double quantity = sc.nextDouble();
					System.out.print("Enter the " + tArray[(cOption - 1)] + " price: ");
					double price = sc.nextDouble();
					
					//SENDING
					System.out.println("SENDING OBJECT TO SERVER. . .");
					if(cOption == 1) {
						b = new BookOrder();
						b.setQuantity(quantity);
						b.setPrice(price);

						bout.writeObject(b);
						
						System.out.println("RECIEVING OBJECT FROM SERVER. . .");
						BookOrder bA = (BookOrder) bin.readObject();
						System.out.println("Number of books:" + bA.getQuantity() + " Price:" + bA.getPrice() + " Tax:" + bA.getTax() + " Bill total for books:" + bA.getResult());
					}
					if(cOption == 2) {
						m = new MovieOrder();
						m.setQuantity(quantity);
						m.setPrice(price);

						mout.writeObject(m);
						System.out.println("RECIEVING OBJECT FROM SERVER. . .");
						MovieOrder mA = (MovieOrder) min.readObject();
						System.out.println("Number of movies:" + mA.getQuantity() + " Price:" + mA.getPrice() + " Tax:" + mA.getTax() + " Bill total for movies:" + mA.getResult());
					}
				}
				else if(cOption == 3) {
					
					bookSocket.close();
					movieSocket.close();
					System.out.println("You have been disconnected from the server!");
					break;
				}
				else {
					System.out.println("Error! Invalid option!");
					break;
				}					
					
			}
			System.out.println("==========");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void clientPrompt() {
		System.out.println("\n**********");
		System.out.println("PLEASE PLACE YOUR ORDER BY SELECTING A NUMBER");
		System.out.println("**********");
		System.out.println("1. Purchase Book");
		System.out.println("2. Purchase Movie");
		System.out.println("3. Exit");
		System.out.println("**********");
	}
}
