package DogExhebition;
import jakarta.persistence.*;


@Entity
@Table(name = "awards")
public class Award {
	private int id;

	private String title;
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Award(){

	}


	public Award(String title, int id) {
		this.setId(id);
		this.setTitle(title);
	}

}
