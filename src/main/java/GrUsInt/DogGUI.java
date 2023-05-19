package GrUsInt;
import java.util.List;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DogGUI {
	protected static final JOptionPane JOptionPanel = null;
	private JPanel head_panel;
	private JButton edit;
	private JButton export;
	private JButton download;
	private JButton add;
	private JButton delete;
	private JButton returnb;
	private JToolBar toolBar;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTable dogs;
	private JPanel filterpanel;
	private JButton w_aw;
	private JComboBox breed_text;
	private JComboBox judgeT;
	private JButton breed_ser;
	private JFrame aA;
	private JButton apply;
	private JTextField DogNameT;
	private JComboBox BreedT;
	private JComboBox AwardsT;
	private JLabel DogNameL;
	private JLabel BreedL;
	private JLabel AwardsL;
	private JLabel title;
	private JLabel judgeL;
	
	private int index;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private JTextField idT;
	private JLabel idL;
	
	private boolean edit_param;
	
	public void show(){
		final JFrame a = new JFrame("Собаки");
		final Object[][] array = new String[][] {{"1", "Тунгуска" , "Борзая", "Александр Кулаков" ,"-" },
            {"2" ,"Корнет" , "Дворовая", "Андрей Головнёв" ,"+" },
            {"3", "Кортик", "Папильон" , "Максим Королёв" , "+" }};
            
            final ArrayList<Breed> BreedList = new ArrayList<>();
            final ArrayList<Dog> DogList = new ArrayList<>();
            final ArrayList<Award> AwardList = new ArrayList<>();
            
            final ArrayList<String> BrAr = new ArrayList<>();
            final ArrayList<String> AwAr = new ArrayList<>();
            
            final org.w3c.dom.Document doc;
			try {

				DocumentBuilder dBuilder =
				DocumentBuilderFactory.newInstance().newDocumentBuilder();

				doc = (org.w3c.dom.Document) dBuilder.parse(new File("C:\\Users\\danii\\eclipse-workspace\\dogex\\dataXML\\AwardFile.xml"));

				((org.w3c.dom.Document) doc).getDocumentElement().normalize();

						NodeList nAward = ((org.w3c.dom.Document) doc).getElementsByTagName("award");

						for (int temp = 0; temp < nAward.getLength(); temp++) {

						Node elem = nAward.item(temp);

						NamedNodeMap attrs = elem.getAttributes();

						String id = attrs.getNamedItem("id").getNodeValue();
						String title = attrs.getNamedItem("title").getNodeValue();
						//model.addRow(new Object[]{id, name, breed , owner, award});
						//tableModel.insertRow(0, new Object[]{id, name, breed , award});
						Award tempAward = new Award();
						tempAward.setAward(title, Integer.parseInt (id));
						AwardList.add(tempAward);
						AwAr.add(title);
						}			
				}
				catch (ParserConfigurationException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}

				catch (SAXException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}
				catch (IOException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}
			org.w3c.dom.Document doc1;
			try {

				DocumentBuilder dBuilder =
				DocumentBuilderFactory.newInstance().newDocumentBuilder();

				doc1 = (org.w3c.dom.Document) dBuilder.parse(new File("C:\\Users\\danii\\eclipse-workspace\\dogex\\dataXML\\BreedFile.xml"));

				((org.w3c.dom.Document) doc1).getDocumentElement().normalize();

						NodeList nBreed = ((org.w3c.dom.Document) doc1).getElementsByTagName("breed");

						for (int temp = 0; temp < nBreed.getLength(); temp++) {

						Node elem = nBreed.item(temp);

						NamedNodeMap attrs = elem.getAttributes();

						String id = attrs.getNamedItem("id").getNodeValue();
						String title = attrs.getNamedItem("title").getNodeValue();
						//model.addRow(new Object[]{id, name, breed , owner, award});
						//tableModel.insertRow(0, new Object[]{id, name, breed , award});
						Breed tempBreed = new Breed();
						tempBreed.setBreed(title, Integer.parseInt (id));
						BreedList.add(tempBreed);
						BrAr.add(title);
						}			
				}
				catch (ParserConfigurationException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}

				catch (SAXException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}
				catch (IOException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}
        
            System.out.print(BreedList.get(0).getTitle());
        
		a.setIconImage(new ImageIcon("src/main/java/dog_main1.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 475, 75);
		
		title_label = new JLabel("Собаки");
		title_label.setFont(new Font("Arial", Font.PLAIN, 60));
		title_label.setBounds(600,20,500,60);
		a.add(title_label);
		
		add = new JButton(new ImageIcon("src/main/java/add5.png"));
		
		returnb = new JButton(new ImageIcon("src/main/java/return.png"));
		
		delete = new JButton(new ImageIcon("src/main/java/delete5.png"));
		
		download = new JButton(new ImageIcon("src/main/java/download.png"));
		
		
		
		export = new JButton(new ImageIcon("src/main/java/export.png"));
		
		edit = new JButton(new ImageIcon("src/main/java/edit.png"));
		
		toolBar.add(download);
		toolBar.add(add);
		toolBar.add(edit);
		toolBar.add(delete);
		toolBar.add(export);
		toolBar.add(returnb);
		
		delete.setToolTipText("Удалить");
		add.setToolTipText("Добавить");
		download.setToolTipText("Загрузить из файла");
		edit.setToolTipText("Изменить");
		export.setToolTipText("Экспортировать в файл");
		
		
		
        Object[] columns = new String[] {"id", "Кличка", "Порода" , "Награды (+/-)"};
        edit_param = false;
        final DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table1 = new JTable(tableModel){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                    return edit_param;               
            };
        };
        
        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }
		
        //final JTable table1 = new JTable(array, columns);
        table1.setBounds(0, 120, 1000, 300);
		
        table1.getColumn("id").setPreferredWidth(30);
        table1.getColumn("Кличка").setPreferredWidth(300);
        table1.getColumn("Порода").setPreferredWidth(150);
        table1.getColumn("Награды (+/-)").setPreferredWidth(100);
        
		
		w_aw = new JButton("с наградами");
		w_aw.setBounds(440, 520, 120, 30);
		w_aw.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DogAr[] = DogList.toArray(new Dog[0]);
				tableModel.getDataVector().removeAllElements();
				String a1;
				System.out.print(name_text.getText());
				for (int i =0; i<DogAr.length; i++) {
					if (DogAr[i].getAward().getId()!=450) {
						tableModel.insertRow(0, new Object[]{DogAr[i].getId(), DogAr[i].getName(), DogAr[i].getBreed().getTitle() , "+"});
					}
				}
			}});
		
		String breeds[] = BrAr.toArray(new String[0]);
		breed_text = new JComboBox(breeds);
		breed_text.setBounds(350,480,280,30);
		
		breed_ser = new JButton("найти");
		breed_ser.setBounds(650,480,100,30);
		breed_ser.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DogAr[] = DogList.toArray(new Dog[0]);
				tableModel.getDataVector().removeAllElements();
				String a1;
				for (int i =0; i<DogAr.length; i++) {
					if (DogAr[i].getBreed().getTitle()==breed_text.getSelectedItem().toString()) {
						if (DogAr[i].getAward().getId()==450) {
							a1 = "-";
						}
						else {
							a1="+";
						}
						tableModel.insertRow(0, new Object[]{DogAr[i].getId(), DogAr[i].getName(), DogAr[i].getBreed().getTitle() , a1});
					}
				}
			}});
		
		name_text = new JTextField("введите кличку");
		name_text.setBounds(350,440,280,30);
		
		name_ser = new JButton("найти");
		name_ser.setBounds(650,440,100,30);
		name_ser.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DogAr[] = DogList.toArray(new Dog[0]);
				tableModel.getDataVector().removeAllElements();
				String a1;
				System.out.print(name_text.getText());
				for (int i =0; i<DogAr.length; i++) {
					if (DogAr[i].getName().contains(name_text.getText())) {
						
						if (DogAr[i].getAward().getId()==450) {
							a1 = "-";
						}
						else {
							a1="+";
						}
						tableModel.insertRow(0, new Object[]{DogAr[i].getId(), DogAr[i].getName(), DogAr[i].getBreed().getTitle() , a1});
					}
				}
			}});
		
		a.add(w_aw);
		a.add(breed_text);
		a.add(breed_ser);
		a.add(name_text);
		a.add(name_ser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table1);
		
		scrollPane.setBounds(100, 120, 800, 300);
		
		a.add(scrollPane);
		
		a.add(toolBar);
		a.setSize(1000,600);
		a.setLayout(null);
		a.setVisible(true);

		
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				aA = new JFrame("");
				aA.setTitle("Добавить собаку");
				aA.setSize(500, 320);
				
				title = new JLabel("добавить собаку");
				title.setBounds(175, 20, 200, 30);
				title.setFont(new Font("Arial", Font.PLAIN, 20));
				aA.add(title);
				
				apply = new JButton("добавить");
				apply.setFont(new Font("Arial", Font.PLAIN, 15));
				apply.setBounds(360, 240, 120, 40);
				
				
				DogNameT = new JTextField();
				DogNameT.setFont(new Font("Arial", Font.PLAIN, 15));
				DogNameT.setBounds(150,70,300,30);
				
				DogNameL = new JLabel("Кличка собаки: ");
				DogNameL.setBounds(30,70,150,30);
				DogNameL.setFont(new Font("Arial", Font.PLAIN, 15));				
				
				String breeds[] = BrAr.toArray(new String[0]);
				BreedT = new JComboBox(breeds);
				BreedT.setFont(new Font("Arial", Font.PLAIN, 15));
				BreedT.setBounds(150,110,300,30);
				
				BreedL = new JLabel("Порода собаки: ");
				BreedL.setBounds(30,110,150,30);
				BreedL.setFont(new Font("Arial", Font.PLAIN, 15));
				
				AwardsT = new JComboBox();
				
				String awards[] = AwAr.toArray(new String[0]);
				AwardsT = new JComboBox(awards);
				AwardsT.setFont(new Font("Arial", Font.PLAIN, 15));
				AwardsT.setBounds(150,185,300,30);
				
				AwardsL = new JLabel("награды: ");
				AwardsL.setBounds(75, 185, 100, 30);
				AwardsL.setFont(new Font("Arial", Font.PLAIN, 15));
				
				idT = new JTextField("");
				idT.setFont(new Font("Arial", Font.PLAIN, 15));
		        idT.setBounds(150,150,50,30);
		        
		        idL = new JLabel("id: ");
		        idL.setBounds(120,150,50,30);
		        idL.setFont(new Font("Arial", Font.PLAIN, 15));
		        
		        aA.add(idT);
		        aA.add(idL);
				
				apply.addActionListener(new ActionListener()
				{
					public void actionPerformed (ActionEvent event)
					{
						String a1 = "-";
						if (findByTitle(AwardList.toArray(new Award[0]), AwardsT.getSelectedItem().toString()).getId()!=450) {
							a1 = "+";
						}
				        DefaultTableModel model = (DefaultTableModel) table1.getModel();
				        model.insertRow(0, new Object[]{idT.getText(), DogNameT.getText(), BreedT.getSelectedItem().toString() , a1});
				        Dog tempDog = new Dog();
				        tempDog.setDog(DogNameT.getText(), findByTitle(BreedList.toArray(new Breed[0]), BreedT.getSelectedItem().toString()), findByTitle(AwardList.toArray(new Award[0]), AwardsT.getSelectedItem().toString()), Integer.parseInt (idT.getText()));
				        DogList.add(tempDog);
					}});
				
				aA.add(DogNameT);
				aA.add(DogNameL);
				
				aA.add(AwardsT);
				aA.add(AwardsL);
				
				aA.add(BreedT);
				aA.add(BreedL);
				
				//aA.add(judgeT);
				//aA.add(judgeL);
				
				aA.add(apply);
	
				aA.setLayout(null);
				aA.setVisible(true);
			}});
		download.addActionListener (new ActionListener()
		{
		public void actionPerformed (ActionEvent event)
		{
			FileDialog open = new FileDialog(a, "-", FileDialog.LOAD);
			open.setFile("*.xml");
			open.setVisible(true); 

			String fileName = open.getDirectory() + open.getFile();
			if(fileName == null) return; 
			org.w3c.dom.Document doc;
			try {

				DocumentBuilder dBuilder =
				DocumentBuilderFactory.newInstance().newDocumentBuilder();

				doc = (org.w3c.dom.Document) dBuilder.parse(new File(fileName));

				((org.w3c.dom.Document) doc).getDocumentElement().normalize();

						NodeList nlDogs = ((org.w3c.dom.Document) doc).getElementsByTagName("dog");

						for (int temp = 0; temp < nlDogs.getLength(); temp++) {

						Node elem = nlDogs.item(temp);

						NamedNodeMap attrs = elem.getAttributes();

						int id =  Integer.parseInt (attrs.getNamedItem("id").getNodeValue());
						String name = attrs.getNamedItem("name").getNodeValue();
						Breed breed = findByID(BreedList.toArray(new Breed[0]), Integer.parseInt (attrs.getNamedItem("breedID").getNodeValue()));
						Award award = findByID(AwardList.toArray(new Award[0]),Integer.parseInt (attrs.getNamedItem("awardID").getNodeValue()));

						//model.addRow(new Object[]{id, name, breed , owner, award});
						if(award.getId()==450) {
							tableModel.insertRow(0, new Object[]{id, name, breed.getTitle() , "-"});
						}
						else {
							tableModel.insertRow(0, new Object[]{id, name, breed.getTitle() , "+"});
						}
						Dog tempDog = new Dog();
						tempDog.setDog(name, breed, award, id);
						DogList.add(tempDog);
						
						}
						JOptionPane.showMessageDialog (a, fileName);
				}
				catch (ParserConfigurationException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}

				catch (SAXException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}
				catch (IOException e) { e.printStackTrace(); JOptionPane.showMessageDialog (a, "-");}
		}});
		
		edit.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new NewDogGUI().show(table1, DogList, BrAr, AwAr, BreedList, AwardList);
			}});
		
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				index = Integer.parseInt(JOptionPanel.showInputDialog(aA, "Удалить id: ")) ;
				DogList.remove(findByID(DogList.toArray(new Dog[0]), index));
				System.out.print(findByID(DogList.toArray(new Dog[0]), index));
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				Dog DgAr[] = DogList.toArray(new Dog[0]);
				for (int i =0; i<DgAr.length; i++) {
					System.out.println(DgAr[i].getId());
					if(DgAr[i].getAward().getId()==450) {
						((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getBreed().getTitle() , "-"});
					}
					else {
						((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getBreed().getTitle() , "+"});
					}
					
				}
			
			}});
		
		returnb.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				Dog DgAr[] = DogList.toArray(new Dog[0]);
				for (int i =0; i<DgAr.length;i++) {
					if(DgAr[i].getAward().getId()==450) {
						((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getBreed().getTitle() , "-"});
					}
					else {
						((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getBreed().getTitle() , "+"});
					}
				}
			}});
		export.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DgAr[] = DogList.toArray(new Dog[0]);
				
				try {
					 DocumentBuilder builder =
					 DocumentBuilderFactory.newInstance().newDocumentBuilder();
					Document doc = builder.newDocument();
				
				Node doglist = doc.createElement("doglist");
				doc.appendChild(doglist);
				for (int i = 0; i < DgAr.length; i++)
				{
				Element dog = doc.createElement("dog");
				doglist.appendChild(dog);
				dog.setAttribute("id", String.format("%d", DgAr[i].getId()));
				dog.setAttribute("name", DgAr[i].getName());
				dog.setAttribute("breedID", String.format("%d", DgAr[i].getBreed().getId()));
				dog.setAttribute("awardID", String.format("%d", DgAr[i].getAward().getId()));
				}
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				java.io.FileWriter fw = new FileWriter("C:\\Users\\danii\\eclipse-workspace\\dogex\\dataXML\\DogFile_out.xml");
				trans.transform(new DOMSource(doc), new StreamResult(fw));
				
				JOptionPane.showMessageDialog (a, "Add");
				}
				catch (TransformerConfigurationException e) { e.printStackTrace(); }
				catch (TransformerException e) { e.printStackTrace(); }
				catch (IOException e) { e.printStackTrace(); } catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
			}});
	}
	public Breed findByID(Breed BreedArr[], int id) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getId() == id) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	public Award findByID(Award AwardArr[], int id) {
		Award res = new Award();
		for (int i =0; i<AwardArr.length; i++) {
			if (AwardArr[i].getId() == id) {
				res = AwardArr[i];
			}
		}
		return res;
	}
	
	public Breed findByTitle(Breed BreedArr[], String title) {
		Breed res = null;
		for (int i =0; i<BreedArr.length; i++) {
			if (BreedArr[i].getTitle() == title) {
				res = BreedArr[i];
			}
		}
		return res;
	}
	public Award findByTitle(Award AwardArr[], String title) {
		Award res = new Award();
		for (int i =0; i<AwardArr.length; i++) {
			if (AwardArr[i].getTitle() == title) {
				res = AwardArr[i];
			}
		}
		return res;
	}
	
	public int findByID(Dog DogArr[], int id) {
		int res = -1;
		System.out.println("entered id = "+id);
		for (int i =0; i<DogArr.length; i++) {
			System.out.println(DogArr[i].getId());
			if (DogArr[i].getId() == id) {
				res = i;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		new DogGUI().show();
	}

}
