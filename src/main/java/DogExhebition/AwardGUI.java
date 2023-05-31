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


public class AwardGUI {
	
	private JButton export;
	private JToolBar toolBar;
	private JComboBox<String> award_text;
	private JButton award_ser;
	private JTextField name_text;
	private JButton name_ser;
	private JButton returnb;
	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";
	
	private JLabel title_label;
	
	public void show(){

        final ArrayList<Award> AwardList = new ArrayList<>();
        
        final ArrayList<Dog> DogList = new ArrayList<>();
     
        final ArrayList<String> AwAr = new ArrayList<>();

		List<Dog> tD = null;
        tD=DogDao.getDog();
        for (int i =0; i<tD.size(); i++){
            Dog jB = tD.get(i);
            System.out.println(jB.getId() + " " + jB.getName()+" "+jB.getBreed().getTitle());
        }
		for (int i=0; i<tD.size(); i++){
			DogList.add(tD.get(i));
		}

		List<Award> tA = null;
        tA=AwardDao.getAwards();
        for (int i =0; i<tA.size(); i++){
            Award jB = tA.get(i);
            System.out.println(jB.getId() + " " + jB.getTitle());
        }
		for (int i=0; i<tA.size(); i++){
			AwardList.add(tA.get(i));
			AwAr.add(tA.get(i).getTitle());
		}



		final JFrame a = new JFrame("Призы");
		a.setIconImage(new ImageIcon("src/main/java/award.png").getImage());
		toolBar = new JToolBar("instruments");
		toolBar.setBounds(0, 0, 175, 75);
		
		title_label = new JLabel("Призы");
		title_label.setFont(new Font("Arial", Font.PLAIN, 60));
		title_label.setBounds(600,20,500,65);
		a.add(title_label);
				
		returnb = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//return.png"));
	
		export = new JButton(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//export.png"));
		
		toolBar.add(export);
		toolBar.add(returnb);

		export.setToolTipText("Создать PDF-отчёт");
		returnb.setToolTipText("Обновить таблицу");
		
		Object[][] array = new String[][] {};
        Object[] columns = new String[] {"id", "Кличка собаки", "Награда"};
        
        
		
        
        final DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table1 = new JTable(tableModel);
        table1.setBounds(0, 120, 1000, 300);
        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }
        for (int i =0; i<array.length; i++) {
        	 tableModel.insertRow(0, array[i]);
        }

		
        table1.getColumn("id").setPreferredWidth(30);
        table1.getColumn("Награда").setPreferredWidth(400);
        table1.getColumn("Кличка собаки").setPreferredWidth(270);
        
        ((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
		Dog DgAr[] = DogList.toArray(new Dog[0]);
		for (int i =0; i<DgAr.length;i++) {
			
			if (!DgAr[i].getAward().getTitle().contains("Нет")) {
				tableModel.insertRow(0, new Object[]{DgAr[i].getId(), DgAr[i].getName(), DgAr[i].getAward().getTitle()});
			}
		}
				
        award_text = new JComboBox<String>(AwAr.toArray(new String[0]));
        award_text.setBounds(350,480,280,30);
		
        award_ser = new JButton("найти");
        award_ser.setBounds(650,480,100,30);
        
        name_text = new JTextField("введите кличку собаки");
        name_text.setBounds(350,520,280,30);
		
        name_ser = new JButton("найти");
        name_ser.setBounds(650,520,100,30);

		
		a.add(award_text);
		a.add(award_ser);
		
		a.add(name_text);
		a.add(name_ser);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table1);
		
		scrollPane.setBounds(100, 120, 800, 300);
		
		a.add(scrollPane);
		
		returnb.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				List<Dog> tD = null;
				tD=DogDao.getDog();

				((DefaultTableModel) table1.getModel()).getDataVector().removeAllElements();
			
				for (int i =0; i<tD.size();i++) {
					Dog td = tD.get(i);
					if (!td.getAward().getTitle().contains("Нет")) {
						tableModel.insertRow(0, new Object[]{td.getId(), td.getName(), td.getAward().getTitle()});
					}
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
			
						fileChooser.setSelectedFile(new File("award.pdf"));
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
							String[] headersPdfExport = {"\nID\n\n","\nКличка собаки" , "\nНаграда"};
							String para_1 = "Награждения"; 
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
							float[] columnWidths = new float[] {0.2f, 0.4f, 0.8f};
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
		award_ser.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DogAr[] = DogList.toArray(new Dog[0]);
				tableModel.setRowCount(0);
				for (int i =0; i<DogAr.length; i++) {
					if (DogAr[i].getAward().getTitle().contains(award_text.getSelectedItem().toString())){
						if (!DogAr[i].getAward().getTitle().contains("Нет")) {
							tableModel.insertRow(0, new Object[]{DogAr[i].getId(), DogAr[i].getName(), DogAr[i].getAward().getTitle()});
						}
					}
				}
			}});
		name_ser.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				Dog DogAr[] = DogList.toArray(new Dog[0]);
				tableModel.getDataVector().removeAllElements();
				System.out.print(name_text.getText());
				for (int i =0; i<DogAr.length; i++) {
					if (DogAr[i].getName().contains(name_text.getText())) {
						if (!DogAr[i].getAward().getTitle().contains("Нет")) {
							tableModel.insertRow(0, new Object[]{DogAr[i].getId(), DogAr[i].getName(), DogAr[i].getAward().getTitle()});
						}
					}
				}
			}});
		a.add(toolBar);
		a.setSize(1000,600);
		a.setLayout(null);
		a.setVisible(true);
	}
	public Award findByID(Award awardlist[], int id) {
		Award res = null;
		for (int i =0; i<awardlist.length; i++) {
			if (awardlist[i].getId()==id) {
				res = awardlist[i];
			}
		}
		return res;
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
	public static void main(String[] args) {
		new AwardGUI().show();
	}

}
