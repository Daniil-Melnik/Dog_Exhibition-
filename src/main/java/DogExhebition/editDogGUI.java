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

public class editDogGUI {
	private JFrame aA;
	private JButton apply;
	private JTextField DogNameT;
	private JTextField OwnerNameT;
	private JComboBox<String> AwardsT;
	private JComboBox<String> OwnerExistT;
	private JComboBox<String> BreedT;
	private JLabel AwardL_1;
	private JLabel BreedL_1;
	private JLabel DogNameL;
	private JLabel OwnerNameL;
	private JLabel BreedL;
	private JLabel AwardsL;
	private JLabel OwnerL_1;
	private JLabel title;

	private JCheckBox breedCheck;
	private JCheckBox awardCheck;
	private JCheckBox ownerCheck;
	private JCheckBox newOwner;

	public void show (final JTable table1, int id)
	{
		String regex_dog_name = "^[А-Я]{1}[а-я]*( {1}[0-9]+)?$";
		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		Pattern pattern_dog_name = Pattern.compile(regex_dog_name);
		Pattern pattern_person_name = Pattern.compile(regex_person_name);
		final ArrayList<Breed> BreedList = new ArrayList<>();
        final ArrayList<Award> AwardList = new ArrayList<>();
        
        final ArrayList<Dog> DogList = new ArrayList<>();     
        
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

		aA = new JFrame("");
		aA.setTitle("Изменить данные");
		aA.setSize(500, 450);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		Dog edDog = DogDao.findDog(id);
		apply = new JButton("Изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 370, 120, 40);
		
		DogNameT = new JTextField(edDog.getName());
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,70,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,70,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));				
				
