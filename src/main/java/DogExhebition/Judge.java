package DogExhebition;
import jakarta.persistence.*;

@Entity
@Table(name = "judges")
public class Judge {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;



	public Judge(){

	}
	
	public void setJudge(Breed breed, int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
}
