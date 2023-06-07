package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.ItemEvent;
// import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
// import javax.swing.JCheckBox;
// import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class editOwnerGUI {

	private JFrame aA;
	private JButton apply;

	private JLabel title;
	
	private JTextField OwnerNameT;
	private JLabel OwnerNameL;

	public void show (final JTable table1, String lastName, Owner owner)
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
        // for (int i =0; i<tO.size(); i++){
        //     Owner jB = tO.get(i);
        //     System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getDog().getName()+" "+jB.getDog().getBreed().getTitle());
        // }

        List<Judge> tJ = null;
        tJ=JudgeDao.getJudges();
        for (int i =0; i<tJ.size(); i++){
            Judge jB = tJ.get(i);
            System.out.println(jB.getId() + " " + jB.getName());
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
		aA.setSize(500, 200);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 120, 120, 40);
		
		
		OwnerNameT = new JTextField();
		OwnerNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		OwnerNameT.setBounds(150,70,300,30);
		OwnerNameT.setText(lastName);
		
		OwnerNameL = new JLabel("Имя владельца: ");
		OwnerNameL.setBounds(30,70,150,30);
		OwnerNameL.setFont(new Font("Arial", Font.PLAIN, 15));

				
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
							OwnerDao.editOwner(own, owner.getId());
							((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
							List<Owner> tJ1=OwnerDao.getOwners();
							for (int i =0; i<tJ1.size(); i++){
								Owner jB = tJ1.get(i);
								((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{jB.getId(), jB.getName()});		
							}
							aA.dispose();
					}
					else{
						JOptionPane.showMessageDialog(aA, "Имя владельца занято");
					}
					}						
					else{
						JOptionPane.showMessageDialog(aA, "Имя владельца на русском языке содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом.\nВ фамилии возможен один дефис.\nВозможно добавление числового индекса через пробел от фамилии.");
					}

					
			}});
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	
	public static void main(String[] args) {
		new editOwnerGUI().show(null, "старое владельца", null);
	}

}
