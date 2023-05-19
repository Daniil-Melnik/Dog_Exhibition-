package GrUsInt;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewDogGUI {
	private JFrame aA;
	private JButton apply;
	private JTextField OwnerNameT;
	private JTextField DogNameT;
	private JComboBox AwardsT;
	private JComboBox BreedT;
	private JLabel OwnerNameL;
	private JLabel DogNameL;
	private JLabel BreedL;
	private JLabel AwardsL;
	private JLabel title;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private JTextField idT;
	private JLabel idL;

	public void show (final JTable table1, final ArrayList<Dog> DogList, ArrayList<String> BrAr, ArrayList<String> AwAr, final ArrayList<Breed> BreedList, final ArrayList<Award> AwardList)
	{
		aA = new JFrame("");
		aA.setTitle("Изменить данные");
		aA.setSize(500, 320);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 240, 120, 40);
		
		
		DogNameT = new JTextField();
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,70,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,70,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));				
		
		String breeds[] = BrAr.toArray(new String[0]);
		//String breeds[] = {};
		BreedT = new JComboBox(breeds);
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,110,300,30);
		
		BreedL = new JLabel("Порода собаки: ");
		BreedL.setBounds(30,110,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		AwardsT = new JComboBox();
		
		String awards[] = AwAr.toArray(new String[0]);
		//String awards[] = {};
		AwardsT = new JComboBox(awards);
		AwardsT.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardsT.setBounds(150,185,300,30);
		
		AwardsL = new JLabel("награды: ");
		AwardsL.setBounds(75, 185, 100, 30);
		AwardsL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		idT = new JTextField("");
		idT.setFont(new Font("Arial", Font.PLAIN, 15));
        idT.setBounds(150,150,50,30);
        
        idL = new JLabel("id: ");
        idL.setBounds(120,150,50,30);
        idL.setFont(new Font("Arial", Font.PLAIN, 15));
        
        aA.add(idT);
        aA.add(idL);
		
		apply.addActionListener(new ActionListener()
		{
			
			public void actionPerformed (ActionEvent event)
			{
				DogList.get(findByID(DogList.toArray(new Dog[0]), Integer.parseInt(idT.getText()))).setDog(DogNameT.getText(), findByTitle(BreedList.toArray(new Breed[0]), BreedT.getSelectedItem().toString()), findByTitle(AwardList.toArray(new Award[0]), AwardsT.getSelectedItem().toString()), Integer.parseInt (idT.getText()));
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
//	public static void main(String[] args) {
//		new NewDogGUI().show(null, null, null, null, null, null);
//	}
}
