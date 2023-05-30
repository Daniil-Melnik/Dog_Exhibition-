package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewOwnerGUI {
	private JFrame aA;
	private JButton apply;
	private JTextField OwnerNameT;
	private JComboBox DogNameT;
	private JComboBox DogNameT1;
	private JComboBox BreedT;
	private JLabel OwnerNameL;
	private JLabel DogNameL;
	private JLabel DogNameL1;
	private JLabel BreedL;
	private JLabel title;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private JTextField idT;
	private JLabel idL;

	public void show (final JTable table1, ArrayList<Breed> BreedList, final ArrayList<Dog> PoorDogList, final ArrayList<Owner> OwnerList)
	{
		
		final ArrayList<String> BrAr = new ArrayList<>();
		Breed helpBreed [] = BreedList.toArray(new Breed[0]);
		
		for (int i =0; i<helpBreed.length; i++) {
			BrAr.add(helpBreed[i].getTitle());
		}
		aA = new JFrame("");
		aA.setTitle("Добавить владельца");
		aA.setSize(500, 275);
		
		title = new JLabel("добавить владельца");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 190, 120, 40);
		
		
		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(150,70,300,30);
		
		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(30,70,150,30);
		OwnerNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		ArrayList<String> dogs = new ArrayList<>();
		Dog DgAr[] = PoorDogList.toArray(new Dog[0]);
		for (int i = 0; i<DgAr.length; i++) {
			dogs.add(DgAr[i].getName());
		}
		
		DogNameT = new JComboBox(dogs.toArray(new String[0]));
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,110,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,110,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		idT = new JTextField("");
		idT.setFont(new Font("Arial", Font.PLAIN, 15));
        idT.setBounds(150,150,50,30);
        
        idL = new JLabel("id: ");
        idL.setBounds(120,150,50,30);
        idL.setFont(new Font("Arial", Font.PLAIN, 15));
        
        aA.add(idT);
        aA.add(idL);
        aA.add(DogNameL);
        aA.add(DogNameT);
		
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{	
				Owner dobOwner = null;
				boolean dobal = false;
				if (findOwnerByOwnerID(OwnerList.toArray(new Owner[0]), Integer.parseInt(idT.getText()))!=null) {
					dobal = true;
					dobOwner = findOwnerByOwnerID(OwnerList.toArray(new Owner[0]), Integer.parseInt(idT.getText()));
				}
				if(dobal) {
					if((dobOwner.getName().contains(OwnerNameT.getText()))&&dobal) {
						Dog tempDog = findDogByDogName(PoorDogList.toArray(new Dog[0]),DogNameT.getSelectedItem().toString());
						Object [] ar = {idT.getText(), OwnerNameT.getText(),tempDog.getName() ,tempDog.getBreed().getTitle()};
						Owner tempOwner = new Owner();
						tempOwner.setOwner(tempDog, OwnerNameT.getText(), Integer.parseInt(idT.getText()));
						OwnerList.add(tempOwner);
						PoorDogList.remove(findNumByDogID(PoorDogList.toArray(new Dog[0]), tempDog.getId()));
				        DefaultTableModel model = (DefaultTableModel) table1.getModel();
				        model.addRow(ar);
				        aA.dispose();
					}
					else {
						JOptionPane.showMessageDialog (aA, "Имя владельца не соответсвует id");
					}
				}
				if(dobal==false) {
					Dog tempDog = findDogByDogName(PoorDogList.toArray(new Dog[0]),DogNameT.getSelectedItem().toString());
					Object [] ar = {idT.getText(), OwnerNameT.getText(),tempDog.getName() ,tempDog.getBreed().getTitle()};
					Owner tempOwner = new Owner();
					tempOwner.setOwner(tempDog, OwnerNameT.getText(), Integer.parseInt(idT.getText()));
					OwnerList.add(tempOwner);
					PoorDogList.remove(findNumByDogID(PoorDogList.toArray(new Dog[0]), tempDog.getId()));
			        DefaultTableModel model = (DefaultTableModel) table1.getModel();
			        model.addRow(ar);
			        aA.dispose();
				}
			}});
		
		aA.add(OwnerNameT);
		aA.add(OwnerNameL);
		
//		aA.add(BreedT);
//		aA.add(BreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	
	public Dog findDogByDogName(Dog doglist[], String name) {
		Dog res = null;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getName()==name) {
				res = doglist[i];
			}
		}
		return res;
	}
	public Owner findOwnerByOwnerID(Owner ownerlist[], int id) {
		Owner res = null;
		for (int i =0; i<ownerlist.length; i++) {
			if (ownerlist[i].getId()==id) {
				res = ownerlist[i];
			}
		}
		return res;
	}
	
	public Owner findOwnerByDogName(Owner ownerlist[], String dogName) {
		Owner res = null;
		for (int i =0; i<ownerlist.length; i++) {
			if (ownerlist[i].getDog().getName().contains(dogName)) {
				res = ownerlist[i];
			}
		}
		return res;
	}
	
	public int findNumByDogID(Dog doglist[], int id) {
		int res = -1;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getId()==id) {
				res = i;
			}
		}
		return res;
	}
	//public static void main(String[] args) {
	//	new NewOwnerGUI().show(null);
	//}

}
