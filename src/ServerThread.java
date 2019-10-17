import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * 
 * @author Spencer
 * 
 *
 * THis class starts a new thread that takes input and puts it out to
 * stdout. It currently uses a buffered reader, but if I can't get it
 * to work the way I think. I'm simply going to roll with scanners and
 * the normal stuff. I tried using the "Implements Runnable" but didn't
 * quite understand the difference between that and "extends Thread".
 * I think the implement let's you still use the class Thread for
 * method calls while taking your custom code into account, but without
 * needing to name the thread in use whatever you call your thread.
 * 
 */

public class ServerThread extends Thread/*extends ServerMain implements Runnable*/ {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	//Constructor
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	//New Thread run() Method
	public void run() {
		try {
			//establish input from Stream & output to stdout
			out = new PrintWriter(socket.getOutputStream(), true);
			in	= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//this loops as long as the socket is connected
			while(!socket.isClosed()) {
				String input = in.readLine();
				if(input != null) {
					System.out.println(input);
					out.flush();
				} 
				if(input.equals("END!~")) {
					socket.close();
					System.exit(0);
				}
			}
			/*while (in.hasNextLine()) {
				String buffer = in.nextLine();
				
				while (!buffer.equals("END!~")) {		//for quitting
					System.out.println(buffer);			//for Client -> Server
					out.flush();	
					if(stdin.hasNext()) {				//for Server -> Client
						printW.println(stdin.nextLine());
						printW.flush();
					}
				}
			}*/
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
	
	public PrintWriter getWriter() {
		return out;
	}
}
