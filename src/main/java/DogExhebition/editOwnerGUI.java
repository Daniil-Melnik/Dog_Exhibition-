package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class editOwnerGUI {

	private JFrame aA;
	private JButton apply;
	private JComboBox<String> DogNameT;

	private JLabel DogNameL;

	private JLabel title;
	private JCheckBox newDogEd;
	
	private JTextField OwnerNameT;
	private JLabel OwnerNameL;
	private JLabel dog_label;

	public void show (final JTable table1, String lastName, Dog dog, Owner owner)
	{
		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		Pattern pattern_person_name = Pattern.compile(regex_person_name);

		final ArrayList<Breed> BreedList = new ArrayList<>();
        
        final ArrayList<Dog> DogList = new ArrayList<>();
        final ArrayList<Owner> OwnerList = new ArrayList<>();
     
        
        final ArrayList<String> BrAr = new ArrayList<>();

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
		for (int i=0; i<tD.size(); i++){
			DogList.add(tD.get(i));
		}
		for (int i=0; i<tO.size(); i++){
			OwnerList.add(tO.get(i));
		}

		aA = new JFrame("");
		aA.setTitle("Изменить данные");
		aA.setSize(500, 305);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 220, 120, 40);
		
		
		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(150,70,300,30);
		OwnerNameT.setText(lastName);
		
		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(30,70,150,30);
		OwnerNameL.setFont(new Font("Arial", Font.PLAIN, 15));

		newDogEd = new JCheckBox("Замена собаки");
		newDogEd.setBounds(25, 105, 120, 45);
		newDogEd.setHorizontalTextPosition(JCheckBox.LEFT);
		aA.add(newDogEd);		
		
		ArrayList<String> dogs = new ArrayList<>();
		Dog DgAr[] = DogList.toArray(new Dog[0]);
		for (int i = 0; i<DgAr.length; i++) {
			if (DgAr[i].getId()!=dog.getId()){
				dogs.add(DgAr[i].getName());
			}
		}
		
		DogNameT = new JComboBox<String>(dogs.toArray(new String[0]));		
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,110,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,150,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		DogNameT.setEnabled(false);

		dog_label = new JLabel("Собака : " + dog.getName());
		dog_label.setBounds(30, 150, 300, 20);
		dog_label.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(dog_label);
        aA.add(DogNameT);

		newDogEd.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					DogNameT.setEnabled(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					DogNameT.setEnabled(false);
				}
			}
		});
				
		aA.add(OwnerNameT);
		aA.add(OwnerNameL);
        
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{	
					String own = OwnerNameT.getText();

					Matcher matcher = pattern_person_name.matcher(own);
					if(matcher.matches()){
						List<Owner> owL = OwnerDao.getOwners();

					boolean notOwnerExist = true;

					for (int i =0; i<owL.size(); i++){
						if((owL.get(i).getName().equals(own))&&(!owL.get(i).getName().equals(owner.getName()))){
							notOwnerExist = false;
						}
					}

					if(notOwnerExist){
						if (newDogEd.isSelected()){
							Dog secondDog = null;
							List<Dog> DogList_1 = DogDao.getDog();
							for (int i =0; i<DogList_1.size(); i++){
								Dog dG = DogList_1.get(i);
								if (dG.getName().contains(DogNameT.getSelectedItem().toString())){
									secondDog = dG;
								}
							}
							Owner SecondOwner = null;
							List<Owner> OwnerList_1 = OwnerDao.getOwners();
							for (int i = 0; i<OwnerList_1.size(); i++){
								Owner OL = OwnerList_1.get(i);
								if (OL.getDog().getId()==secondDog.getId()){
									SecondOwner = OL;
								}
							}
							OwnerDao.editOwner(own, secondDog, owner.getId());
							OwnerDao.editOwner(SecondOwner.getName(), dog, SecondOwner.getId());
						}
						else{
							OwnerDao.editOwner(own, dog, owner.getId());
						}
						
						((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
						List<Owner> tJ1=OwnerDao.getOwners();
						for (int i =0; i<tJ1.size(); i++){
							Owner jB = tJ1.get(i);
							((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{jB.getId(), jB.getName(), jB.getDog().getName(), jB.getDog().getBreed().getTitle()});		
						}
						aA.dispose();
					}
					else{
						JOptionPane.showMessageDialog(aA, "Имя владельца занято");
					}
					}						
					else{
						JOptionPane.showMessageDialog(aA, "Имя владельца содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом, в фамилии возможен один дефис. Возможно добавление числового индекса через пробел от фамилии.");
					}

					
			}});
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	public static boolean isNumericID(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	  }
	
	public int FindNumDogByDogID(Dog poorDogs[], int id) {
		int res = -1;
		for (int i =0; i<poorDogs.length; i++) {
			if (poorDogs[i].getId()==id) {
				res = i;
			}
		}
		return res;
	}
	
	public Dog findByID(Dog doglist[], int id) {
		Dog res = null;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getId()==id) {
				res = doglist[i];
			}
		}
		return res;
	}
	public Owner findByID(Owner Ownerlist[], int id) {
		Owner res = null;
		for (int i =0; i<Ownerlist.length; i++) {
			if (Ownerlist[i].getId()==id) {
				res = Ownerlist[i];
			}
		}
		return res;
	}
	public Dog findByTitle(Dog doglist[], String name) {
		Dog res = null;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getName().contains(name)) {
				res = doglist[i];
			}
		}
		return res;
	}
	public Breed findByTitle(Breed BreedArr[], String title) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getTitle() == title) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	
	public Owner findOwnerByDogName(Owner OwAr[], String name) {
		Owner res = null;
		for (int i=0; i<OwAr.length; i++) {
			if(OwAr[i].getDog()!=null) {
				if (OwAr[i].getDog().getName().contains(name)) {
					res = OwAr[i];
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		new editOwnerGUI().show(null, "старое владельца", null, null);
	}

}
