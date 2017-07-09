package my;

public abstract class Animal {
	private String name;
	private Integer ID;
	public Animal(String n){
		name = n;
		ID = 0;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name+" : "+ID;
	}
	
	public void setID(Integer id){
		ID = id;
	}
	public Integer getID(){
		return ID;
	}
	
	
}
