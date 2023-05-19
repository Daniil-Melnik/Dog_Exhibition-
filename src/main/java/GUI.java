import java.awt.BorderLayout;
import org.apache.log4j.Logger;
import java.util.*;
import java.lang.*;

//import org.w3c.dom.Attr;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerConfigurationException;


import java.io.File;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import org.imgscalr.Scalr;
import org.imgscalr.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
/**
 * @author mukomol
 *
 */

import java.awt.*;

public class GUI {
/**
 * 
 * @param main frame
 * 
 * */
private JFrame bookList;
/**
 * 
 * @param dog table
 * 
 * */
private DefaultTableModel model;
private JButton save;
/**
 * 
 * @param button to export
 * 
 * */
private JButton expord;
private JToolBar toolBar;
/**
 * 
 * @param scrolling
 * 
 * */

private JScrollPane scroll;
private JTable dogs;
private JComboBox refereeName;
private JComboBox ext;
private JRadioButton referee;
private JRadioButton holder;
private JRadioButton award;
private ButtonGroup bG;
private JPanel exportPanel;
private JTextField filename;

private JTextField holderName;
private JButton filter;
private JButton export;
private JButton update;
private JButton add;
private JButton delete;

private static final Logger log = Logger.getLogger("GUI.class");

public void show() {

bookList = new JFrame("-");
bookList.setSize(500, 300);
bookList.setLocation(100, 100);
bookList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


save = new JButton(new ImageIcon("src/main/java/save1.png"));
export = new JButton(new ImageIcon("src/main/java/export2.png"));
update = new JButton(new ImageIcon("src/main/java/update1.png"));
add = new JButton(new ImageIcon("src/main/java/add.png"));
delete = new JButton(new ImageIcon("src/main/java/delete.png"));

save.setToolTipText("save");
export.setToolTipText("import");
update.setToolTipText("update");
add.setToolTipText("add");
delete.setToolTipText("delete");

toolBar = new JToolBar("instruments");
toolBar.add(save);
toolBar.add(export);
toolBar.add(update);
toolBar.add(add);
toolBar.add(delete);

//save.addActionListener (new ActionListener()
//{
//public void actionPerformed (ActionEvent event)
//
//{
//	FileDialog save = new FileDialog(bookList, "Сохранение данных", FileDialog.SAVE);
//	save.setFile("*.txt");
//	save.setVisible(true); // Отобразить запрос пользователю
//	// Определить имя выбранного каталога и файла
//	String fileName = save.getDirectory() + save.getFile();
//	if(fileName == null) return; // Если пользователь нажал «отмена»
//	try {
//		BufferedWriter writer = new BufferedWriter (new FileWriter("src/main/java/test_1.txt"));
//		for (int i = 0; i < model.getRowCount(); i++) // Для всех строк
//		for (int j = 0; j < model.getColumnCount(); j++) // Для всех столбцов
//		{writer.write ((String) model.getValueAt(i, j)); // Записать значение из ячейки
//		writer.write("\n"); // Записать символ перевода каретки
//		}
//		writer.close();
//		JOptionPane.showMessageDialog (bookList, "Данные сохранены");
//		}
//		catch(IOException e) // Ошибка записи в файл
//		{ e.printStackTrace(); }
//	
//}});





save.addActionListener (new ActionListener()
{
public void actionPerformed (ActionEvent event)

{
	
//	try {
//		// Создание парсера документа
//		DocumentBuilder builder =
//		DocumentBuilderFactory.newInstance().newDocumentBuilder();
//		// Создание пустого документа
//		Document doc = builder.newDocument();
//		
//		Node doglist = doc.createElement("doglist");
//		doc.appendChild(doglist);
//		// Создание дочерних элементов book и присвоение значений атрибутам
//		for (int i = 0; i < model.getRowCount(); i++)
//		{
//		Element dog = doc.createElement("dog");
//		doglist.appendChild(dog);
//		dog.setAttribute("num", (String)model.getValueAt(i, 0));
//		dog.setAttribute("holder", (String)model.getValueAt(i, 1));
//		dog.setAttribute("name", (String)model.getValueAt(i, 2));
//		dog.setAttribute("breed", (String)model.getValueAt(i, 3));
//		dog.setAttribute("referee", (String)model.getValueAt(i, 4));
//		dog.setAttribute("awards", (String)model.getValueAt(i, 5));
//		}
//		try {
//		// Создание преобразователя документа
//		Transformer trans = TransformerFactory.newInstance().newTransformer();
//		// Создание файла с именем books.xml для записи документа
//		java.io.FileWriter fw = new FileWriter("dogs.xml");
//		// Запись документа в файл
//		trans.transform(new DOMSource(doc), new StreamResult(fw));
//		JOptionPane.showMessageDialog (bookList, "Данные сохранены");
//		}
//		catch (TransformerConfigurationException e) { e.printStackTrace(); }
//		catch (TransformerException e) { e.printStackTrace(); }
//		catch (IOException e) { e.printStackTrace(); }	
//		
//		} catch (ParserConfigurationException e) { e.printStackTrace(); }
	
	
}});



//export.addActionListener (new ActionListener()
//{
//public void actionPerformed (ActionEvent event)
//{
//	FileDialog open = new FileDialog(bookList, "Сохранение данных", FileDialog.LOAD);
//	open.setFile("*.txt");
//	open.setVisible(true); // Отобразить запрос пользователю
//	// Определить имя выбранного каталога и файла
//	String fileName = open.getDirectory() + open.getFile();
//	if(fileName == null) return; // Если пользователь нажал «отмена
//
//	try {
//	BufferedReader reader = new BufferedReader(new FileReader(fileName));
//	int rows = model.getRowCount();
//	for (int i = 0; i < rows; i++) model.removeRow(0); // Очистка таблицы
//	String id;
//	do {
//	id = reader.readLine();
//	if(id != null)
//	{ String name_hold = reader.readLine();
//	String name_dog = reader.readLine();
//	String poroda = reader.readLine();
//	String sudia = reader.readLine();
//	String awards = reader.readLine();
//	model.addRow(new String[]{id,name_hold,name_dog,poroda,sudia,awards}); // Запись строки в таблицу
//	}
//	} while(id != null);
//	reader.close();
//	JOptionPane.showMessageDialog (bookList, "Данные добавлены");
//	} catch (FileNotFoundException e) {e.printStackTrace();} // файл не найден
//	catch (IOException e) {e.printStackTrace();}
//	
//	
//}});

export.addActionListener (new ActionListener()
{
public void actionPerformed (ActionEvent event)
{
	FileDialog open = new FileDialog(bookList, "-", FileDialog.LOAD);
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

				NodeList nlBooks = ((org.w3c.dom.Document) doc).getElementsByTagName("dog");

				for (int temp = 0; temp < nlBooks.getLength(); temp++) {

				Node elem = nlBooks.item(temp);

				NamedNodeMap attrs = elem.getAttributes();

				String num = attrs.getNamedItem("num").getNodeValue();
				String holder = attrs.getNamedItem("holder").getNodeValue();
				String name = attrs.getNamedItem("name").getNodeValue();
				String breed = attrs.getNamedItem("breed").getNodeValue();
				String referee = attrs.getNamedItem("referee").getNodeValue();
				String awards = attrs.getNamedItem("awards").getNodeValue();

				model.addRow(new String[]{num, holder, name, breed, referee, awards});
				}
				JOptionPane.showMessageDialog (bookList, "-");
				log.info("Opening data");
				log.debug("Data is opened");
		
		}
		catch (ParserConfigurationException e) { e.printStackTrace(); JOptionPane.showMessageDialog (bookList, "-");log.warn("Error with opening file");}

		catch (SAXException e) { e.printStackTrace(); JOptionPane.showMessageDialog (bookList, "-");}
		catch (IOException e) { e.printStackTrace(); JOptionPane.showMessageDialog (bookList, "-");}
		
	
}});


update.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
        try 
        {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File("."));

