package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserBashInterfaceLogic {
	private static Shelter myShelter;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public UserBashInterfaceLogic(){
		myShelter = new Shelter(10);
		
	}
	public static void main(String[] args) {
		UserBashInterfaceLogic inter = new UserBashInterfaceLogic();
		String message = null;
		do{
			try {
				message = br.readLine();
				
				handleMessage(message);
			} catch (IOException e) {
				System.out.println("Reading error");
			}
			
		}while(message != "q");
	}
	
	public static void handleMessage(String m){
		String[] messageTab;
		messageTab = m.split(" ", 3);
	
		if(messageTab.length == 1){
			if(messageTab[0].equals(new String("Status"))){
				System.out.println(myShelter.getCapacity());
				System.out.println(myShelter.toString());
			}
		}else if(messageTab.length == 3){
			if(messageTab[0].equals(new String("Add"))){
				if(messageTab[1].equals(new String("dog"))){
					myShelter.addAnimal(new Dog(messageTab[2]));
				}else if(messageTab[1].equals(new String("cat"))){
					myShelter.addAnimal(new Cat(messageTab[2]));
				}
			}
		}else if(messageTab.length == 2){
			if(messageTab[0].equals(new String("Remove")))
				myShelter.removeAnimal(Integer.valueOf(messageTab[1]));
		}
	}
}
