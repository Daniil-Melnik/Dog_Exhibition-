package GrUsInt;

public class Judge {
	private int id;
	private String name;
	private Breed breed;
	
	public void setJudge() {
		this.setBreed(breed);
		this.setId(id);
		this.setName(name);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBreed(Breed breed) {
		this.breed = breed;
	}
	
	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public Breed getBreed() {
		return breed;
	}
}
