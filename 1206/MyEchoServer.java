import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.io.*;


public class MyEchoServer {
	public static void main(String[] args) {
		int portNumber = 8888;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
		}
		catch(IOException e) {
			System.out.println(e);
		}

		while(true) {
			try {
				System.out.println("Waiting for client connection......");
				Socket socket = serverSocket.accept();
				System.out.println("A new client is connected : " + socket);
				
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();

				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String message;
				
				while ((message = reader.readLine()) != null) {
					
					System.out.println("Client message: " + message);
					out.write((message + "\n").getBytes());
					out.flush();
				}
			} catch(IOException e) {
				System.out.println(e);
			}
		}
		
	}
}