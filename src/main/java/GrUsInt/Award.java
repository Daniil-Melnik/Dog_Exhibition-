package GrUsInt;

public class Award {
	private String title;
	private int id;

	public void setAward(String title, int id) {
		this.setId(id);
		this.setTitle(title);
	}
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
