package DogExhebition;

import jakarta.persistence.*;

@Entity
@Table(name = "breeds")
public class Breed {

	@Id
    @Column(name = "dg_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int did;

	@Column(name = "title")
	private String title;

	public Breed(){

	}

	public Breed(String title, int id){
		this.setId(id);
		this.setTitle(title);
	}

	public void setBreed(String title, int id) {
		this.setId(id);
		this.setTitle(title);
	}

	
	public void setId(int id) {
		this.did = id;
	}
	public int getId() {
		return did;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	
	
	
}
