package GrUsInt;

public class Dog {
	private String name;
	private Breed breed;
	private Award award;
	private int id;

	public void setDog(String name, Breed breed, Award award, int id) {
		this.setAward(award);
		this.setName(name);
		this.setBreed(breed);
		this.setId(id);
	}

	public Award getAward() {
		return award;
	}
	public String getName() {
		return name;
	}

	public Breed getBreed() {
		return breed;
	}
	public int getId() {
		return id;
	}
	
	
	public void setAward(Award award) {
		this.award = award;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBreed(Breed breed) {
		this.breed = breed;
	}
	public void setId(int id) {
		this.id = id;
	}
}
