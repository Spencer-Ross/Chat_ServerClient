import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author Spencer
 *
 *This class creates a new Thread that, unlike its ServerThread
 *counterpart, writes the user's output to the other's stdout
 *
 */

public class ClientThread extends Thread /*implements Runnable*/ {
	private Socket socket;
	private Scanner stdin;
	private PrintWriter stdout;
	
	//Constructor
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	//New Thread run() Method
	public void run() {
		try {
			//establish input from stdin & output to Stream
			stdin  = new Scanner(System.in);
			stdout = new PrintWriter(socket.getOutputStream(), true);
			
			//this loops as long as the socket is live
			while(!socket.isClosed()) {
				if(stdin.hasNextLine()) {
					String output = stdin.nextLine();
					stdout.println("OTHER: " + output); //print output to piped console
					stdout.flush();			//..immediately
					if(output.equals("END!~")) { //this is the keyword to exit
						stdout.println("Connection Closed by OTHER: Goodbye!");
						socket.close();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