		BreedL = new JLabel("Порода собаки: ");
		BreedL.setBounds(30,150,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));

		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(30,110,150,30);
		OwnerNameL.setFont(new Font("Arial", Font.PLAIN, 15));	
		
		AwardsT = new JComboBox<String>();

		aA.add(OwnerNameL);
		
		ArrayList<String> awards = new ArrayList<>();
		for (int i=0; i< AwardList.size(); i++){
			Award usAw = AwardList.get(i);
			if (!usAw.getTitle().contains(edDog.getAward().getTitle())){
				awards.add(usAw.getTitle());
			}
		}
		AwardsT = new JComboBox<String>(awards.toArray(new String[0]));
		AwardsT.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardsT.setBounds(150,190,300,30);
		 
		AwardsL = new JLabel("Награды: ");
		AwardsL.setBounds(75, 190, 100, 30);
		AwardsL.setFont(new Font("Arial", Font.PLAIN, 15));

		ArrayList<String> breeds = new ArrayList<>();
		List<Breed> brL = BreedDao.getBreeds();
		for (int i =0; i<brL.size(); i++){
			if (!brL.get(i).getTitle().contains(edDog.getBreed().getTitle())){
				breeds.add(brL.get(i).getTitle());
			}
		}

		BreedT = new JComboBox<String>(breeds.toArray(new String[0]));
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,150,300,30);

		BreedT.setEnabled(false);
		AwardsT.setEnabled(false);

		newOwner = new JCheckBox("Новый владелец");
		newOwner.setBounds(10, 230, 200, 20);
		newOwner.setFont(new Font("Arial", Font.PLAIN, 15));
		newOwner.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					OwnerNameT.setVisible(true);
					OwnerExistT.setVisible(false);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					OwnerNameT.setVisible(false);
					OwnerExistT.setVisible(true);
				}
			}
		});


		breedCheck = new JCheckBox("Изменить породу");
		breedCheck.setBounds(10, 260, 200, 20);
		breedCheck.setFont(new Font("Arial", Font.PLAIN, 15));

		breedCheck.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					BreedT.setEnabled(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					BreedT.setEnabled(false);
				}
			}
		});

		awardCheck = new JCheckBox("Изменить награду");
		awardCheck.setBounds(10, 290, 200, 20);
		awardCheck.setFont(new Font("Arial", Font.PLAIN, 15));

		awardCheck.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					AwardsT.setEnabled(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					AwardsT.setEnabled(false);
				}
			}
		});
		ArrayList<String> oAL = new ArrayList<>();
		List<Owner> oL = OwnerDao.getOwners();
		for (int i =0; i< oL.size(); i++){
			Owner ol = oL.get(i);
			if(ol.getId()!=DogDao.findDog(id).getOwner().getId()){
				oAL.add(ol.getName());
			}
		}
		OwnerExistT = new JComboBox<String>(oAL.toArray(new String[0]));
		OwnerExistT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerExistT.setBounds(150,110,300,30);
		OwnerExistT.setEnabled(false);
		aA.add(OwnerExistT);

		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(150,110,300,30);
		OwnerNameT.setEnabled(false);
		aA.add(OwnerNameT);
		OwnerNameT.setEnabled(false);
		OwnerNameT.setVisible(false);

		ownerCheck = new JCheckBox("Изменить владельца");
		ownerCheck.setBounds(10, 320, 200, 25);
		ownerCheck.setFont(new Font("Arial", Font.PLAIN, 15));

		ownerCheck.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					OwnerExistT.setEnabled(true);
					OwnerNameT.setEnabled(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					OwnerExistT.setEnabled(false);
					OwnerNameT.setEnabled(false);
				}
			}
		});
		
		aA.add(breedCheck);
		aA.add(awardCheck);
		aA.add(ownerCheck);
		aA.add(newOwner);

		BreedL_1 = new JLabel("Порода : "+edDog.getBreed().getTitle());
		BreedL_1.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedL_1.setBounds(210, 260, 400, 25);
		aA.add(BreedL_1);

		AwardL_1 = new JLabel("Награда : "+edDog.getAward().getTitle());
		AwardL_1.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardL_1.setBounds(210, 290, 400, 25);
		aA.add(AwardL_1);

		OwnerL_1 = new JLabel("Владелец : "+edDog.getOwner().getName());
		OwnerL_1.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerL_1.setBounds(210, 320, 400, 25);
		aA.add(OwnerL_1);
		
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String newName = DogNameT.getText();
				Matcher matcher_1 = pattern_dog_name.matcher(newName);
				if(matcher_1.matches()){
					Breed edBreed = edDog.getBreed();
					Award edAward = edDog.getAward();
					Owner edOwner = edDog.getOwner();
					
						if(breedCheck.isSelected()){
							String breedTitle = BreedT.getSelectedItem().toString();
							
							List <Breed> breeds = BreedDao.getBreeds();
							for (int i =0; i<breeds.size(); i++){
								if (breeds.get(i).getTitle().contains(breedTitle)){
									edBreed = breeds.get(i);
								}
							}
							edDog.setBreed(edBreed);
						}

						if (awardCheck.isSelected()){
							String awardTitle_1 = AwardsT.getSelectedItem().toString();
							System.out.println(awardTitle_1);
							List <Award> awards = AwardDao.getAwards();
							for (int i =0; i<awards.size(); i++){
								if (awards.get(i).getTitle().contains(awardTitle_1)){
									edAward = awards.get(i);
								}
							}
						}

						if (ownerCheck.isSelected()){
							if(!newOwner.isSelected()){
								String ownerTitle_1 = OwnerExistT.getSelectedItem().toString();
								List <Owner> owners = OwnerDao.getOwners();
								for (int i =0; i<owners.size(); i++){
									if (owners.get(i).getName().equals(ownerTitle_1)){
										edOwner = owners.get(i);
									}
								}
								int k = 0;
								Dog eDog = DogDao.findDog(id);
								List<Dog> dggL = DogDao.getDog();
								for (int i =0; i<dggL.size(); i++){
									Dog dgl = dggL.get(i);
									if(dgl.getOwner().getId()==edDog.getOwner().getId()){
										k++;
									}
								}
								if(k==1){
									OwnerDao.deleteOwner(eDog.getOwner().getId());
								}
							}
							if(newOwner.isSelected()){
								String ownerTitle_1 = OwnerNameT.getText();
								Matcher matcher_2 = pattern_person_name.matcher(ownerTitle_1);
								if(matcher_2.matches()){
									int ownerID = OwnerDao.addOwner(ownerTitle_1);
									edOwner = OwnerDao.findOwner(ownerID);
								}
								else{
									JOptionPane.showMessageDialog(aA, "Имя владельца на русском языке содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом.\nВ фамилии возможен один дефис.\nВозможно добавление числового индекса через пробел от фамилии.");
								}

							}
							
						}

						DogDao.editDog(newName, edBreed, edAward, edOwner, id);

						List<Dog> dgL = DogDao.getDog();
						((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
						for (int i =0; i<dgL.size(); i++){
							Dog DgAr = dgL.get(i);
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
			}});
		
		aA.add(DogNameT);
		aA.add(DogNameL);
		
		aA.add(AwardsT);
		aA.add(AwardsL);
		
		aA.add(BreedT);
		aA.add(BreedL);
		
		//aA.add(judgeT);
		//aA.add(judgeL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
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
	
	
	public static void main(String[] args) {
		new editDogGUI().show(null, 0);
	}
}
