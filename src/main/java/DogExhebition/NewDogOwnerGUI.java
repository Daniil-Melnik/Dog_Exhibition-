package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewDogOwnerGUI {
    private static JFrame aA;
	private static JButton apply;
	private static JTextField OwnerNameT;
	private static JTextField DogNameT;
	private static JComboBox<String> AwardsT;
	private static JComboBox<String> BreedT;
	private static JLabel OwnerNameL;
	private static JLabel DogNameL;
	private static JLabel BreedL;
	private static JLabel AwardsL;
	private static JLabel title_dog;
	private static JLabel title_owner;
	
	
	//private JTextField idT;
	//private JLabel idL;

	public static void show (final JTable table1)
	{
		//Заполнение листов с главными данными для окна
		//#############################################

		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		String regex_dog_name = "^[А-Я]{1}[а-я]*( {1}[0-9]+)?$";

		Pattern pattern_dog_name = Pattern.compile(regex_dog_name);
        Pattern pattern_person_name = Pattern.compile(regex_person_name);

		final ArrayList<Breed> BreedList = new ArrayList<>();
        final ArrayList<Award> AwardList = new ArrayList<>();
        
        final ArrayList<Dog> DogList = new ArrayList<>();
        final ArrayList<Owner> OwnerList = new ArrayList<>();
     
        
        final ArrayList<String> BrAr = new ArrayList<>();
        final ArrayList<String> AwAr = new ArrayList<>();

		List<Award> tA = null;
        tA=AwardDao.getAwards();
        for (int i =0; i<tA.size(); i++){
            Award jB = tA.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
			AwAr.add(jB.getTitle());
        }

		List<Breed> tB = null;
		tB=BreedDao.getBreeds();
        for (int i =0; i<tB.size(); i++){
            Breed jB = tB.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
			BrAr.add(jB.getTitle());
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

		for (int i=0; i<tB.size(); i++){
			BreedList.add(tB.get(i));
		}
		for (int i=0; i<tA.size(); i++){
			AwardList.add(tA.get(i));
		}
		for (int i=0; i<tD.size(); i++){
			DogList.add(tD.get(i));
		}
		for (int i=0; i<tO.size(); i++){
			OwnerList.add(tO.get(i));
		}
		//######################################################
		//Формирование окна
		//######################################################
		aA = new JFrame("");
		aA.setTitle("Добавить собаку с владельцем");
		aA.setSize(1200, 300);
		aA.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//dog15.png").getImage());
		
		title_dog = new JLabel("Добавить собаку");
		title_dog.setBounds(200, 20, 200, 30);
		title_dog.setFont(new Font("Arial", Font.PLAIN, 25));
		aA.add(title_dog);

		title_owner = new JLabel("Добавить владельца");
		title_owner.setBounds(800, 20, 300, 30);
		title_owner.setFont(new Font("Arial", Font.PLAIN, 25));
		aA.add(title_owner);
		
		apply = new JButton("Добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 20));
		apply.setBounds(500, 220, 200, 35);
		
		
		DogNameT = new JTextField();
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,70,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,70,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));	
		
		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(800,70,300,30);
		
		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(680,70,150,30);
		OwnerNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		String breeds[] = BrAr.toArray(new String[0]);
		BreedT = new JComboBox<String>(breeds);
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,110,300,30);
		
		BreedL = new JLabel("Порода собаки: ");
		BreedL.setBounds(30,110,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		AwardsT = new JComboBox<String>();
		
		String awards[] = AwAr.toArray(new String[0]);
		//String awards[] = {};
		AwardsT = new JComboBox<String>(awards);
		AwardsT.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardsT.setBounds(150,155,300,30);
		
		AwardsL = new JLabel("Награда: ");
		AwardsL.setBounds(75, 155, 100, 30);
		AwardsL.setFont(new Font("Arial", Font.PLAIN, 15));

		aA.add(DogNameT);
		aA.add(DogNameL);

		aA.add(OwnerNameT);
		aA.add(OwnerNameL);
		
		aA.add(AwardsT);
		aA.add(AwardsL);
		
		aA.add(BreedT);
		aA.add(BreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
		//#####################################################
		//Расшифровка кнопки "Добавить"
		//#####################################################
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
					String dogName = DogNameT.getText();
					String ownerName = OwnerNameT.getText();
					Matcher matcher = pattern_person_name.matcher(ownerName);
					if(matcher.matches()){
						Matcher matcher_1 = pattern_dog_name.matcher(dogName);
						if(matcher_1.matches()){
							String breedTitle = BreedT.getSelectedItem().toString();
							String awardTitle = AwardsT.getSelectedItem().toString();
							Breed tempBreed = findByTitle(BreedList.toArray(new Breed[0]), breedTitle);
							Award tempAward = findByTitle(AwardList.toArray(new Award[0]), awardTitle);

							List<Owner> owL = OwnerDao.getOwners();
							List<Dog> dgL = DogDao.getDog();

							boolean notDogExist = true;
							boolean notOwnerExist = true;

							for (int i =0; i<owL.size(); i++){
								if(owL.get(i).getName().equals(ownerName)){
									notOwnerExist = false;
								}
							}

							for (int i =0; i<dgL.size(); i++){
								if(dgL.get(i).getName().equals(dogName)){
									notDogExist = false;
								}
							}

							if(notDogExist){
								if(notOwnerExist){
									int dogID = DogDao.addDog(dogName, tempBreed, tempAward);
									OwnerDao.addOwner(ownerName, DogDao.findDog(dogID));

									DogList.add(DogDao.findDog(dogID));

									((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
									Dog DgAr[] = DogList.toArray(new Dog[0]);
									for (int i =0; i<DgAr.length; i++) {
										if(DgAr[i].getAward().getId()==450) {
											((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getBreed().getTitle() , "-"});
										}
										else {
											((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getBreed().getTitle() , "+"});
										}
									}
								}
								else{
									JOptionPane.showMessageDialog(aA, "Имя владельца занято");
								}
							}
							else{
								JOptionPane.showMessageDialog(aA, "Кличка занята");
							}
						}
						else{
							JOptionPane.showMessageDialog(aA, "Кличка собаки начинается с заглавной буквы и может содержать числовой индекс, отделённый ОДНИМ пробелом от буквенного слова.");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(aA, "Имя владельца содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом, в фамилии возможен один дефис. Возможно добавление числового индекса через пробел от фамилии.");
					}
			}});
		
		
	};
	
	public Dog findDogByDogID(Dog DgAr[], int dogid) {
		Dog res = null;
		for (int i =0; i<DgAr.length; i++) {
			if (DgAr[i].getId()==dogid) {
				res = DgAr[i];
			}
		}
		return res;
	}
	
	public Breed findByID(Breed BreedArr[], int id) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getId() == id) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	public Award findByID(Award AwardArr[], int id) {
		Award res = new Award();
		for (int i =0; i<AwardArr.length; i++) {
			if (AwardArr[i].getId() == id) {
				res = AwardArr[i];
			}
		}
		return res;
	}
	
	public static Breed findByTitle(Breed BreedArr[], String title) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getTitle() == title) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	public static Award findByTitle(Award AwardArr[], String title) {
		Award res = new Award();
		for (int i =0; i<AwardArr.length; i++) {
			if (AwardArr[i].getTitle() == title) {
				res = AwardArr[i];
			}
		}
		return res;
	}
	// public static void main(String[] args) {
	// 	new NewDogOwnerGUI().show(null);
	// }
}
