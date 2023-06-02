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
	private JLabel JudgeNameL;
	private JLabel BreedL;
	private JLabel title;
	private JCheckBox breedCheck;
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
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getBreed().getTitle());
			
        }

		for (int i=0; i<tB.size(); i++){
			BreedList.add(tB.get(i));
		}
		for (int i=0; i<tJ.size(); i++){
			JudgeList.add(tJ.get(i));
		}

		aA = new JFrame("");
		aA.setTitle("Изменить данные");
		aA.setSize(500, 270);
		
		title = new JLabel("Изменить данные");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("Изменить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 185, 120, 40);
		
		
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
		breedNewCheck.setBounds(5, 170, 140, 20);
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


		List<Breed> brL = BreedDao.getBreeds();
		List<String> brStr = new ArrayList<>();
		for (int i=0; i<brL.size(); i++){
			if (brL.get(i).getId()!=edJudge.getBreed().getId()){
				brStr.add(brL.get(i).getTitle());
			}
		}

		BreedT = new JComboBox<String>(brStr.toArray(new String[0]));
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,110,300,30);
		
		BreedL = new JLabel("Порода собак: ");
		BreedL.setBounds(30,110,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));

		breedCheck = new JCheckBox("Сменить породу");

		breedCheck.setBounds(5, 150, 140, 20);
		breedCheck.setFont(new Font("Arial", Font.PLAIN, 15));;
		BreedT.setEnabled(false);
		breedCheck.addItemListener((ItemListener) new ItemListener() {
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

		aA.add(breedCheck);
		
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String judgeName = JudgeNameT.getText();
				
				Matcher matcher = pattern_person_name.matcher(judgeName);
				if(matcher.matches()){

				Breed firstBreed = edJudge.getBreed();

				Breed secondBreed = null;;				
				if(breedCheck.isSelected()){
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
					List<Judge> jL = JudgeDao.getJudges();
						int k =0;
						for (int i =0; i<jL.size(); i++){
							Judge jl = jL.get(i);
							if(jl.getBreed().getId()==firstBreed.getId()){
								k++;
							}
						}
						if(k==1){
							List<Dog> dL = DogDao.getDog();
							for (int i =0; i<dL.size(); i++){
								Dog dl = dL.get(i);
								if (dl.getBreed().getId()==firstBreed.getId()){
									int z = 0;
									Owner delOwner = null;
									for (int j=0; j<dL.size(); j++){
										Dog dl1 = dL.get(j);
										if (dl1.getOwner().getId()==dl.getOwner().getId()){
											z++;
											delOwner = dl1.getOwner();
										}
									}
									if(z==1){
										OwnerDao.deleteOwner(delOwner.getId());
									}
									DogDao.deleteDog(dl.getId());
								}
							}
							BreedDao.deleteBreed(firstBreed.getId());
						}

				}
				if(!breedCheck.isSelected()){
					secondBreed = firstBreed;
				}	
				JudgeDao.editJudge(judgeName, secondBreed, id);
				List<Judge> tJ=JudgeDao.getJudges();
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				
				for (int i =0; i<tJ.size();i++) {
					Judge tj = tJ.get(i);
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tj.getId(), tj.getName(), tj.getBreed().getTitle()});
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
		
		aA.add(JudgeNameT);
		aA.add(JudgeNameL);
		
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
