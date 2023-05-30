package DogExhebition;

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

public class NewAwardGUI {
	private JFrame aA;
	private JButton apply;
	private JTextField OwnerNameT;
	private JTextField DogNameT;
	private JComboBox AwardT;
	private JLabel OwnerNameL;
	private JLabel DogNameL;
	private JLabel AwardL;
	private JLabel title;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private JTextField idT;
	private JLabel idL;

	public void show (final JTable table1, final ArrayList<Award> AwardList, final ArrayList<Dog> DogList)
	{
		aA = new JFrame("");
		aA.setTitle("Добавить призёра");
		aA.setSize(500, 300);
		
		title = new JLabel("добавить призёра");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 220, 120, 40);
		
		
		DogNameT = new JTextField();
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,70,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,70,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		String awards[] = {"Большая кость в золотом","Большая кость в светлом","Большая кость в тёмном","Малая кость в золотом", "Малая кость в светлом", "Малая кость в тёмном"};
		AwardT = new JComboBox(awards);
		AwardT.setFont(new Font("Arial", Font.PLAIN, 15));
		AwardT.setBounds(150,110,300,30);
		
		AwardL = new JLabel("Награда: ");
		AwardL.setBounds(30,110,150,30);
		AwardL.setFont(new Font("Arial", Font.PLAIN, 15));
		
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
				Dog tempDog = findByID(DogList.toArray(new Dog[0]), Integer.parseInt(idT.getText()));
				if (tempDog == null) {
					tempDog = findByTitle(DogList.toArray(new Dog[0]), DogNameT.getText());
				}
				tempDog.setDog(tempDog.getName(), tempDog.getBreed(), findByTitle(AwardList.toArray(new Award[0]), AwardT.getSelectedItem().toString()), tempDog.getId());
				Object [] ar = {idT.getText(),DogNameT.getText()};
		        DefaultTableModel model = (DefaultTableModel) table1.getModel();
		        model.addRow(ar);
			}});
		
		aA.add(AwardT);
		aA.add(AwardL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	public Dog findByID(Dog doglist[], int id) {
		Dog res = null;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getId()==id) {
				res = doglist[i];
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
	public Award findByTitle(Award awardlist[], String title) {
		Award res = null;
		for (int i =0; i<awardlist.length; i++) {
			if (awardlist[i].getTitle().contains(title)) {
				res = awardlist[i];
			}
		}
		return res;
	}
	public static void main(String[] args) {
		new NewAwardGUI().show(null, null, null);
	}
}
