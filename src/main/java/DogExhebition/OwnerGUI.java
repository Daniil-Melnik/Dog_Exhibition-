package DogExhebition;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;

import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class OwnerGUI {

	protected static final JOptionPane JOptionPanel = null;
	private JButton edit;
	private JButton export;
	private JButton add;
	private JButton delete;
	private JToolBar toolBar;
	private JTextField owner_text;
	private JButton owner_ser;
	
	private JButton returnb;
	
	private JLabel title_label;
	
	private int index;
	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";

	public void show(){

		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		Pattern pattern_person_name = Pattern.compile(regex_person_name);
		
		final ArrayList<Breed> BreedList = new ArrayList<>();
        final ArrayList<Dog> DogList = new ArrayList<>();
        final ArrayList<Dog> PoorDogList = new ArrayList<>();
        final ArrayList<Owner> OwnerList = new ArrayList<>();



		List<Breed> tB = null;
		tB=BreedDao.getBreeds();
        for (int i =0; i<tB.size(); i++){
            Breed jB = tB.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
        }

        List<Dog> tD = null;
        tD=DogDao.getDog();
        for (int i =0; i<tD.size(); i++){
            Dog jB = tD.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getBreed().getTitle());
        }

        List<Owner> tO = null;
        tO=OwnerDao.getOwners();
        for (int i =0; i<tO.size(); i++){
            Owner jB = tO.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getDog().getName()+" "+jB.getDog().getBreed().getTitle());
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
		
		final JFrame a = new JFrame("Владельцы собак");
		a.setIconImage(new ImageIcon("src/main/java/owner.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 400, 75);
		
		title_label = new JLabel("Владельцы собак");
		title_label.setFont(new Font("Arial", Font.PLAIN, 50));
		title_label.setBounds(490,20,500,65);
		a.add(title_label);
		
		final ArrayList<String> BrAr = new ArrayList<>();
		Breed helpBreed [] = BreedList.toArray(new Breed[0]);
		
		for (int i =0; i<helpBreed.length; i++) {
			BrAr.add(helpBreed[i].getTitle());
		}
		
		add = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add10.png"));

		delete = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//delete5.png"));
		
		returnb = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//return.png"));
		
		export = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//export.png"));

		edit = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//edit.png"));
		toolBar.add(add);
		toolBar.add(edit);
		toolBar.add(delete);
		toolBar.add(export);
		toolBar.add(returnb);
		
		delete.setToolTipText("Удалить");
		add.setToolTipText("Добавить");
		edit.setToolTipText("Изменить");
		export.setToolTipText("Экспортировать в файл");
		
		Object[][] array = new String[][] {};
        Object[] columns = new String[] {"id", "Имя владельца", "Кличка собаки", "Порода собаки"};
        
        
        final DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table1 = new JTable(tableModel);
        
        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }

		Owner OwAr[] = OwnerList.toArray(new Owner[0]);
		for (int i =0; i<OwAr.length;i++) {
			tableModel.insertRow(0, new Object[]{OwAr[i].getId(), OwAr[i].getName(),OwAr[i].getDog().getName() ,OwAr[i].getDog().getBreed().getTitle()});
		}
        add.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new NewOwnerDogGUI().show(table1);
			}});
        table1.setBounds(0, 120, 1000, 300);
		
        table1.getColumn("id").setPreferredWidth(30);
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

		returnb.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				List<Owner> tO=OwnerDao.getOwners();
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				
				for (int i =0; i<tO.size();i++) {
					Owner to = tO.get(i);
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{to.getId(), to.getName(), to.getDog().getName(), to.getDog().getBreed().getTitle()});
				}
				
			}});
		edit.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				if(table1.getSelectedRows().length==1){
					int edOwnerIndex = table1.getSelectedRow();
					int index = Integer.parseInt(table1.getValueAt(edOwnerIndex, 0).toString());

					Owner editOw = OwnerDao.findOwner(index);

					new editOwnerGUI().show(table1, editOw.getName(), editOw.getDog(), editOw);
				}
				else{
					JOptionPane.showMessageDialog(a, "Выберите одну строку");
				}

				
			}});
		owner_ser.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Matcher matcher = pattern_person_name.matcher(owner_text.getText());
				if(matcher.matches()){
					Owner OwnerAr[] = OwnerList.toArray(new Owner[0]);
					tableModel.setNumRows(0);
					System.out.print(owner_text.getText());
					for (int i =0; i<OwnerAr.length; i++) {
						if (OwnerAr[i].getName().contains(owner_text.getText())) {
							tableModel.insertRow(0, new Object[]{OwnerAr[i].getId(), OwnerAr[i].getName(), OwnerAr[i].getDog().getBreed().getTitle()});
						}
					}
				}						
				else{
					JOptionPane.showMessageDialog(a, "Имя владельца содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом, в фамилии возможен один дефис. Возможно добавление числового индекса через пробел от фамилии.");
				}
				
			}});
		
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
					int tabelIndexArr[] = table1.getSelectedRows();

					for (int k=0; k<tabelIndexArr.length; k++){
						int tabelIndex = tabelIndexArr[k];
						int index = Integer.parseInt(table1.getValueAt(tabelIndex, 0).toString());
					
						Owner delOwner = OwnerDao.findOwner(index);
						Dog delDog = DogDao.findDog(delOwner.getDog().getId());
						DogDao.deleteDog(delDog.getId());
						OwnerDao.deleteOwner(delOwner.getId());
					}

					List<Owner> tO = null;
					tO=OwnerDao.getOwners();
							
					((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
							
					for (int i =0; i<tO.size(); i++) {
						Owner tOd = tO.get(i);
						((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tOd.getId(), tOd.getName(), tOd.getDog().getName(), tOd.getDog().getBreed().getTitle()});
					}
			}});
			export.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent event)
				{
					try 
					{
						BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
						com.itextpdf.text.Font font1=new com.itextpdf.text.Font(bf,15,Font.PLAIN);
						com.itextpdf.text.Font font2=new com.itextpdf.text.Font(bf,15,Font.BOLD);
						com.itextpdf.text.Font font3=new com.itextpdf.text.Font(bf,20,Font.CENTER_BASELINE);
						JFileChooser fileChooser = new JFileChooser();
			
						fileChooser.setCurrentDirectory(new File("."));
			
						fileChooser.setSelectedFile(new File("owners.pdf"));
						int result = fileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) 
						{
							File selectedFile = fileChooser.getSelectedFile();
							String fileName = selectedFile.getAbsolutePath();
			
							if (!fileName.endsWith(".pdf")) 
							{
								fileName += ".pdf";
							}
							Document document = new Document();
							PdfWriter.getInstance(document, new FileOutputStream(fileName));
							document.open();
							PdfPTable pdfTable = new PdfPTable(tableModel.getColumnCount());
							//JOptionPane.showMessageDialog (a, tableModel.getColumnCount());
							
							
			
							//Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
							com.itextpdf.text.Font headerFont = font2;
							String[] headersPdfExport = {"\nID\n\n","\nИмя владельца" , "\nКличка", "\nПорода"};
			
							Paragraph p = new Paragraph("Владельцы",font3);
							p.setAlignment(0);
							document.add(p);
							
							for (int i = 0; i < tableModel.getColumnCount(); i++) 
							{
								PdfPCell header = new PdfPCell(new Phrase(headersPdfExport[i], headerFont));
								header.setBackgroundColor(BaseColor.GREEN);
								header.setBorderWidth(2);
								header.setHorizontalAlignment(Element.ALIGN_CENTER);
								pdfTable.addCell(header);
							}
							
							// Create font for table data
							com.itextpdf.text.Font dataFont = font1;
							
							// Set custom widths for each row 
							float[] columnWidths = new float[] {0.3f, 0.75f, 0.3f, 0.45f};
							pdfTable.setWidths(columnWidths);
							
							// Add table data
							for (int i = 0; i < tableModel.getRowCount(); i++) 
							{
								for (int j = 0; j < tableModel.getColumnCount(); j++) 
								{
									PdfPCell data = new PdfPCell(new Phrase(tableModel.getValueAt(i, j).toString(), dataFont));
									if (i % 2 == 1)
									{
										data.setBackgroundColor(BaseColor.WHITE);
									}
									else
									{
										data.setBackgroundColor(BaseColor.WHITE);
									}
									data.setBorderWidth(1);
									data.setHorizontalAlignment(Element.ALIGN_LEFT);
									pdfTable.addCell(data);
								}
							}
							document.add(pdfTable);
							document.close();
							JOptionPane.showMessageDialog(null, "Exported table data to " + fileName);
						}
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error exporting table data to PDF");
					}
				}});
		
		a.add(toolBar);
		a.setSize(1000,600);
		a.setLayout(null);
		a.setVisible(true);
	}
	public Dog findByID(Dog doglist[], int id) {
		Dog res = null;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getId()==id) {
				res = doglist[i];
			}
		}
		return res;
	}
	
	public Owner findOwnerByDogID(Owner ownerlist[], int dogID) {
		Owner res = null;
		for (int i =0; i<ownerlist.length; i++) {
			if (ownerlist[i].getDog().getId()==dogID) {
				res = ownerlist[i];
			}
		}
		return res;
	}
	
	public int findByID_num(Owner OwnerArr[], int id) {
		int res = -1;
		System.out.println("entered id = "+id);
		for (int i =0; i<OwnerArr.length; i++) {
			System.out.println(OwnerArr[i].getId());
			if (OwnerArr[i].getId() == id) {
				res = i;
			}
		}
		return res;
	}

	public static boolean isNumericID(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	  }
	public static void main(String[] args) {
		new OwnerGUI().show();
	}

}
