import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 */

/**
 * @author Spencer
 *
 */
public class ServerMain {
	public static void main(String[] args) throws Exception {
		int portNumber = 1201;
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + portNumber);	//instructables.com
			System.exit(1);					//instructables.com
		}
		
		while(true) {
			System.out.println("Waiting for connection\nenter \"quit\" to exit");
			Scanner stdin = new Scanner(System.in);
			
			try {
				Socket socket		= serverSocket.accept();	//accepts new server socket
				System.out.println("Got connection from " + socket);
				ServerThread server = new ServerThread(socket);
				ClientThread client = new ClientThread(socket);
				//Thread thread		= new Thread(client);
				client.start();
				server.start();
				//clients.add(client);
			} catch (IOException e) {
				System.out.println("Accept failed on: " + portNumber);
			}
			
			if(stdin.hasNextLine()) {
				if(stdin.nextLine().equals("quit")) {
					System.out.println("Server Closed");
					serverSocket.close();
					System.exit(0);
					return;
				} 
			}
		}	
	}
	
	//accepts new clients to connect to the port pipe
	/*public static void acceptClients(ServerSocket serverSocket, String portNumber) {
		ArrayList clients = new ArrayList<ServerThread>();
		
		while(true) {
			try {
				Socket socket		= serverSocket.accept();	//accepts new server socket
				ServerThread client = new ServerThread(socket);
				Thread thread		= new Thread(client);
				thread.start();
				clients.add(client);
			} catch (IOException e) {
				System.out.println("Accept failed on: " + portNumber);
			}
		}
	}*/
}
