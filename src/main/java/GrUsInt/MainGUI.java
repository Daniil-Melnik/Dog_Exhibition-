package GrUsInt;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainGUI {
	private JButton Ref;
	private JButton Dg;
	private JButton Hol;
	private JButton Award;
	private JButton Out;
	ImageIcon ico_judge;
	ImageIcon ico_dog;
	ImageIcon ico_hol;
	ImageIcon ico_award;
	ImageIcon ico_exit;
	public void show() {
		final JFrame a = new JFrame("Главное меню");
		
		
		
		a.setIconImage(new ImageIcon("src/main/java/dog_main.png").getImage());
		
		ico_judge = new ImageIcon("src/main/java/judge.png");
		JLabel label_1 = new JLabel(ico_judge);
		label_1.setBounds(40,95,64,64);
		Ref = new JButton ("судьи");
		Ref.setBounds(110,95,250,64);
		Ref.setFont(new Font("Arial", Font.ITALIC, 40));
		Ref.setHorizontalAlignment(SwingConstants.CENTER);
		
		Ref.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new RefereeGUI().show();
			}});
		
		ico_dog = new ImageIcon("src/main/java/dog2.png");
		JLabel label_2 = new JLabel(ico_dog);
		label_2.setBounds(40,20,64,64);
		Dg = new JButton ("cобаки");
		Dg.setBounds(110,20,250,64);
		Dg.setFont(new Font("Arial", Font.ITALIC, 40));
		Dg.setHorizontalAlignment(SwingConstants.CENTER);
		
		Dg.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new DogGUI().show();
			}});
		
		ico_hol = new ImageIcon("src/main/java/dog_walking.png");
		JLabel label_3 = new JLabel(ico_hol);
		label_3.setBounds(40,170,64,64);
		Hol = new JButton ("владельцы");
		//Hol.setIcon(new ImageIcon("src/main/java/dog_walking.png"));
		Hol.setBounds(110,170,250,64);
		Hol.setFont(new Font("Arial", Font.ITALIC, 35));
		Hol.setHorizontalAlignment(SwingConstants.CENTER);
		Hol.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new OwnerGUI().show();
			}});
		
		ico_award = new ImageIcon("src/main/java/award.png");
		JLabel label_4 = new JLabel(ico_award);
		label_4.setBounds(40,245,64,64);
		Award = new JButton ("призы");
		Award.setBounds(110,245,250,64);
		Award.setFont(new Font("Arial", Font.ITALIC, 40));
		Award.setHorizontalAlignment(SwingConstants.CENTER);
		Award.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new AwardGUI().show();
			}});
		
		ico_exit = new ImageIcon("src/main/java/exit2.png");
		JLabel label_5 = new JLabel(ico_exit);
		label_5.setBounds(40,320,64,64);
		Out = new JButton ("выход");
		Out.setBounds(110,320,250,64);
		Out.setFont(new Font("Arial", Font.ITALIC, 40));
		Out.setHorizontalAlignment(SwingConstants.CENTER);
		Out.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				a.dispose();
			}});
		
		a.add(Ref);
		a.add(Dg);
		a.add(Hol);
		a.add(Award);
		a.add(Out);
		
		
		a.add(label_1);
		a.add(label_2);
		a.add(label_3);
		a.add(label_4);
		a.add(label_5);
		
		a.setSize(420,450);
		a.setLayout(null);
		a.setVisible(true);
	}
	

	public static void main(String[] args) {
		new MainGUI().show(); 
	}

}
