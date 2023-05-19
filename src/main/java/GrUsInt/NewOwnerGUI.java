package GrUsInt;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewOwnerGUI {
	private JFrame aA;
	private JButton apply;
	private JTextField OwnerNameT;
	private JTextField DogNameT;
	private JComboBox BreedT;
	private JLabel OwnerNameL;
	private JLabel DogNameL;
	private JLabel BreedL;
	private JLabel title;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private JTextField idT;
	private JLabel idL;

	public void show (final JTable table1)
	{
		aA = new JFrame("");
		aA.setTitle("Добавить владельца");
		aA.setSize(500, 400);
		
		title = new JLabel("добавить владельца");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 220, 120, 40);
		
		
		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(150,70,300,30);
		
		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(30,70,150,30);
		OwnerNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		DogNameT = new JTextField();
		DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		DogNameT.setBounds(150,110,300,30);
		
		DogNameL = new JLabel("Кличка собаки: ");
		DogNameL.setBounds(30,110,150,30);
		DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		String breeds[] = {"борзая","лайка","дворовая","такса", "овчарка"};
		BreedT = new JComboBox(breeds);
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,150,300,30);
		
		BreedL = new JLabel("Порода собак: ");
		BreedL.setBounds(30,150,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		idT = new JTextField("");
		idT.setFont(new Font("Arial", Font.PLAIN, 15));
        idT.setBounds(150,190,50,30);
        
        idL = new JLabel("id: ");
        idL.setBounds(120,190,50,30);
        idL.setFont(new Font("Arial", Font.PLAIN, 15));
        
        aA.add(idT);
        aA.add(idL);
        aA.add(DogNameL);
        aA.add(DogNameT);
		
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{				
				Object [] ar = {idT.getText(), OwnerNameT.getText(),DogNameT.getText() ,BreedT.getSelectedItem().toString()};
		        DefaultTableModel model = (DefaultTableModel) table1.getModel();
		        model.addRow(ar);
			}});
		
		aA.add(OwnerNameT);
		aA.add(OwnerNameL);
		
		aA.add(BreedT);
		aA.add(BreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	//public static void main(String[] args) {
	//	new NewOwnerGUI().show(null);
	//}

}
