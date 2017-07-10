package my;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shelter {
	private List<Animal> shelterAnimals;
	private Integer totalCapacity;
	private Integer capacity;
	
	public Shelter(Integer c){
		shelterAnimals = new ArrayList<>();
		totalCapacity = c;
		capacity = 0;
	}
	
	public void addAnimal(Animal a){
		capacity++;
		try{
			if(capacity>totalCapacity)
				throw new IndexOutOfBoundsException();
			
			a.setID(findFreeID());
			shelterAnimals.add(a);
			UserBashInterfaceLogic.saveToDB(a);
		}catch(IndexOutOfBoundsException iobe){
			new ErrorDialog("The shelter is full \nUnable to add new animal");
			capacity--;
		}
	}
	
	public void addAnimal(Animal a, Integer ID){
		capacity++;
		try{
			if(capacity>totalCapacity)
				throw new IndexOutOfBoundsException();
			a.setID(ID);
			shelterAnimals.add(a);
			
		}catch(IndexOutOfBoundsException iobe){
			new ErrorDialog("The shelter is full \nUnable to add new animal");
			capacity--;
		}
	}
	
	public void removeAnimal(int ID){
		Animal animal = null;
		boolean flag = false; 
		capacity--;
		try{
			if(capacity<0)
				throw new IndexOutOfBoundsException();
			
		}catch(IndexOutOfBoundsException iobe){
			capacity++;
			new ErrorDialog("The shelter is empty");
		}
		for(Animal a : shelterAnimals){
			if(a.getID() == ID){
				animal = a;
				flag = true;
			}
		}
		try{
		if(flag == false)
			throw new Exception();
		}catch(Exception e){
			capacity++;
			new ErrorDialog("Ther is no animal\nwith such ID");
		}
		if(flag){
			shelterAnimals.remove(animal);
			(new DatabaseManaging()).remove("Animals", animal.getID());
		}
		
	}
	
	
	public Integer findFreeID(){
		boolean flag = true;
		Random myRand = new Random();
		int myID = 0;
		myID = 1000 + myRand.nextInt(8999);

		while(flag && !shelterAnimals.isEmpty()){
			myID = 1000 + myRand.nextInt(8999);
			flag = false;
			for(Animal a : shelterAnimals){
				if(a.getID() == myID)
					flag = true;
			}
		}
		
		return myID;
	}
	
	public Integer getCapacity(){
		return capacity;
	}
	
	public Integer getTotalCapacity(){
		return totalCapacity;
	}
	
	@Override
	public String toString(){
		String myString ="";
		
		for(Animal a : shelterAnimals)
			myString += a.toString() + ", "; 
		
		return myString;
	}
	
	public List<Animal> getShelterList(){
		return shelterAnimals;
	}
}
