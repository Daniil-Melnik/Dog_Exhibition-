package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class NewRefGUI {
	private JFrame aA;
	private JButton apply;
	private JTextField JudgeNameT;
	private JComboBox<String> BreedT;
	private JLabel JudgeNameL;
	private JLabel BreedL;
	private JLabel title;

	public void show (final JTable table1)
	{
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
		aA.setSize(500, 300);
		
		final ArrayList<String> BrAr = new ArrayList<>();
		Breed helpBreed [] = BreedList.toArray(new Breed[0]);
		
		for (int i =0; i<helpBreed.length; i++) {
			BrAr.add(helpBreed[i].getTitle());
		}
		
		title = new JLabel("добавить судью");
		title.setBounds(175, 20, 200, 30);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		aA.add(title);
		
		apply = new JButton("добавить");
		apply.setFont(new Font("Arial", Font.PLAIN, 15));
		apply.setBounds(360, 220, 120, 40);
		
		
		JudgeNameT = new JTextField();
		JudgeNameT.setFont(new Font("Arial", Font.PLAIN, 15));
		JudgeNameT.setBounds(150,70,300,30);
		
		JudgeNameL = new JLabel("Имя судьи: ");
		JudgeNameL.setBounds(30,70,150,30);
		JudgeNameL.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		String breeds[] = BrAr.toArray(new String[0]);
		BreedT = new JComboBox<String>(breeds);
		BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
		BreedT.setBounds(150,110,300,30);
		
		BreedL = new JLabel("Порода собак: ");
		BreedL.setBounds(30,110,150,30);
		BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
		
        apply.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{				
				// Object [] ar = {idT.getText(), JudgeNameT.getText(), BreedT.getSelectedItem().toString()};
		        // DefaultTableModel model = (DefaultTableModel) table1.getModel();
		        // model.addRow(ar);
		        // Judge tempJudge = new Judge();
		        // tempJudge.setJudge(findByTitle(BreedList.toArray(new Breed[0]), BreedT.getSelectedItem().toString()),Integer.parseInt(idT.getText()),JudgeNameT.getText() );
		        // JudgeList.add(tempJudge);
			}});
		
		aA.add(JudgeNameT);
		aA.add(JudgeNameL);
		
		aA.add(BreedT);
		aA.add(BreedL);
		
		aA.add(apply);

		aA.setLayout(null);
		aA.setVisible(true);
	};
	
	public Breed findByTitle(Breed BreedArr[], String title) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getTitle() == title) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	public static void main(String[] args) {
		new NewRefGUI().show(null);
	}
}
