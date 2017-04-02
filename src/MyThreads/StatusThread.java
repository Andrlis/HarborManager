package MyThreads;

import java.io.FileWriter;
import java.io.IOException;

import DataPackage.Harbor;

public class StatusThread  extends Thread{
	
	private Harbor harbor;
	
	public StatusThread(Harbor harbor){
		super();
		this.harbor = harbor;
	}
	
	@Override
	public void run(){
		while(true){
			try{
 				sleep(5000);
			}
			catch(InterruptedException e){}
			try(FileWriter writer = new FileWriter("C:\\Andrey\\Eclipse Neon projects\\HarborManager\\HarborStatus.txt",true)){
				writer.write(harbor.toString());
				writer.append("\r\n");
				
				writer.flush();
			}catch(IOException ex){
				System.out.println(ex.getMessage());
			}
		}
	}

}
