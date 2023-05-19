package GrUsInt;

public class Breed {
	private int id;
	private String title;

	public void setBreed(String title, int id) {
		this.setId(id);
		this.setTitle(title);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
}