            fileChooser.setSelectedFile(new File("dogs.pdf"));
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
                PdfPTable pdfTable = new PdfPTable(model.getColumnCount());
                JOptionPane.showMessageDialog (bookList, model.getColumnCount());
                
                

                //Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
                Font headerFont = FontFactory.getFont("/fonts/Arial.ttf", "windows-1251", 10);
                String[] headersPdfExport = {"\nID\n\n", "\nName", "\nHolder", "\nBreed", "\nReferee", "\nawards"};


                for (int i = 0; i < model.getColumnCount(); i++) 
                {
                    PdfPCell header = new PdfPCell(new Phrase(headersPdfExport[i], headerFont));
                    header.setBackgroundColor(BaseColor.GREEN);
                    header.setBorderWidth(2);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    // Give more weight to the first row
                    pdfTable.addCell(header);
                }
                
                // Create font for table data
                Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
                
                // Set custom widths for each row 
                float[] columnWidths = new float[] {0.45f, 0.45f, 0.45f, 0.45f, 0.45f, 0.45f};
                pdfTable.setWidths(columnWidths);
                
                // Add table data
                for (int i = 0; i < model.getRowCount(); i++) 
                {
                    for (int j = 0; j < model.getColumnCount(); j++) 
                    {
                        PdfPCell data = new PdfPCell(new Phrase(model.getValueAt(i, j).toString(), dataFont));
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
    }
});

