import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @author Spencer
 *
 */
public class ClientMain {
	public static void main(String[] args) /*throws Exception*/ {
		Socket socket = null;
		/*System.out.println("Please input username");
		Scanner stdin = new Scanner(System.in);
		String username = stdin.nextLine();
		stdin.close();*/
		
		int portNumber	= 1201;		//port to pipe to
		//Socket socket	= new Socket("localhost", portNumber); //connect socket to port number and say that it's a local connection
	   
		/*Scanner scanner = new Scanner(socket.getInputStream());
	    PrintWriter printW = new PrintWriter(socket.getOutputStream());
	    Scanner stdin = new Scanner(System.in);
	    while (stdin.hasNextLine()){
	      String buffer=stdin.nextLine();
	      printW.println(buffer);printW.flush();
	      buffer=scanner.nextLine();
	      System.out.println("> " + buffer);
	    }
	    scanner.close();
	    socket.close();*/
		
		try {
			socket = new Socket ("localhost", portNumber);
			Thread.sleep(1000);
			ServerThread out  = new ServerThread(socket);
			ClientThread in = new ClientThread(socket);
			out.start();
			in.start();
		} catch (IOException e) {
			System.err.println("Fatal Connection error!");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println("Fatal Connection error!");
			e.printStackTrace();
		}
	}
}