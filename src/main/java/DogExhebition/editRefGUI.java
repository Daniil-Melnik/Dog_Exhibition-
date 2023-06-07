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

public class editRefGUI {

	private JFrame aA;
	private JButton apply;
	private JTextField JudgeNameT;
	private JTextField newBreedT;
	private JComboBox<String> BreedT;
	private JComboBox<String> RemBreedT;
	private JLabel JudgeNameL;
	private JLabel AddBreedL;
	private JLabel RemBreedL;
	private JLabel title;
	private JCheckBox breedAddCheck;
	private JCheckBox breedRemCheck;
	private JCheckBox breedNewCheck;

	public void show (final JTable table1, int id)
	{

		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		Pattern pattern_person_name = Pattern.compile(regex_person_name);

		Judge edJudge = JudgeDao.findJudge(id);
		final ArrayList<Breed> BreedList = new ArrayList<>();
        final ArrayList<Judge> JudgeList = new ArrayList<>();     
        
        final ArrayList<String> BrAr = new ArrayList<>();

		List<Breed> tB = null;
		tB=BreedDao.getBreeds();
        for (int i =0; i<tB.size(); i++){
            Breed jB = tB.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
			BrAr.add(jB.getTitle());
        }

        List<Judge> tJ = null;
        tJ=JudgeDao.getJudges();
        for (int i =0; i<tJ.size(); i++){
            Judge jB = tJ.get(i);
            System.out.println(jB.getId() + " " + jB.getName());
			
        }

		for (int i=0; i<tB.size(); i++){
			BreedList.add(tB.get(i));
		}
		for (int i=0; i<tJ.size(); i++){
			JudgeList.add(tJ.get(i));
		}

		aA = new JFrame("");
		aA.setTitle("Изменить данные");
		aA.setSize(500, 300);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("Изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 215, 120, 40);
		
		
		JudgeNameT = new JTextField(edJudge.getName());
		JudgeNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		JudgeNameT.setBounds(150,70,300,30);

		newBreedT = new JTextField();
		newBreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		newBreedT.setBounds(150,110,300,30);
		newBreedT.setVisible(false);
		
		JudgeNameL = new JLabel("Имя судьи: ");
		JudgeNameL.setBounds(30,70,150,30);
		JudgeNameL.setFont(new Font("Arial", Font.PLAIN, 15));				

		breedNewCheck = new JCheckBox("Новая порода");
		breedNewCheck.setBounds(5, 210, 140, 20);
		breedNewCheck.setFont(new Font("Arial", Font.PLAIN, 15));

		aA.add(newBreedT);

		breedNewCheck.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					BreedT.setVisible(false);
					newBreedT.setVisible(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					BreedT.setVisible(true);
					newBreedT.setVisible(false);
				}
			}
		});

		aA.add(breedNewCheck);



		List<J_B_com> brL = J_B_comDao.getComs();
		List<J_B_com> brL1 = new ArrayList<>() ;
		for (int i =0; i<brL.size(); i++){
			if(brL.get(i).getJudge().getId()==id){
				brL1.add(brL.get(i));
			}
		}
		List<Breed> bL = BreedDao.getBreeds();
		List<String> brStr = new ArrayList<>();

		for(int i =0; i<bL.size(); i++){
			Breed bl = bL.get(i);
			boolean us = true;
			for(int j=0; j<brL1.size(); j++){
				if(brL1.get(j).getBreed().getId()==bl.getId()){
					us = false;
				}
			}
			if(us){
				brStr.add(bl.getTitle());
			}
		}

		BreedT = new JComboBox<String>(brStr.toArray(new String[0]));
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,110,300,30);

		List<String> brStr_1 = new ArrayList<>();

		for (int i =0; i<bL.size(); i++){
			Breed bl = bL.get(i);
			for (int j =0; j<brL.size(); j++){
				if((brL.get(j).getJudge().getId()==id)&&(brL.get(j).getBreed().getId()==bl.getId())){
					brStr_1.add(bl.getTitle());
				}
			}
		}
		RemBreedT = new JComboBox<String>(brStr_1.toArray(new String[0]));
		RemBreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		RemBreedT.setBounds(150,150,300,30);
		
		AddBreedL = new JLabel("Порода + : ");
		AddBreedL.setBounds(30,110,150,30);
		AddBreedL.setFont(new Font("Arial", Font.PLAIN, 15));

		RemBreedL = new JLabel("Порода - : ");
		RemBreedL.setBounds(30,150,150,30);
		RemBreedL.setFont(new Font("Arial", Font.PLAIN, 15));

		breedAddCheck = new JCheckBox("Добавить породу");

		breedAddCheck.setBounds(5, 190, 170, 20);
		breedAddCheck.setFont(new Font("Arial", Font.PLAIN, 15));;
		BreedT.setEnabled(false);
		breedAddCheck.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					BreedT.setEnabled(true);
					newBreedT.setEnabled(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					BreedT.setEnabled(false);
					newBreedT.setEnabled(false);
				}
			}
		});
		RemBreedT.setEnabled(false);
		breedRemCheck = new JCheckBox("Удалить породу");
		breedRemCheck.setBounds(180, 190, 140, 20);
		breedRemCheck.setFont(new Font("Arial", Font.PLAIN, 15));;
		breedRemCheck.addItemListener((ItemListener) new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					RemBreedT.setEnabled(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					RemBreedT.setEnabled(false);
				}
			}
		});

		aA.add(breedAddCheck);
		aA.add(breedRemCheck);
		
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String judgeName = JudgeNameT.getText();
				
				Matcher matcher = pattern_person_name.matcher(judgeName);
				if(matcher.matches()){

				Breed secondBreed = null;;				
				if(breedAddCheck.isSelected()){
					if(breedNewCheck.isSelected()){
						String newBreedTitle = newBreedT.getText();
						int breedID = BreedDao.addBreed(newBreedTitle);
						secondBreed = BreedDao.findBreed(breedID);
					}
					if(!breedNewCheck.isSelected()){
						String newBreedTitle = BreedT.getSelectedItem().toString();
						List<Breed> bL = BreedDao.getBreeds();
						for (int i =0; i<bL.size(); i++){
							Breed bl = bL.get(i);
							if(bl.getTitle().equals(newBreedTitle)){
								secondBreed = bl;
							}
						}
					}
					J_B_comDao.addCom(JudgeDao.findJudge(id), secondBreed);
				}
				if(breedRemCheck.isSelected()){
					String remBreedTitle = RemBreedT.getSelectedItem().toString();
					Breed remBreed = null;
					J_B_com remCom = null;
					List<Breed> bL = BreedDao.getBreeds();
					for (int i =0; i<bL.size(); i++){
						Breed bl = bL.get(i);
						if(bl.getTitle().equals(remBreedTitle)){
							remBreed = bl;
						}
					}
					List<J_B_com> JBc = J_B_comDao.getComs();
					for (int i =0; i<JBc.size(); i++){
						J_B_com jbC = JBc.get(i);
						if((jbC.getBreed().getId()==remBreed.getId())&&(jbC.getJudge().getId()==id)){
							remCom = jbC;
						}
					}
					J_B_comDao.deleteCom(remCom.getId());
				}
				// if(!breedCheck.isSelected()){
				// 	secondBreed = firstBreed;
				// }	
				JudgeDao.editJudge(judgeName, id);
				List<Judge> tJ=JudgeDao.getJudges();
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				
				for (int i =0; i<tJ.size();i++) {
					Judge tj = tJ.get(i);
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tj.getId(), tj.getName()});
				}
				aA.dispose();
				
				}						
				else{
					JOptionPane.showMessageDialog(aA, "Имя владельца содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом, в фамилии возможен один дефис. Возможно добавление числового индекса через пробел от фамилии.");
				}
			}});
		
		aA.add(JudgeNameT);
		aA.add(JudgeNameL);
		
		aA.add(BreedT);
		aA.add(RemBreedT);
		
		aA.add(JudgeNameT);
		aA.add(JudgeNameL);

		aA.add(AddBreedL);
		aA.add(RemBreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	
	public static boolean isNumericID(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	  }
	public static void main(){
		new editDogGUI().show(null, 0);
	}
}
