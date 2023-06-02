package DogExhebition;
import jakarta.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;

	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    // @JoinColumn(name = "dogID", referencedColumnName = "id")
	// private Dog dog = null;
	
	public Owner(){

	}

	public void setOwner(Dog dog, String name, int id) {
		this.setId(id);
		this.setName(name);
		//this.setDog(dog);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	// public void setDog(Dog dog) {
	// 	this.dog = dog;
	// }

	// public Dog getDog() {
	// 	return dog;
	// }
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
