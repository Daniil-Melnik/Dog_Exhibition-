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
	
	public Owner(){

	}

	public void setOwner(Dog dog, String name, int id) {
		this.setId(id);
		this.setName(name);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
