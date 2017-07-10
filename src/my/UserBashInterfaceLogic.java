package my;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserBashInterfaceLogic {
	private static Shelter myShelter;
	//private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public UserBashInterfaceLogic(){
		myShelter = new Shelter(10);
		parseDBData();
		//for reading from file
		//parseData();
	}
	/*public static void main(String[] args) {
		String message = null;
		
		
		
		System.out.println("Hello!");
		System.out.println("Options: ");
		System.out.println("Status");
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
		
		do{
			;
		}while(true);
		
	}*/
	
	//terminal input
	/*public static void handleMessage(String m){
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
	}*/
	
	public static String getStatus(){
		return myShelter.getCapacity()+"/"+myShelter.getTotalCapacity()+"\n"+myShelter.toString();
	}
	
	public static void remove(String ID){
		myShelter.removeAnimal(Integer.valueOf(ID));
	}
	
	public static void save(){
		FileManaging fileManaging = new FileManaging();
		
		for(Animal a : myShelter.getShelterList()){
			fileManaging.addToList(a.toString());
		}
		
		fileManaging.writeToFile();
	}
	
	public static void saveToDB(Animal a){
		DatabaseManaging databaseManaging = new DatabaseManaging();
		String[] stringAnimal = new String[3];
		stringAnimal[0] = Integer.toString(a.getID());
		stringAnimal[1] = "'"+a.getName().split(" : ", 2)[1]+"'";
		if(a instanceof Dog){
			stringAnimal[2] = Integer.toString(1);
		}else if(a instanceof Cat){
			stringAnimal[2] = Integer.toString(2);
		}
		
		databaseManaging.insertInto("Animals", stringAnimal);
	}
	
	public static void add(String choice, String name){
		if(choice.equals(new String("Dog"))){
			myShelter.addAnimal(new Dog(name));
		}else if(choice.equals(new String("Cat"))){
			myShelter.addAnimal(new Cat(name));
		}
	}
	
	public static void writeToCSV(){
		String csvFile = "C:\\Users\\lukas\\Java workspaces\\Simple Shelter App\\Shelter.csv";
		FileWriter writer;
		try {
			writer = new FileWriter(csvFile);
			CSVManaging.writeLine(writer, myShelter.getShelterList());
			writer.close();
		} catch (IOException e) {
			new ErrorDialog(e.getMessage());
		}
		
	}
	
	public static void exportToPDF(){
		String csvFile = "C:\\Users\\lukas\\Java workspaces\\Simple Shelter App\\Shelter.csv";
		PDFManaging.CSVToPDF(csvFile);
		
	}
	public static void parseDBData(){
		(new DatabaseManaging()).parseDatabaseData(myShelter);
	}
	//reading from file
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
