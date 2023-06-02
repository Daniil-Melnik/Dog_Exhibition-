package DogExhebition;

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

public class OwnerGUI {

	protected static final JOptionPane JOptionPanel = null;
	private JButton edit;
	private JButton export;
	private JButton add;
	private JButton delete;
	private JButton info;
	private JToolBar toolBar;
	private JTextField owner_text;
	private JButton owner_ser;
	
	private JButton returnb;
	
	private JLabel title_label;
	
	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";

	public void show(){

		String regex_person_name = "^[А-Я]{1}[а-я]*( ){1}[А-Я]{1}[а-я]*(\\-[А-Я]{1}[а-я]*)?( {1}[0-9]+)?$";
		Pattern pattern_person_name = Pattern.compile(regex_person_name);

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
		
		final JFrame a = new JFrame("Владельцы собак");
		a.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//owner.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 475, 75);
		
		title_label = new JLabel("Владельцы собак");
		title_label.setFont(new Font("Arial", Font.PLAIN, 50));
		title_label.setBounds(490,20,500,65);
		a.add(title_label);
		
		final ArrayList<String> BrAr = new ArrayList<>();
		
		List<Breed> helpBreed = BreedDao.getBreeds();
		for (int i =0; i<helpBreed.size(); i++) {
			BrAr.add(helpBreed.get(i).getTitle());
		}
		
		add = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//add10.png"));

		delete = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//delete5.png"));
		
		returnb = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//return.png"));
		
		export = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//export.png"));

		edit = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//edit.png"));

		info = new JButton(new ImageIcon("C://Users//danii//OneDrive//Документы//GitHub//Dog_Exhibition-//images//clipboard.png"));

		toolBar.add(add);
		toolBar.add(edit);
		toolBar.add(delete);
		toolBar.add(export);
		toolBar.add(info);
		toolBar.add(returnb);
		
		delete.setToolTipText("Удалить");
		add.setToolTipText("Добавить");
		edit.setToolTipText("Изменить");
		export.setToolTipText("Создать PDF-отчёт");
		info.setToolTipText("Информация о владельце");
		returnb.setToolTipText("Обновить таблицу");

        Object[] columns = new String[] {"id", "Имя владельца"};
        
        
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

		List<Owner> dO = OwnerDao.getOwners();
		for (int i =0; i<dO.size();i++) {
			Owner OwAr = dO.get(i);
			tableModel.insertRow(0, new Object[]{OwAr.getId(), OwAr.getName()});
		}

        add.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new NewOwnerDogGUI();
				NewOwnerDogGUI.show(table1);
			}});
        table1.setBounds(0, 120, 1000, 300);
		
        table1.getColumn("id").setPreferredWidth(30);
        table1.getColumn("Имя владельца").setPreferredWidth(600);
        
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
					((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{to.getId(), to.getName()});
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
					new editOwnerGUI().show(table1, editOw.getName(), editOw);
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

					List<Dog> dL= DogDao.getDog();
					tableModel.setNumRows(0);
					System.out.print(owner_text.getText());
					for (int i =0; i<dL.size(); i++) {
						Dog OwnerAr = dL.get(i);
						if (OwnerAr.getName().contains(owner_text.getText())) {
							tableModel.insertRow(0, new Object[]{OwnerAr.getOwner().getId(), OwnerAr.getOwner().getName(), OwnerAr.getBreed().getTitle()});
						}
					}
				}						
				else{
					JOptionPane.showMessageDialog(a, "Имя владельца на русском языке содержит имя и фамилию с заглавных букв разделённые ОДНИМ пробелом.\nВ фамилии возможен один дефис.\nВозможно добавление числового индекса через пробел от фамилии.");
				}
				
			}});
		
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
					int tabelIndexArr[] = table1.getSelectedRows();
					if(JOptionPane.showConfirmDialog(a, "Вы точно хотите удалить из базы этих владельцев?\nДействие необратимо и повлечёт удаление из базы их собак.")==0){
						for (int k=0; k<tabelIndexArr.length; k++){
							int tabelIndex = tabelIndexArr[k];
							int index = Integer.parseInt(table1.getValueAt(tabelIndex, 0).toString());
						
							Owner delOwner = OwnerDao.findOwner(index);
							List<Dog> dL = DogDao.getDog();
							for (int i =0; i<dL.size(); i++){
								Dog dl = dL.get(i);
								if (dl.getOwner().getId()==delOwner.getId()){
									DogDao.deleteDog(dl.getId());
								}
							}							
							OwnerDao.deleteOwner(delOwner.getId());
						}
	
						List<Owner> tO = null;
						tO=OwnerDao.getOwners();
								
						((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
								
						for (int i =0; i<tO.size(); i++) {
							Owner tOd = tO.get(i);
							((DefaultTableModel) table1.getModel()).insertRow(0, new Object[]{tOd.getId(), tOd.getName()});
						}
					}					
			}});
			info.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent event)
				{
					int selectedIndexes[]=table1.getSelectedRows();
					if(selectedIndexes.length==1){
						int tabelIndex = table1.getSelectedRow();
						int index = Integer.parseInt(table1.getValueAt(tabelIndex, 0).toString());
						Owner infOwner = OwnerDao.findOwner(index);
						new ownerInfo().show(infOwner);
					}
					else{
						JOptionPane.showMessageDialog(a, "Выберите одну строку");
					}
				}});
			export.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent event)
				{
					// try 
					// {
					// 	BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
					// 	com.itextpdf.text.Font font1=new com.itextpdf.text.Font(bf,15,Font.PLAIN);
					// 	com.itextpdf.text.Font font2=new com.itextpdf.text.Font(bf,15,Font.BOLD);
					// 	com.itextpdf.text.Font font3=new com.itextpdf.text.Font(bf,20,Font.CENTER_BASELINE);
					// 	JFileChooser fileChooser = new JFileChooser();
			
					// 	fileChooser.setCurrentDirectory(new File("."));
			
					// 	fileChooser.setSelectedFile(new File("owners.pdf"));
					// 	int result = fileChooser.showSaveDialog(null);
					// 	if (result == JFileChooser.APPROVE_OPTION) 
					// 	{
					// 		File selectedFile = fileChooser.getSelectedFile();
					// 		String fileName = selectedFile.getAbsolutePath();
			
					// 		if (!fileName.endsWith(".pdf")) 
					// 		{
					// 			fileName += ".pdf";
					// 		}
					// 		Document document = new Document();
					// 		PdfWriter.getInstance(document, new FileOutputStream(fileName));
					// 		document.open();
					// 		PdfPTable pdfTable = new PdfPTable(tableModel.getColumnCount());
					// 		//JOptionPane.showMessageDialog (a, tableModel.getColumnCount());
							
							
			
					// 		//Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
					// 		com.itextpdf.text.Font headerFont = font2;
					// 		String[] headersPdfExport = {"\nID\n\n","\nИмя владельца" , "\nКличка", "\nПорода"};
							

					// 		String para_1 = "Владельцы собак"; 
					// 		Paragraph para = new Paragraph (para_1,font3);
					// 		para.setAlignment(Element.ALIGN_CENTER);
					// 		document.add(para); 
					// 		Paragraph p = new Paragraph(" ",font3);
					// 		p.setAlignment(0);
					// 		document.add(p);
							
					// 		for (int i = 0; i < tableModel.getColumnCount(); i++) 
					// 		{
					// 			PdfPCell header = new PdfPCell(new Phrase(headersPdfExport[i], headerFont));
					// 			header.setBackgroundColor(BaseColor.GREEN);
					// 			header.setBorderWidth(2);
					// 			header.setHorizontalAlignment(Element.ALIGN_CENTER);
					// 			pdfTable.addCell(header);
					// 		}
							
					// 		// Create font for table data
					// 		com.itextpdf.text.Font dataFont = font1;
							
					// 		// Set custom widths for each row 
					// 		float[] columnWidths = new float[] {0.2f, 0.75f, 0.5f, 0.35f};
					// 		pdfTable.setWidths(columnWidths);
							
					// 		// Add table data
					// 		for (int i = 0; i < tableModel.getRowCount(); i++) 
					// 		{
					// 			for (int j = 0; j < tableModel.getColumnCount(); j++) 
					// 			{
					// 				PdfPCell data = new PdfPCell(new Phrase(tableModel.getValueAt(i, j).toString(), dataFont));
					// 				if (i % 2 == 1)
					// 				{
					// 					data.setBackgroundColor(BaseColor.WHITE);
					// 				}
					// 				else
					// 				{
					// 					data.setBackgroundColor(BaseColor.WHITE);
					// 				}
					// 				data.setBorderWidth(1);
					// 				data.setHorizontalAlignment(Element.ALIGN_LEFT);
					// 				pdfTable.addCell(data);
					// 			}
					// 		}
					// 		document.add(pdfTable);
					// 		document.close();
					// 		JOptionPane.showMessageDialog(null, "Exported table data to " + fileName);
					// 	}
					// }
					// catch (Exception ex)
					// {
					// 	ex.printStackTrace();
					// 	JOptionPane.showMessageDialog(null, "Error exporting table data to PDF");
					// }
				}});
		
		a.add(toolBar);
		a.setSize(1000,600);
		a.setLayout(null);
		a.setVisible(true);
	}
	

	public static void main(String[] args) {
		new OwnerGUI().show();
	}

}
