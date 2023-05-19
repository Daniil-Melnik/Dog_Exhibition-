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
import javax.swing.table.DefaultTableModel;

public class OwnerGUI {

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
	private JTextField owner_text;
	private JButton owner_ser;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	public void show(){
		JFrame a = new JFrame("Владельцы собак");
		a.setIconImage(new ImageIcon("src/main/java/owner.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 400, 75);
		
		title_label = new JLabel("Владельцы собак");
		title_label.setFont(new Font("Arial", Font.PLAIN, 60));
		title_label.setBounds(420,20,500,65);
		a.add(title_label);
		
		add = new JButton(new ImageIcon("src/main/java/add10.png"));

		delete = new JButton(new ImageIcon("src/main/java/delete5.png"));
		
		download = new JButton(new ImageIcon("src/main/java/download.png"));
	
		export = new JButton(new ImageIcon("src/main/java/export.png"));

		edit = new JButton(new ImageIcon("src/main/java/edit.png"));
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
		
		Object[][] array = new String[][] {{"1", "Григорий Кравцов" , "Шпулька", "Борзая" },
            {"2", "Анна Говорова" , "Фунтик", "Дворовая" },
            {"3", "Никита Торжков" , "Кренделёк", "Такса" }};
        Object[] columns = new String[] {"№", "Имя владельца", "Кличка собаки", "Порода собаки"};
        
        
        DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table1 = new JTable(tableModel);
        
        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }
        for (int i =0; i<array.length; i++) {
        	 tableModel.insertRow(0, array[i]);
        }
        add.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new NewOwnerGUI().show(table1);
			}});
        table1.setBounds(0, 120, 1000, 300);
		
        table1.getColumn("№").setPreferredWidth(30);
        table1.getColumn("Имя владельца").setPreferredWidth(400);
        table1.getColumn("Кличка собаки").setPreferredWidth(270);
        table1.getColumn("Порода собаки").setPreferredWidth(270);
				
        owner_text = new JTextField("введите имя владельца");
		owner_text.setBounds(350,480,280,30);
		
		owner_ser = new JButton("найти");
		owner_ser.setBounds(650,480,100,30);

		
		a.add(owner_text);
		a.add(owner_ser);
		
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
		new OwnerGUI().show();
	}

}
