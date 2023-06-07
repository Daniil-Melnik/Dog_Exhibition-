package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private static JComboBox<String> ExistOwnerT;
	private static JLabel OwnerNameL;
	private static JLabel DogNameL;
	private static JLabel BreedL;
	private static JLabel AwardsL;
	private static JLabel title_dog;
	private static JCheckBox newOwner;

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

        List<Judge> tJ = null;
        tJ=JudgeDao.getJudges();
        for (int i =0; i<tJ.size(); i++){
            Judge jB = tJ.get(i);
            System.out.println(jB.getId() + " " + jB.getName());
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
		aA.setSize(600, 400);
		aA.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add5.png").getImage());
		
		title_dog = new JLabel("Добавить собаку");
		title_dog.setBounds(200, 20, 200, 30);
		title_dog.setFont(new Font("Arial", Font.PLAIN, 25));
		aA.add(title_dog);
		
		apply = new JButton("Добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 20));
		apply.setBounds(180, 320, 200, 35);
		
		
		DogNameT = new JTextField();
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,70,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,70,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));	
		
		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(150,190,300,30);

		List<Owner> oL = OwnerDao.getOwners();
		ArrayList<String> ownerStr = new ArrayList<>();
		for (int i=0; i< oL.size(); i++){
			Owner owl = oL.get(i);
			ownerStr.add(owl.getName());
		}
		ExistOwnerT = new JComboBox<String>(ownerStr.toArray(new String[0]));
		ExistOwnerT.setBounds(150, 190, 300, 30);
		ExistOwnerT.setFont(new Font("Arial", Font.PLAIN, 15));

		//ExistOwnerL = new JLabel("Имя владельца: ")

		newOwner = new JCheckBox("Новый владелец");
		newOwner.setFont(new Font("Arial", Font.PLAIN, 15));
		newOwner.setBounds(185, 230, 150, 20);

		OwnerNameT.setVisible(false);

		newOwner.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					ExistOwnerT.setVisible(false);
					OwnerNameT.setVisible(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					ExistOwnerT.setVisible(true);
					OwnerNameT.setVisible(false);
				}
			}
		});
		
		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(30,190,150,30);
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
		AwardsT.setBounds(150,150,300,30);
		
		AwardsL = new JLabel("Награда: ");
		AwardsL.setBounds(75, 150, 100, 30);
		AwardsL.setFont(new Font("Arial", Font.PLAIN, 15));

		aA.add(DogNameT);
		aA.add(DogNameL);

		aA.add(ExistOwnerT);
		aA.add(newOwner);

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
					Owner usOwner = null;
					String ownerName = "";

					if (newOwner.isSelected()){
						ownerName = OwnerNameT.getText();
					}
					if(!newOwner.isSelected()){
						ownerName = ExistOwnerT.getSelectedItem().toString();
					}
					
					Matcher matcher = pattern_person_name.matcher(ownerName);
					if(matcher.matches()){
						Matcher matcher_1 = pattern_dog_name.matcher(dogName);
						if(matcher_1.matches()){
							if (newOwner.isSelected()){
								ownerName = OwnerNameT.getText();
								int OwnerID = OwnerDao.addOwner(ownerName);
								usOwner = OwnerDao.findOwner(OwnerID);
								
							}
							if(!newOwner.isSelected()){
								ownerName = ExistOwnerT.getSelectedItem().toString();
								List <Owner> oL = OwnerDao.getOwners();
								for (int i =0; i< oL.size(); i++){
									Owner ol = oL.get(i);
									if (ol.getName().equals(ownerName)){
										usOwner = ol;
									}
								}
							}
							String breedTitle = BreedT.getSelectedItem().toString();
							String awardTitle = AwardsT.getSelectedItem().toString();
							Breed tempBreed = findByTitle(BreedList.toArray(new Breed[0]), breedTitle);
							Award tempAward = findByTitle(AwardList.toArray(new Award[0]), awardTitle);

							DogDao.addDog(dogName, tempBreed, tempAward, usOwner);

							

							List<Dog> dL = DogDao.getDog();
							((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
							
							for (int i =0; i<dL.size(); i++) {
								Dog DgAr = dL.get(i);
								if(DgAr.getAward().getId()==450) {
									((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr.getId(), DgAr.getName(), DgAr.getBreed().getTitle() , "-"});
								}
								else {
									((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr.getId(), DgAr.getName(), DgAr.getBreed().getTitle() , "+"});
								}
							}
							aA.dispose();
						}
						else{
							JOptionPane.showMessageDialog(aA, "Кличка собаки начинается с заглавной буквы и может содержать числовой индекс, отделённый ОДНИМ пробелом от буквенного слова.");
						}
						
					}
					else{
						JOptionPane.showMessageDialog(aA, "Имя владельца на русском языке содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом.\nВ фамилии возможен один дефис.\nВозможно добавление числового индекса через пробел от фамилии.");
					}
			}});
		
		
	};
		
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
