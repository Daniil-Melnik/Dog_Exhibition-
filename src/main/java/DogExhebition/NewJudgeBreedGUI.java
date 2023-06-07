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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

public class NewJudgeBreedGUI {
    private JFrame aA;
	private JButton apply;
	private JTextField JudgeNameT;
	private JTextField BreedTitleT;
	private JComboBox<String> BreedExistT;
	private JCheckBox breedCheck;
	private JLabel JudgeNameL;
	private JLabel BreedL;
	private JLabel titleJudge;

	public void show (final JTable table1)
	{
		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		String regex_breed_title = "^[А-Я]{1}[а-я]+( {1}[а-я]+)?(\\-{1}[А-Я]{1}[а-я]+)?$";

		Pattern pattern_dog_name = Pattern.compile(regex_breed_title);
        Pattern pattern_person_name = Pattern.compile(regex_person_name);
		final ArrayList<Breed> BreedList = new ArrayList<>();
        final ArrayList<Judge> JudgeList = new ArrayList<>();

		List<Breed> tB = null;
		tB=BreedDao.getBreeds();
        for (int i =0; i<tB.size(); i++){
            Breed jB = tB.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
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
		aA.setTitle("Добавить судью");
		aA.setSize(500, 280);
		aA.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add5.png").getImage());
		
		final ArrayList<String> BrAr = new ArrayList<>();
		Breed helpBreed [] = BreedList.toArray(new Breed[0]);
		
		for (int i =0; i<helpBreed.length; i++) {
			BrAr.add(helpBreed[i].getTitle());
		}
		
		titleJudge = new JLabel("Добавить судью");
		titleJudge.setBounds(175, 20, 200, 30);
		titleJudge.setFont(new Font("Arial", Font.PLAIN, 20));

		aA.add(titleJudge);
		
		apply = new JButton("Добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 25));
		apply.setBounds(160, 190, 170, 40);
		
		
		JudgeNameT = new JTextField();
		JudgeNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		JudgeNameT.setBounds(150,70,300,30);
		
		JudgeNameL = new JLabel("Имя судьи: ");
		JudgeNameL.setBounds(30,70,150,30);
		JudgeNameL.setFont(new Font("Arial", Font.PLAIN, 15)); 
		
		
		//String breeds[] = BrAr.toArray(new String[0]);
		BreedTitleT = new JTextField();
		BreedTitleT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedTitleT.setBounds(150,110,300,30);

		List<Breed> bL = BreedDao.getBreeds();
		ArrayList<String> bAL = new ArrayList<>();
		for (int i=0; i<bL.size(); i++){
			bAL.add(bL.get(i).getTitle());
		}

		BreedExistT = new JComboBox<String>(bAL.toArray(new String[0]));
		BreedExistT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedExistT.setBounds(150,110,300,30);		
		
		BreedL = new JLabel("Порода собак: ");
		BreedL.setBounds(30,110,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedTitleT.setVisible(false);

		breedCheck = new JCheckBox("Новая порода");
		breedCheck.setBounds(200, 140, 150, 25);
		aA.add(breedCheck);
		aA.add(BreedExistT);

		breedCheck.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					BreedExistT.setVisible(false);
					BreedTitleT.setVisible(true);
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					BreedExistT.setVisible(true);
					BreedTitleT.setVisible(false);
				}
			}
		});
		
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{	
				
					String judgeName = JudgeNameT.getText();				
					Matcher matcher = pattern_person_name.matcher(judgeName);
					if(matcher.matches()){
						if(breedCheck.isSelected()){
						String breedName = BreedTitleT.getText();

						Matcher matcher_1 = pattern_dog_name.matcher(breedName);
						if (matcher_1.matches()){

									int breedID = BreedDao.addBreed(breedName);
									int judgeID = JudgeDao.addJudge(judgeName);
									J_B_comDao.addCom(JudgeDao.findJudge(judgeID), BreedDao.findBreed(breedID));
									List<Judge> jL = JudgeDao.getJudges();
									((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
									for (int i =0; i<jL.size(); i++){
										Judge jl = jL.get(i);
										((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{jl.getId(), jl.getName()});
									}
									aA.dispose();
						}
						else{
							JOptionPane.showMessageDialog(aA, "Название породы начинается с одной заглавной буквы, может содержать два слова с заглавной буквы разделённые дефисом без пробелов.");
						}
					}						
					if(!breedCheck.isSelected()){
						String breedTitle = BreedExistT.getSelectedItem().toString();
						Breed edBreed= null;
						List<Breed> bL = BreedDao.getBreeds();
						for (int i =0; i<bL.size(); i++){
							Breed bl = bL.get(i);
							if(bl.getTitle().equals(breedTitle)){
								edBreed = bl;
							}
						}
						
						int judgeID = JudgeDao.addJudge(judgeName);
						J_B_comDao.addCom(JudgeDao.findJudge(judgeID), edBreed);

						List<Judge> jL = JudgeDao.getJudges();
						((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
						for (int i =0; i<jL.size(); i++){
							Judge jl = jL.get(i);
							((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{jl.getId(), jl.getName()});
						}
						aA.dispose();
					}
				}
				
				

				
		        
		}}); 
		
		aA.add(JudgeNameT);
		aA.add(JudgeNameL);
		
		aA.add(BreedTitleT);
		aA.add(BreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	
	public static void main(String[] args) {
		new NewJudgeBreedGUI().show(null);
	}
}
