package DogExhebition;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainGUI {
	
	private JButton Ref;
	private JButton Dg;
	private JButton Hol;
	private JButton Award;
	private JButton Out;
	private JButton Breed;
	ImageIcon ico_judge;
	ImageIcon ico_dog;
	ImageIcon ico_hol;
	ImageIcon ico_award;
	ImageIcon ico_exit;
	ImageIcon ico_breed;
	public void show() {
		final JFrame a = new JFrame("Главное меню");
				
		a.setIconImage(new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//dog_main.png").getImage());
		
		ico_judge = new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//judge.png");
		JLabel label_1 = new JLabel(ico_judge);
		label_1.setBounds(40,95,64,64);
		Ref = new JButton ("судьи выставки");
		Ref.setBounds(110,95,250,64);
		Ref.setFont(new Font("Arial", Font.PLAIN, 20));
		Ref.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		ico_dog = new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//dog2.png");
		JLabel label_2 = new JLabel(ico_dog);
		label_2.setBounds(40,20,64,64);
		Dg = new JButton ("выставочные cобаки");
		Dg.setBounds(110,20,250,64);
		Dg.setFont(new Font("Arial", Font.PLAIN, 20));
		Dg.setHorizontalAlignment(SwingConstants.CENTER);
		
		Dg.addActionListener(new ActionListener()
		{ 
			public void actionPerformed (ActionEvent event)
			{
				new DogGUI().show();
			}});
		
		ico_hol = new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//dog_walking.png");
		JLabel label_3 = new JLabel(ico_hol);
		label_3.setBounds(40,170,64,64);
		Hol = new JButton ("владельцы собак");
		Hol.setBounds(110,170,250,64);
		Hol.setFont(new Font("Arial", Font.PLAIN, 20));
		Hol.setHorizontalAlignment(SwingConstants.CENTER);
		Hol.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new OwnerGUI().show();
			}});
		
		ico_award = new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//award.png");
		JLabel label_4 = new JLabel(ico_award);
		label_4.setBounds(40,320,64,64);
		Award = new JButton ("собаки призёры");
		Award.setBounds(110,320,250,64);
		Award.setFont(new Font("Arial", Font.PLAIN, 20));
		Award.setHorizontalAlignment(SwingConstants.CENTER);
		Award.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new AwardGUI().show(); 
			}});
		
		ico_exit = new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//exit2.png");
		JLabel label_5 = new JLabel(ico_exit);
		label_5.setBounds(40,395,64,64);
		Out = new JButton ("выход");
		Out.setBounds(110,395,250,64);
		Out.setFont(new Font("Arial", Font.PLAIN, 20));
		Out.setHorizontalAlignment(SwingConstants.CENTER);
		Out.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				a.dispose();
				System.exit(0);
			}});
		
		ico_breed = new ImageIcon("C://Users//danii//OneDrive//Рабочий стол//JavaVScode//dog.exhibition//images//breed.png");
		JLabel label_7 = new JLabel(ico_breed);
		label_7.setBounds(40,245,64,64);
		Breed = new JButton ("породы собак");
		Breed.setBounds(110,245,250,64);
		Breed.setFont(new Font("Arial", Font.PLAIN, 20));
		Breed.setHorizontalAlignment(SwingConstants.CENTER);
		Breed.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{				
				new BreedGUI().show();
			}});		
		
		Ref.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				new JudgeGUI().show();
			}});
		
		a.add(Ref);
		a.add(Dg);
		a.add(Hol); 
		a.add(Award);
		a.add(Out);
		a.add(Breed);
		
		a.add(label_1);
		a.add(label_2);
		a.add(label_3);
		a.add(label_4);
		a.add(label_5);
		a.add(label_7);
		
		a.setSize(420,540);
		a.setLayout(null); 
		a.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainGUI().show(); 
	}
}
