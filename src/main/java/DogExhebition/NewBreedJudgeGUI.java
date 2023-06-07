package DogExhebition;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

public class NewBreedJudgeGUI {
    private JFrame aA;
	private JButton apply;
	private JTextField JudgeNameT;
	private JTextField BreedT;
	private JLabel JudgeNameL;
	private JLabel BreedL;
	private JLabel titleJudge;
    private JLabel titleBreed;

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
		aA.setSize(1000, 280);
		aA.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add5.png").getImage());
		
		final ArrayList<String> BrAr = new ArrayList<>();
		Breed helpBreed [] = BreedList.toArray(new Breed[0]);
		
		for (int i =0; i<helpBreed.length; i++) {
			BrAr.add(helpBreed[i].getTitle());
		}
		
		titleJudge = new JLabel("Добавить судью");
		titleJudge.setBounds(650, 20, 200, 30);
		titleJudge.setFont(new Font("Arial", Font.PLAIN, 20));

        titleBreed = new JLabel("Добавить породу");
		titleBreed.setBounds(175, 20, 200, 30);
		titleBreed.setFont(new Font("Arial", Font.PLAIN, 20));

		aA.add(titleJudge);
        aA.add(titleBreed);
		
		apply = new JButton("Добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 25));
		apply.setBounds(400, 180, 200, 50);
		
		
		JudgeNameT = new JTextField();
		JudgeNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		JudgeNameT.setBounds(650,70,300,30);
		
		JudgeNameL = new JLabel("Имя судьи: ");
		JudgeNameL.setBounds(540,70,150,30);
		JudgeNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		//String breeds[] = BrAr.toArray(new String[0]);
		BreedT = new JTextField();
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(175,70,300,30);
		
		BreedL = new JLabel("Порода собак: ");
		BreedL.setBounds(50,70,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{	
				String judgeName = JudgeNameT.getText();
				Matcher matcher = pattern_person_name.matcher(judgeName);
				if(matcher.matches()){
					String breedName = BreedT.getText();
					
					Matcher matcher_1 = pattern_dog_name.matcher(breedName);
					if (matcher_1.matches()){
						List<Judge> jdL = JudgeDao.getJudges();
						List<Breed> brL = BreedDao.getBreeds();

						boolean notJudgeExist = true;
						boolean notBreedExist = true;

						for (int i =0; i<brL.size(); i++){
							if(brL.get(i).getTitle().equals(breedName)){
								notBreedExist = false;
							}
						}

						for (int i =0; i<jdL.size(); i++){
							if(jdL.get(i).getName().equals(judgeName)){
								notJudgeExist = false;
							}
						}			
						
						if(notBreedExist){
							if(notJudgeExist){
								int breedID = BreedDao.addBreed(breedName);
								JudgeDao.addJudge(JudgeNameT.getText());
								BreedList.add(BreedDao.findBreed(breedID));
								((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
								for (int i =0; i<BreedList.size(); i++){
									Breed tB = BreedList.get(i);
									((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tB.getId(), tB.getTitle()});
								}
								aA.dispose();
							}
							else{
								JOptionPane.showMessageDialog(aA, "Имя судьи занято");
							}
						} 
						else{
							JOptionPane.showMessageDialog(aA, "Название породы занято");
						}
					}
					else{
						JOptionPane.showMessageDialog(aA, "Название породы начинается с одной заглавной буквы, может содержать два слова с заглавной буквы разделённые дефисом без пробелов.");
					}
					
				}						
				else{
					JOptionPane.showMessageDialog(aA, "Имя судьи на русском языке содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом.\nВ фамилии возможен один дефис.\nВозможно добавление числового индекса через пробел от фамилии.");
				}				
			}});
		
		aA.add(JudgeNameT);
		aA.add(JudgeNameL);
		
		aA.add(BreedT);
		aA.add(BreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	
	public static void main(String[] args) {
		new NewBreedJudgeGUI().show(null);
	}
}
