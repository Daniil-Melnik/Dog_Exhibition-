package GrUsInt;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class RefereeGUI {

	private JButton edit;
	private JButton export;
	private JButton download;
	private JButton add;
	private JButton delete;
	private JToolBar toolBar;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTable dogs;
	private JPanel filterpanel;
	private JButton w_aw;
	private JTextField judge_text;
	private JButton judge_ser;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	public void show(){
		JFrame a = new JFrame("Судьи");
		a.setIconImage(new ImageIcon("src/main/java/judge.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 400, 75);
		
		title_label = new JLabel("Судьи");
		title_label.setFont(new Font("Arial", Font.PLAIN, 60));
		title_label.setBounds(600,20,500,65);
		a.add(title_label);
		
		add = new JButton(new ImageIcon("src/main/java/add5.png"));
		
		
		delete = new JButton(new ImageIcon("src/main/java/delete5.png"));
		//delete.setBounds(0, 0, 100, 100);
		
		download = new JButton(new ImageIcon("src/main/java/download.png"));
//		add.setBounds(0, 0, 100, 100);
//		
		export = new JButton(new ImageIcon("src/main/java/export.png"));
//		add.setBounds(0, 0, 100, 100);
//		
		edit = new JButton(new ImageIcon("src/main/java/edit.png"));
//		add.setBounds(0, 0, 100, 100);
		toolBar.add(download);
		toolBar.add(add);
		toolBar.add(edit);
		toolBar.add(delete);
		toolBar.add(export);
		
		delete.setToolTipText("Удалить");
		add.setToolTipText("Добавить");
		download.setToolTipText("Загрузить из файла");
		edit.setToolTipText("Изменить");
		export.setToolTipText("Экспортировать в файл");
		
		Object[][] array = new String[][] {{"1", "Александр Кулаков" , "Борзая" },
            {"2" ,"Андрей Головнёв"  , "Шпиц" },
            {"3", "Максим Королёв" , "Овчарка" }};
        Object[] columns = new String[] {"№", "Имя судьи", "Обслуживаемые породы"};
       
        
        DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table1 = new JTable(tableModel);

        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }
        for (int i =0; i<array.length; i++) {
        	 tableModel.insertRow(0, array[i]);
        }
        
        table1.setBounds(0, 120, 1000, 300);
        
        table1.getColumn("№").setPreferredWidth(30);
        table1.getColumn("Обслуживаемые породы").setPreferredWidth(400);
        table1.getColumn("Имя судьи").setPreferredWidth(270);
        
        add.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new NewRefGUI().show(table1);
			}});
				
		judge_text = new JTextField("введите имя судьи");
		judge_text.setBounds(350,480,280,30);
		
		judge_ser = new JButton("найти");
		judge_ser.setBounds(650,480,100,30);

		
		a.add(judge_text);
		a.add(judge_ser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table1);
		
		scrollPane.setBounds(100, 120, 800, 300);
		
		a.add(scrollPane);
		
		a.add(toolBar);
		a.setSize(1000,600);
		a.setLayout(null);
		a.setVisible(true);
	}
	
	public static void main(String[] args) {
		new RefereeGUI().show();
	}
}

