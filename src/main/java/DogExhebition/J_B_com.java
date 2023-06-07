package DogExhebition;
import jakarta.persistence.*;

@Entity
@Table(name = "j_b_com")
public class J_B_com {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "judge_id", referencedColumnName = "id")
    private Judge judge;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "breed_id", referencedColumnName = "dg_id")
    private Breed breed;

    @Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    public J_B_com(){

    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setJudge(Judge judge){
        this.judge = judge;
    }
    public Judge getJudge(){
        return this.judge;
    }

    public void setBreed(Breed breed){
        this.breed = breed;
    }
    public Breed getBreed(){
        return this.breed;
    }
}
