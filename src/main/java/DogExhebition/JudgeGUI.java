package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileOutputStream;


import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class JudgeGUI {

	protected static final JOptionPane JOptionPanel = null;
	private JButton edit;
	private JButton export;
	private JButton add;
	private JButton delete;
	private JButton returnb;
	private JToolBar toolBar;
	private JTextField judge_text;
	private JButton judge_ser;
	
	private JLabel title_label;
	
	private int index;

	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";
	
	public void show(){

		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		Pattern pattern_person_name = Pattern.compile(regex_person_name);

		final ArrayList<Breed> BreedList = new ArrayList<>();
        
        final ArrayList<Judge> JudgeList = new ArrayList<>();
        final ArrayList<Dog> DogList = new ArrayList<>();
        final ArrayList<Owner> OwnerList = new ArrayList<>();
		
		List<Award> tA = null;
        tA=AwardDao.getAwards();
        for (int i =0; i<tA.size(); i++){
            Award jB = tA.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
        }

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
        // for (int i =0; i<tO.size(); i++){
        //     Owner jB = tO.get(i);
        //     System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getDog().getName()+" "+jB.getDog().getBreed().getTitle());
        // }

        List<Judge> tJ = null;
        tJ=JudgeDao.getJudges();
        for (int i =0; i<tJ.size(); i++){
            Judge jB = tJ.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getBreed().getTitle());
        }

		for (int i=0; i<tB.size(); i++){
			BreedList.add(tB.get(i));
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

		final JFrame a = new JFrame("Судьи");
		final ArrayList<String> BrAr = new ArrayList<>();

		Breed helpBreed [] = BreedList.toArray(new Breed[0]);
		System.out.println("length of arr 3 = "+DogList.toArray().length);
		
		for (int i =0; i<helpBreed.length; i++) {
			BrAr.add(helpBreed[i].getTitle());
		}
		a.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//judge.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 400, 75);
		
		title_label = new JLabel("Судьи");
		title_label.setFont(new Font("Arial", Font.PLAIN, 60));
		title_label.setBounds(600,20,500,65);
		a.add(title_label);
		
		add = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add5.png"));

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
		export.setToolTipText("Создать PDF-отчёт");
		returnb.setToolTipText("Обновить таблицу");
		
		Object[][] array = new String[][] {};
        Object[] columns = new String[] {"id", "Имя судьи", "Обслуживаемые породы"};
       
        
        final DefaultTableModel tableModel = new DefaultTableModel();
		final JTable table1 = new JTable(tableModel){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                    return false;               
            };
        };

        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }
        for (int i =0; i<array.length; i++) {
        	 tableModel.insertRow(0, array[i]);
        }
        
        table1.setBounds(0, 120, 1000, 300);
        
        table1.getColumn("id").setPreferredWidth(30);
        table1.getColumn("Обслуживаемые породы").setPreferredWidth(400);
        table1.getColumn("Имя судьи").setPreferredWidth(270);
        
        ((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
		Judge JgAr[] = JudgeList.toArray(new Judge[0]);
		for (int i =0; i<JgAr.length;i++) {
			
			((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{JgAr[i].getId(), JgAr[i].getName(), JgAr[i].getBreed().getTitle()});
		}
        
        add.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new NewJudgeBreedGUI().show(table1);
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
		
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				int tabelIndex = 0;
				int tabelIndexArr[] = table1.getSelectedRows();
				if((tabelIndexArr.length>=1)&&(tabelIndexArr.length<=5)){
					if(JOptionPane.showConfirmDialog(a, "Вы точно хотите удалить из базы этих судей?\nДействие необратимо и повлечёт удаление:\n1) Обслуживаемых судьями пород\n2) Собак этих породы\n3) Владельцев этих собак")==0){
						for (int k = 0; k<tabelIndexArr.length; k++){
							tabelIndex = tabelIndexArr[k];
							index = Integer.parseInt(table1.getValueAt(tabelIndex, 0).toString());
		
							Judge delJudge = JudgeDao.findJudge(index);									
							Breed delBreed = delJudge.getBreed();

							int k1 =0;
							List<Judge> jL = JudgeDao.getJudges();
							for (int i =0; i<jL.size(); i++){
								Judge jl = jL.get(i);
								if(jl.getBreed().getId()==delBreed.getId()){
									k1++;
								}
							}
							if(k1==1){
								List<Dog> dL = DogDao.getDog();
								for (int i =0; i<dL.size(); i++){
									Dog dl = dL.get(i);
									if (dl.getBreed().getId()==delBreed.getId()){
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
								BreedDao.deleteBreed(delBreed.getId());
							}
							JudgeDao.deleteJudge(delJudge.getId());

							OwnerList.clear();
							List<Owner> OwL = OwnerDao.getOwners();
							for (int i =0; i<OwL.size(); i++){
								OwnerList.add(OwL.get(i));
							}
		
							BreedList.clear();
							List<Breed> BrL = BreedDao.getBreeds();
							for (int i =0; i<BrL.size(); i++){
								BreedList.add(BrL.get(i));
							}
		
							DogList.clear();
							List<Dog> DgL = DogDao.getDog();
							for (int i =0; i<DgL.size(); i++){
								DogList.add(DgL.get(i));
							}
						}
						List <Judge> tJ=JudgeDao.getJudges();
						((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
									
						for (int i =0; i<tJ.size(); i++) {
							Judge tj = tJ.get(i);
							((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tj.getId(), tj.getName(), tj.getBreed().getTitle()});
						}		
					}
				}
				else{
					JOptionPane.showMessageDialog(a, "Выберите от 1 до 5 строк");
				}				
			}});
		edit.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				if(table1.getSelectedRows().length==1){
					int tabelIndex = table1.getSelectedRow();
					int index = Integer.parseInt(table1.getValueAt(tabelIndex, 0).toString());
					new editRefGUI().show(table1, index);
				}
				else{
					JOptionPane.showMessageDialog(a, "Выберите одну строку");
				}
			}});

		judge_ser.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{

				Matcher matcher = pattern_person_name.matcher(judge_text.getText());
				if(matcher.matches()){
					List<Judge> jL= JudgeDao.getJudges();
					tableModel.setNumRows(0);
					for (int i =0; i<jL.size(); i++) {
						Judge JudgeAr = jL.get(i);
						if (JudgeAr.getName().equals(judge_text.getText())) {
							tableModel.insertRow(0, new Object[]{JudgeAr.getId(), JudgeAr.getName(), JudgeAr.getBreed().getTitle()});
						}
					}
				}						
				else{
					JOptionPane.showMessageDialog(a, "Имя судьи на русском языке содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом.\nВ фамилии возможен один дефис.\nВозможно добавление числового индекса через пробел от фамилии.");
				}

				
			}});
		returnb.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				List<Judge> tJ=JudgeDao.getJudges();
				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
				
				for (int i =0; i<tJ.size();i++) {
					Judge tj = tJ.get(i);
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tj.getId(), tj.getName(), tj.getBreed().getTitle()});
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
			
					fileChooser.setSelectedFile(new File("judges.pdf"));
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
						String[] headersPdfExport = {"\nID\n\n","\nИмя судьи" , "\nОбслуживаемая порода"};
						String para_1 = "Судьи"; 
						Paragraph para = new Paragraph (para_1,font3);
						para.setAlignment(Element.ALIGN_CENTER);
						document.add(para); 
						Paragraph p = new Paragraph(" ",font3);
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
						float[] columnWidths = new float[] {0.2f, 0.80f, 0.55f};
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

	
	
	public static void main(String[] args) {
		new JudgeGUI().show();
	}
}

