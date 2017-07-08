package my;

public abstract class Animal {
	private String name;
	
	public Animal(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
