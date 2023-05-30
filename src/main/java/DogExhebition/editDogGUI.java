package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

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
	private JTextField OwnerNameT;
	private JTextField DogNameT;
	private JComboBox AwardsT;
	private JComboBox BreedT;
	private JLabel AwardL_1;
	private JLabel BreedL_1;
	private JLabel DogNameL;
	private JLabel BreedL;
	private JLabel AwardsL;
	private JLabel title;

	private JCheckBox breedCheck;
	private JCheckBox awardCheck;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private JTextField idT;
	private JLabel idL;

	public void show (final JTable table1, int id)
	{

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

		aA = new JFrame("");
		aA.setTitle("Изменить данные");
		aA.setSize(500, 400);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		Dog edDog = DogDao.findDog(id);
		apply = new JButton("Изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 320, 120, 40);
		
		DogNameT = new JTextField(edDog.getName());
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,70,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,70,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));				
				
		BreedL = new JLabel("Порода собаки: ");
		BreedL.setBounds(30,110,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		AwardsT = new JComboBox();
		
		ArrayList<String> awards = new ArrayList<>();
		for (int i=0; i< AwardList.size(); i++){
			Award usAw = AwardList.get(i);
			if (!usAw.getTitle().contains(edDog.getAward().getTitle())){
				awards.add(usAw.getTitle());
			}
		}
		AwardsT = new JComboBox(awards.toArray(new String[0]));
		AwardsT.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardsT.setBounds(150,150,300,30);
		 
		AwardsL = new JLabel("Награды: ");
		AwardsL.setBounds(75, 150, 100, 30);
		AwardsL.setFont(new Font("Arial", Font.PLAIN, 15));

		ArrayList<String> breeds = new ArrayList<>();
		List<Breed> brL = BreedDao.getBreeds();
		for (int i =0; i<brL.size(); i++){
			if (!brL.get(i).getTitle().contains(edDog.getBreed().getTitle())){
				breeds.add(brL.get(i).getTitle());
			}
		}

		BreedT = new JComboBox(breeds.toArray(new String[0]));
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,110,300,30);

		BreedT.setEnabled(false);
		AwardsT.setEnabled(false);

		breedCheck = new JCheckBox("Изменить породу");
		breedCheck.setBounds(70, 190, 200, 20);
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
		awardCheck.setBounds(300, 190, 200, 20);
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
		
		aA.add(breedCheck);
		aA.add(awardCheck);

		BreedL_1 = new JLabel("Порода : "+edDog.getBreed().getTitle());
		BreedL_1.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedL_1.setBounds(50, 230, 400, 25);
		aA.add(BreedL_1);

		AwardL_1 = new JLabel("Награда : "+edDog.getAward().getTitle());
		AwardL_1.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardL_1.setBounds(50, 270, 400, 25);
		aA.add(AwardL_1);
		
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String newName = DogNameT.getText();
				Breed edBreed = edDog.getBreed();
				Award edAward = edDog.getAward();


				List<Dog> dgL = DogDao.getDog();

				boolean notDogExist = true;


				for (int i =0; i<dgL.size(); i++){
					if((dgL.get(i).getName().equals(newName))&&(!dgL.get(i).getName().equals(edDog.getName()))){
						notDogExist = false;
					}
				}
				if(notDogExist){
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
						edDog.setAward(edAward);
					}

					DogDao.editDog(newName, edBreed, edAward, id);

					Dog edDog_1 = findDogByDogID(DogList.toArray(new Dog[0]), id);
					edDog_1.setDog(newName, edBreed, edAward, id);
					((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
					for (int i =0; i<DogList.size(); i++){
						Dog DgAr = DogList.get(i);
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
					JOptionPane.showMessageDialog(aA, "Кличка занята");
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
	
	int findByID(Dog dogs[], int id) {
		int res=0;
		for (int i =0; i< dogs.length; i++) {
			if (dogs[i].getId()==Integer.parseInt(idT.getText())) {
				res = i;
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
	
	public Breed findByTitle(Breed BreedArr[], String title) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getTitle() == title) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	public Award findByTitle(Award AwardArr[], String title) {
		Award res = new Award();
		for (int i =0; i<AwardArr.length; i++) {
			if (AwardArr[i].getTitle() == title) {
				res = AwardArr[i];
			}
		}
		return res;
	}
	public static boolean isNumericID(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	  }
	public static void main(String[] args) {
		new editDogGUI().show(null, 0);
	}
}
