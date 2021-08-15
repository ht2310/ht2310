

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
public class server {
	ServerSocket server;
	Socket socket;
	BufferedReader br;
	PrintStream out;
	public server() {
		try {
		server=new ServerSocket(7777);
		System.out.println("ready for accepting request!!!!");

		System.out.println("Waiting.....");
		socket=server.accept();
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out= new PrintStream(socket.getOutputStream());
		startReading();
		startWritting();
		
		}
		catch(Exception e) {
			
			e.getStackTrace();
		}
		
}
	public void  startReading(){
		
		Runnable r1=()->{
			while(true) {
				
			try {
				String msg=br.readLine();
				if(msg.equals("exit")) {
					System.out.println("Client want to exit!!!");
					break;
				}
				else {
					System.out.println("Client:"+msg);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		};
		new Thread(r1).start();
	}
public void  startWritting(){
		
		Runnable r2=()->{
			while(true) {
				try {
					System.out.println("writting start...");
				
				BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
				String content=br1.readLine();
				out.println(content);
				out.flush();
				
			}
				catch(Exception e) {
					e.printStackTrace();
				}
				}
			
		};
		new Thread(r2).start();
	}

public static void main(String ar[]) {
	System.out.println("Server is runninggg");
	new server();
	
}
}
