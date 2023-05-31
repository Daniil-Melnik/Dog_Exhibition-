package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewBreedGUI {

	private JFrame aA;
	private JButton apply;
	private JTextField BreedTitleT;
	private JLabel BreedTitleL;
	private JLabel title;
	
	private JTextField idT;
	private JLabel idL;

	public void show (final JTable table1, final ArrayList<Award> AwardList, final ArrayList<Dog> DogList, final ArrayList<Breed> BreedList)
	{
		aA = new JFrame("");
		aA.setTitle("Добавить породу");
		aA.setSize(500, 235);
		
		title = new JLabel("добавить породу");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 150, 120, 40);
		
		
		BreedTitleT = new JTextField();
		BreedTitleT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedTitleT.setBounds(150,70,300,30);
		
		BreedTitleL = new JLabel("Название породы: ");
		BreedTitleL.setBounds(20,70,150,30);
		BreedTitleL.setFont(new Font("Arial", Font.PLAIN, 15));
				
		idT = new JTextField("");
		idT.setFont(new Font("Arial", Font.PLAIN, 15));
        idT.setBounds(150,110,50,30);
        
        idL = new JLabel("id: ");
        idL.setBounds(120,110,50,30);
        idL.setFont(new Font("Arial", Font.PLAIN, 15));
        
        aA.add(idT);
        aA.add(idL);
        aA.add(BreedTitleL);
        aA.add(BreedTitleT);
		
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{	
				Breed tempBreed = new Breed();
				tempBreed.setBreed(BreedTitleT.getText() ,Integer.parseInt(idT.getText()));
				BreedList.add(tempBreed);
				DefaultTableModel model = (DefaultTableModel) table1.getModel();
				Object [] ar = {tempBreed.getId(), tempBreed.getTitle()};
		        model.addRow(ar);
				aA.dispose();
			}});
		
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

}
