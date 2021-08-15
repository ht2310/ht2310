
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;

public class client {
	ServerSocket server;
	Socket socket;
	BufferedReader br;
	PrintStream out;
	public client() {
		try {
			System.out.println("ready for sending request!!!!");
		socket=new Socket("127.0.0.1",7777);
		System.out.println("connection done!!!!");
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
					System.out.println("server want to exit!!!");
					break;
				}
				else {
					System.out.println("server:"+msg);
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
	
	System.out.println("cleint");
	new client();
	}
}
