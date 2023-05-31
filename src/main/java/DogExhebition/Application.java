package DogExhebition;

import java.util.List;
// import org.hibernate.HibernateException;

public class Application {
    // public static void main(String args[]){
    //     AwardDao awardDao = new AwardDao();
    //     List<Award> BreedList1 = awardDao.getAwards();
	// 	for (int i =0; i< BreedList1.size(); i++){
	// 		Award tB = BreedList1.get(i);
	// 		System.out.println(tB.getId()+" "+tB.getTitle());
	// 	}
    // }
    public static void main(String[] args) {
        List<Award> tA = null;
        tA=AwardDao.getAwards();
        for (int i =0; i<tA.size(); i++){
            Award jB = tA.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
        }

        List<Dog> tD = null;
        tD=DogDao.getDog();
        for (int i =0; i<tD.size(); i++){
            Dog jB = tD.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getBreed().getTitle());
        }

        List<Owner> tO = null;
        tO=OwnerDao.getOwners();
        for (int i =0; i<tO.size(); i++){
            Owner jB = tO.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getDog().getName()+" "+jB.getDog().getBreed().getTitle());
        }

        List<Judge> tJ = null;
        tJ=JudgeDao.getJudges();
        for (int i =0; i<tJ.size(); i++){
            Judge jB = tJ.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getBreed().getTitle());
        }
    }
}