add.addActionListener (new ActionListener()
{
public void actionPerformed (ActionEvent event)
{
JOptionPane.showMessageDialog (bookList, "Add");
}});

delete.addActionListener (new ActionListener()
{
public void actionPerformed (ActionEvent event)
{
	model.setRowCount(0);
	log.info("data is cleaned");
	log.warn("data is poor");
	log.debug("cleaned");
JOptionPane.showMessageDialog (bookList, "Delete");
}});

bookList.setLayout(new BorderLayout());
bookList.add(toolBar, BorderLayout.NORTH);

String [] columns = {"number", "name of holder", "name of dog", "breed", "referee", "awards"};
String [][] data = {};
model= new DefaultTableModel(data, columns);
dogs = new JTable(model);
scroll = new JScrollPane(dogs);

referee = new JRadioButton("referee");
holder = new JRadioButton("holder");
award = new JRadioButton("awards");

bG = new ButtonGroup();

bG.add(holder);
bG.add(referee);
bG.add(award);

holder.setSelected(true);

bookList.add(scroll, BorderLayout.CENTER);

String [] arr = {};

if (holder.isSelected()) {
	String [] referees = {"Garunov", "Levchenko"};
	refereeName = new JComboBox(referees);
}
else {
	String [] referees = {"Garunov"};
	refereeName = new JComboBox(referees);
}


holderName = new JTextField("enter holder_name");
filter = new JButton("name");

JPanel filterPanel = new JPanel();
filterPanel.add(refereeName);
filterPanel.add(holderName);
filterPanel.add(filter);
filterPanel.add(referee);
filterPanel.add(holder);
filterPanel.add(award);

bookList.add(filterPanel, BorderLayout.SOUTH);

String [] exten = {".csv",".txt",".html"};

ext = new JComboBox(exten);
expord = new JButton("export");
filename = new JTextField("filename");

exportPanel = new JPanel();

exportPanel.add(expord);
exportPanel.add(filename);
exportPanel.add(ext);

//filter.addActionListener(new ActionListener() {
//public void actionPerformed(ActionEvent event) {
//	checkName(holderName);

//JOptionPane.showMessageDialog (bookList, "find" + " " + holderName.getText());
//}});

bookList.add(exportPanel, BorderLayout.WEST);

bookList.setVisible(true);
}

public static boolean checkName (String sName)
{
boolean us = false;
if (sName.contains("@")||sName.contains(",")||sName.contains(";")||sName.contains("-")||(sName.length() == 0)||(sName == " ")||sName.contains("0")||sName.contains("1")||sName.contains("2")||sName.contains("3")||sName.contains("4")||sName.contains("5")||sName.contains("7")||sName.contains("8")||sName.contains("9")) us = false;
else us = true;
return us;
}

public boolean checkFileName (String sName)
{
boolean us = false;
if (sName.contains("@")||sName.contains(",")||sName.contains(";")||sName.contains("-")||(sName.length() == 0)) us = false;
else us = true;
return us;
}



public static void main(String[] args) {

new GUI().show();
log.info("Open aplication");
log.warn("File isn't closed automatically");
}
}