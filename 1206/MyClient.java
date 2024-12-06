import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.*;

public class MyClient {
    private static final String ADDRESS = "localhost";//ip
    private static final int PORT = 8888;//port
 
	public static void main(String args[]) {
		try {
			Socket client = new Socket(ADDRESS, PORT);
			InputStream in = client.getInputStream();
			OutputStream out = client.getOutputStream();
			
			Console console = System.console();
			String userInput;
			System.out.print("input: ");

			while ((userInput = console.readLine()) != null) {
				out.write(userInput.getBytes());
				out.flush();
				
				byte[] buffer = new byte[1024];
				int readBytes = in.read(buffer);
				System.out.println("Server response: " + new String(buffer, 0, readBytes));
				System.out.print("input: ");
			}
			
		} catch(IOException e) {
			System.out.println(e);
		}
	}
}