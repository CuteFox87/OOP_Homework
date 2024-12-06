import java.net.ServerSocket;
import java.net.Socket;
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
				
				while ((in.read()) != -1) {
					
					byte[] buffer = new byte[1024];
					int readBytes = in.read(buffer);
					System.out.println("Client message: " + new String(buffer, 0, readBytes));

					out.write(buffer, 0, readBytes);
					out.flush();
				}
			} catch(IOException e) {
				System.out.println(e);
			}
		}
		
	}
}