package DogExhebition;
import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "breedID", referencedColumnName = "dg_id")
	private Breed breed = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "awardID", referencedColumnName = "id")
	private Award award = null;

	public void setDog(String name, Breed breed, Award award, int id) {
		this.setAward(award);
		this.setName(name);
		this.setBreed(breed);
		this.setId(id);
	}

	public Dog(){

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
