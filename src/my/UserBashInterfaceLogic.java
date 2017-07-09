package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserBashInterfaceLogic {
	private static Shelter myShelter;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public UserBashInterfaceLogic(){
		myShelter = new Shelter(10);
		
	}
	public static void main(String[] args) {
		String message = null;
		UserBashInterfaceLogic userBashInterfaceLogic = new UserBashInterfaceLogic();
		parseData();
		
		System.out.println("Hello!");
		System.out.println("Options: ");
		System.out.println("Add cat/dog name");
		System.out.println("Remove ID");
		System.out.println("Quit and save press 'q'");
		do{
			try {
				message = br.readLine();
				handleMessage(message);
			} catch (IOException e) {
				System.out.println("Reading error");
			}
			
		}while(!message.equals(new String("q")));
		
		save();
	}
	
	public static void handleMessage(String m){
		String[] messageTab;
		messageTab = m.split(" ", 3);
	
		if(messageTab.length == 1){
			if(messageTab[0].equals(new String("Status"))){
				System.out.println(myShelter.getCapacity()+"/"+myShelter.getTotalCapacity());
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
	
	public static void save(){
		FileManaging fileManaging = new FileManaging();
		
		for(Animal a : myShelter.getShelterList()){
			fileManaging.addToList(a.toString());
		}
		
		fileManaging.writeToFile();
	}
	
	public static void parseData(){
		List<String> data;
		String[] stringArray = new String[3];
		FileManaging fileManaging = new FileManaging();
		data = fileManaging.readFile();
		
		//data.stream().forEach(a -> System.out.println(a + " . "));
		
		for(String a : data){
			stringArray = a.split(" : ",3);
			if(stringArray[0].equals(new String("dog")))
				myShelter.addAnimal(new Dog(stringArray[1]),Integer.valueOf(stringArray[2]));
			else if(stringArray[0].equals(new String("cat")))
				myShelter.addAnimal(new Cat(stringArray[1]),Integer.valueOf(stringArray[2]));
		}
	}
}
