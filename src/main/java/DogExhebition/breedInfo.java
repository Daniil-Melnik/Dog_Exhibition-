package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class breedInfo {
    protected static final JOptionPane JOptionPanel = null;
	private JFrame aO;
    private JButton ok;
    private JButton export;
	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";

	private JLabel title_label;
	
	private boolean edit_param;
    public void show(Breed breed){
        final DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table1 = new JTable(tableModel){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {                
                    return edit_param;               
            };
        };

        Object[] columns = new String[] {"id судьи", "Имя судьи"};
        
        for (int i=0; i<columns.length; i++) {
        	tableModel.addColumn(columns[i]);
        }

        table1.setBounds(0, 120, 1000, 300);
        table1.getColumn("id судьи").setPreferredWidth(30);
        table1.getColumn("Имя судьи").setPreferredWidth(600);

        aO = new JFrame("Информация о породе");
        aO.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//dog.png").getImage());
		
		title_label = new JLabel("Информация о породе: "+breed.getTitle());
		title_label.setFont(new Font("Arial", Font.PLAIN, 20));
		title_label.setBounds(100,20,500,60);
		aO.add(title_label);
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table1);
		
		scrollPane.setBounds(100, 120, 800, 300);
		
		aO.add(scrollPane);
		aO.setSize(1000,600);
		aO.setLayout(null);

        ok = new JButton("закрыть");
        ok.setBounds(780, 500, 200, 55);
        ok.setFont(new Font("Arial", Font.PLAIN, 20));
        ok.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				aO.dispose();
			}});

        export = new JButton("в PDF");
        export.setBounds(10, 500, 200, 55);
        export.setFont(new Font("Arial", Font.PLAIN, 20));;

        aO.add(ok);
        aO.add(export);
        

		
        List<J_B_com> jL = J_B_comDao.getComs();
        for (int i =0; i<jL.size(); i++){
            J_B_com jl = jL.get(i);
            if(jl.getBreed().getId()==breed.getId()){
                tableModel.insertRow(0, new Object[]{jl.getJudge().getId(), jl.getJudge().getName()});
            }
        }

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
			
						fileChooser.setSelectedFile(new File("breed_.pdf"));
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
							String[] headersPdfExport = {"\nID судьи\n\n", "\nИмя судьи"};
							
							String para_1 = "Информация о породе: "+breed.getTitle(); 
							Paragraph para = new Paragraph (para_1,font3);
							para.setAlignment(Element.ALIGN_LEFT);
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
        aO.setVisible(true);			
    }
    
    public static void main(String [] args){
        Owner ow = new Owner();
        ow.setId(0);
        ow.setName("Андрей");
        new ownerInfo().show(ow);
    }
}
