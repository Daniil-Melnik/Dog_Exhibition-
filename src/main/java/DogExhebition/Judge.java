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

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "breedID", referencedColumnName = "dg_id")
	private Breed breed = null;

	public Judge(){

	}
	
	public void setJudge(Breed breed, int id, String name) {
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
