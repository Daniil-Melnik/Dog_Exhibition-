package GrUsInt;

public class Owner {
	private Dog dog;
	private String name;
	private int id;
	
	public void setOwner(Dog dog, String name, int id) {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Dog getDog() {
		return dog;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
