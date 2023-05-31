package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;

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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class BreedGUI {
	protected static final JOptionPane JOptionPanel = null;
	private JButton export;
	private JButton add;
	private JButton delete;
	private JButton returnb;
	private JToolBar toolBar;
	private JButton w_aw;
	private JComboBox<String> breed_text;
	private JButton breed_ser;
	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";
	
	private int index;
	
	private JTextField name_text;
	private JButton name_ser;
	private JLabel title_label;
	
	private boolean edit_param;
	
	public void show(){
		final JFrame a = new JFrame("Породы");
            
		final ArrayList<Breed> BreedList = new ArrayList<>();
        final ArrayList<Award> AwardList = new ArrayList<>();
        
        final ArrayList<Judge> JudgeList = new ArrayList<>();
        final ArrayList<Dog> DogList = new ArrayList<>();
        final ArrayList<Owner> OwnerList = new ArrayList<>();
     
        
        final ArrayList<String> BrAr = new ArrayList<>();
        final ArrayList<String> AwAr = new ArrayList<>();

		List<Award> tA = null;
        tA=AwardDao.getAwards();
        for (int i =0; i<tA.size(); i++){
            Award jB = tA.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
			AwAr.add(jB.getTitle());
        }

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
        for (int i =0; i<tO.size(); i++){
            Owner jB = tO.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getDog().getName()+" "+jB.getDog().getBreed().getTitle());
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
		for (int i=0; i<tA.size(); i++){
			AwardList.add(tA.get(i));
		}
		for (int i=0; i<tD.size(); i++){
			DogList.add(tD.get(i));
		}
		for (int i=0; i<tJ.size(); i++){
			JudgeList.add(tJ.get(i));
		}
		for (int i=0; i<tO.size(); i++){
			OwnerList.add(tO.get(i));
		}
        
            System.out.print(BreedList.get(0).getTitle());
        
		a.setIconImage(new ImageIcon("src/main/java/breed.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 325, 75);
		
		title_label = new JLabel("Породы");
		title_label.setFont(new Font("Arial", Font.PLAIN, 60));
		title_label.setBounds(600,20,500,60);
		a.add(title_label);
		
		add = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add5.png"));

		delete = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//delete5.png"));
		
		returnb = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//return.png"));
		
		export = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//export.png"));
		
		toolBar.add(add);
		toolBar.add(delete);
		toolBar.add(export);
		toolBar.add(returnb);
		
		delete.setToolTipText("Удалить");
		add.setToolTipText("Добавить");
		export.setToolTipText("Экспортировать в файл");
		
		
		
        Object[] columns = new String[] {"id", "Наименование"};
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
        table1.getColumn("Наименование").setPreferredWidth(300);
        
        ((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
        Breed BrAr1[] = BreedList.toArray(new Breed[0]);
		for (int i =0; i<BrAr1.length;i++) {
			((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{BrAr1[i].getId(), BrAr1[i].getTitle()});
		}        
		
		w_aw = new JButton("с наградами");
		w_aw.setBounds(440, 520, 120, 30);
		w_aw.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DogAr[] = DogList.toArray(new Dog[0]);
				tableModel.getDataVector().removeAllElements();
				System.out.print(name_text.getText());
				for (int i =0; i<DogAr.length; i++) {
					if (DogAr[i].getAward().getId()!=450) {
						tableModel.insertRow(0, new Object[]{DogAr[i].getId(), DogAr[i].getName(), DogAr[i].getBreed().getTitle() , "+"});
					}
				}
			}});
		
		String breeds[] = BrAr.toArray(new String[0]);
		breed_text = new JComboBox<String>(breeds);
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
				new NewBreedJudgeGUI().show(table1);
			}});
		//##############################################################
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				int tabelIndex = 0;
				int tabelIndexArr[] = table1.getSelectedRows();
				for (int k =0; k<tabelIndexArr.length; k++){
					tabelIndex = tabelIndexArr[k];
					index = Integer.parseInt(table1.getValueAt(tabelIndex, 0).toString());
					Breed delBreed = BreedDao.findBreed(index);
					System.out.println("delBreed = "+delBreed.getId());
							
					Judge delJudge = null;
					for (int i =0; i<JudgeList.size(); i++){
						if(JudgeList.get(i).getBreed().getId()==delBreed.getId()){
							delJudge = JudgeList.get(i);
						}
					}
					System.out.println(delJudge.getName());
							
					List<Owner> tO = null;
					tO=OwnerDao.getOwners();
							
					for (int i =0; i<tO.size(); i++){
						Owner to = tO.get(i);
						if (to.getDog().getBreed().getId()==delBreed.getId()){
							DogDao.deleteDog(to.getDog().getId());
							OwnerDao.deleteOwner(to.getId());
						}
					}
					JudgeDao.deleteJudge(delJudge.getId());
					BreedDao.deleteBreed(delBreed.getId());

					List<Judge> jD = JudgeDao.getJudges();
					JudgeList.clear();
					for (int i=0; i<jD.size(); i++){
						JudgeList.add(jD.get(i));
					}

					List<Breed> bD = BreedDao.getBreeds();
					BreedList.clear();
					for (int i =0; i<bD.size(); i++){
						BreedList.add(bD.get(i));
					}

							
				}

				List<Breed> tB=BreedDao.getBreeds();
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				for (int i =0; i<tB.size(); i++){
					Breed tb = tB.get(i);
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tb.getId(), tb.getTitle()});
				}

			
			}});
		//##############################################################
		
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
			
						fileChooser.setSelectedFile(new File("breeds.pdf"));
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
							String[] headersPdfExport = {"\nID\n\n","\nНазвание породы"};
			
							Paragraph p = new Paragraph("Породы",font3);
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
							float[] columnWidths = new float[] {0.3f, 0.75f};
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
		returnb.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				List<Breed> tB=BreedDao.getBreeds();
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				
				for (int i =0; i<tB.size();i++) {
					Breed tb = tB.get(i);
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tb.getId(), tb.getTitle()});
				}
			}});
	}
	
	public int findByID_Owner(Owner OwArr[], int id) {
		int res = -1;
		for (int i =0; i<OwArr.length; i++) {
			if (OwArr[i].getId() == id) {
				res = i;
			}
		}
		return res;
	}
	public int findByID_Dog(Owner DgAr[], int id) {
		int res = -1;
		for (int i =0; i<DgAr.length; i++) {
			if (DgAr[i].getId() == id) {
				res = i;
			}
		}
		return res;
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

	public Judge findJudgeByBreedID_Judge(Judge JdArr[], int id) {
		Judge res = null;
		for (int i =0; i<JdArr.length; i++) {
			if (JdArr[i].getBreed().getId() == id) {
				res = JdArr[i];
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
	
	public int findByID_num(Breed breedArr[], int id) {
		int res = -1;
		for (int i =0; i<breedArr.length; i++) {
			if (breedArr[i].getId() == id) {
				res = i;
			}
		}
		return res;
	}
	
	public int findOwnerByDogID(Owner OwAr[], int dogID) {
		int res = -1;
		for (int i =0; i<OwAr.length; i++) {
			if (OwAr[i].getDog().getId()==dogID) {
				res = i;
			}
		}
		return res;
	}
	
	public Dog findByID_Dog(Dog doglist[], int id) {
		Dog res = null;
		for (int i =0; i<doglist.length; i++) {
			if (doglist[i].getId()==id) {
				res = doglist[i];
			}
		}
		return res;
	}
	
	public int findDogByBreedID(Dog DgAr[], int breedID) {
		int res = -1;
		for (int i =0; i<DgAr.length; i++) {
			if (DgAr[i].getBreed().getId()==breedID) {
				res = i;
			}
		}
		return res;
	}
	
	public Dog findDogByBreedID_Dog(Dog DgAr[], int breedID) {
		Dog res = null;
		for (int i =0; i<DgAr.length; i++) {
			if (DgAr[i].getBreed().getId()==breedID) {
				res = DgAr[i];
			}
		}
		return res;
	}
	
	public int findJudgeByBreedID(Judge JgAr[], int breedID) {
		int res = -1;
		for (int i =0; i<JgAr.length; i++) {
			if (JgAr[i].getBreed().getId()==breedID) {
				res = i;
			}
		}
		return res;
	}
	
	public Breed findByID_Dog(Breed breedlist[], int id) {
		Breed res = null;
		for (int i =0; i<breedlist.length; i++) {
			if (breedlist[i].getId()==id) {
				res = breedlist[i];
			}
		}
		return res;
	}
	public static boolean isNumericID(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	  }
	
	

}
