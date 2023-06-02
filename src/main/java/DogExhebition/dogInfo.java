package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class dogInfo {
    protected static final JOptionPane JOptionPanel = null;
	private JFrame aO;
    private JButton ok;
    private JButton export;
	public static final String FONT = "C:/Users/danii/OneDrive/Рабочий стол/JavaVScode/dog.exhibition/assets/fonts/arialmt.ttf";

	private JLabel title_label;

    private JLabel breed_label;
    private JLabel owner_label;
    private JLabel award_label;

	
    public void show(Dog dog){

        aO = new JFrame("Информация о собаке");
        aO.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//dog.png").getImage());
		
		title_label = new JLabel("Информация о собаке : "+dog.getName());
		title_label.setFont(new Font("Arial", Font.PLAIN, 18));
		title_label.setBounds(60,20,300,40);
		aO.add(title_label);

		aO.setSize(400, 300);
		aO.setLayout(null);

        ok = new JButton("закрыть");
        ok.setBounds(280, 230, 100, 25);
        ok.setFont(new Font("Arial", Font.PLAIN, 15));
        ok.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				aO.dispose();
			}});

        export = new JButton("в PDF");
        export.setBounds(10, 230, 100, 25);
        export.setFont(new Font("Arial", Font.PLAIN, 15));

        aO.add(ok);
        aO.add(export);

        owner_label = new JLabel("Владелец: "+dog.getOwner().getName());
        owner_label.setBounds(20, 80, 250, 25);
        owner_label.setFont(new Font("Arial", Font.PLAIN, 15));

        breed_label = new JLabel("Порода: "+dog.getBreed().getTitle());
        breed_label.setBounds(20, 110, 250, 25);
        breed_label.setFont(new Font("Arial", Font.PLAIN, 15));


        award_label = new JLabel("Награда: "+dog.getAward().getTitle());
        award_label.setBounds(20, 140, 250, 25);
        award_label.setFont(new Font("Arial", Font.PLAIN, 15));

        aO.add(breed_label);
        aO.add(owner_label);
        aO.add(award_label);

        export.addActionListener(new ActionListener()
			{
				public void actionPerformed (ActionEvent event)
				{
					try 
					{
						BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
						com.itextpdf.text.Font font1=new com.itextpdf.text.Font(bf,15,Font.PLAIN);
						com.itextpdf.text.Font font3=new com.itextpdf.text.Font(bf,20,Font.CENTER_BASELINE);
						JFileChooser fileChooser = new JFileChooser();
			
						fileChooser.setCurrentDirectory(new File("."));
			
						fileChooser.setSelectedFile(new File("owner_.pdf"));
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
							
							String para_1 = "Информация о  собаке: "; 
							Paragraph para = new Paragraph (para_1,font3);

                            Paragraph p = new Paragraph(" ",font1);
							p.setAlignment(0);
							document.add(p);

							para.setAlignment(Element.ALIGN_LEFT);
							document.add(para); 

                            String para_5 = "Кличка: "+dog.getName(); 
							Paragraph para5 = new Paragraph (para_5,font1);
                            document.add(para5);

							document.add(p);

                            String para_2 = "Порода: "+dog.getBreed().getTitle(); 
							Paragraph para2 = new Paragraph (para_2,font1);
                            document.add(para2);

                            document.add(p);
                            
                            String para_3 = "Владелец: "+dog.getOwner().getName(); 
							Paragraph para3 = new Paragraph (para_3,font1);
                            document.add(para3);

                            document.add(p);

                            String para_4 = "Награда: "+dog.getAward().getTitle(); 
							Paragraph para4 = new Paragraph (para_4,font1);
                            document.add(para4);

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

