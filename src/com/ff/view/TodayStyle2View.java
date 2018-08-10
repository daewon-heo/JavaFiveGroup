package com.ff.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TodayStyle2View extends JFrame {
	private static final long serialVersionUID = 781983372302967463L;

	public TodayStyle2View(){
		super("스타일 더보기");
		subView();
		
	}
	//setFocusPainted
	
	//setContentAreaFilled
	//setBoarderPainted
	
	public void subView(){
		setBounds(500, 200, 500, 500);
		setSize(350, 700);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());
//		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(3, 3));
		setContentPane(panel);

		setVisible(true);

		
		nineImg(getSeason(), panel);




		pack();
		setMinimumSize(getPreferredSize());
		
		try {
			setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));
			

		} catch (IOException e) {
		
			System.out.println("이미지 읽기 실패!");
		}
		
		
	}
	
	public String getSeason(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.MONTH) +1;
		System.out.println("현재 월 : " + iSeason);
		
		String sSeason = null;
		
		switch (iSeason) {
		case 12:
		case 1:
		case 2:
			sSeason = "winter";
			break;
		case 3:
		case 4:
		case 5:
			sSeason = "spring";
			break;
		case 6:
		case 7:
		case 8:
			sSeason = "summer";
			break;
		case 9:
		case 10:
		case 11:
			sSeason = "fall";
			break;
		default:
			break;
		}
		return sSeason;
	}
	
	public void nineImg(String season, JPanel panel){
		
		String path = "datas/images/style/" + season + "/";
		JButton btn1 = new JButton();
		JButton btn2 = new JButton();
		JButton btn3 = new JButton();
		JButton btn4 = new JButton();
		JButton btn5 = new JButton();
		JButton btn6 = new JButton();
		JButton btn7 = new JButton();
		JButton btn8 = new JButton();
		JButton btn9 = new JButton();
		
		try {
		    Image myPicture1 = new ImageIcon(path +  "1" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture2 = new ImageIcon(path +  "2" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture3 = new ImageIcon(path +  "3" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture4 = new ImageIcon(path +  "4" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture5 = new ImageIcon(path +  "5" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture6 = new ImageIcon(path +  "6" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture7 = new ImageIcon(path +  "7" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture8 = new ImageIcon(path +  "8" +".jpg").getImage().getScaledInstance(150, 260, 0);
		    Image myPicture9 = new ImageIcon(path +  "9" +".jpg").getImage().getScaledInstance(150, 260, 0);

		    
		    
		    btn1.setIcon(new ImageIcon(myPicture1));
		    btn2.setIcon(new ImageIcon(myPicture2));
		    btn3.setIcon(new ImageIcon(myPicture3));
		    btn4.setIcon(new ImageIcon(myPicture4));
		    btn5.setIcon(new ImageIcon(myPicture5));
		    btn6.setIcon(new ImageIcon(myPicture6));
		    btn7.setIcon(new ImageIcon(myPicture7));
		    btn8.setIcon(new ImageIcon(myPicture8));
		    btn9.setIcon(new ImageIcon(myPicture9));
		    
			btn1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});

			btn2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					

				}
			});
			btn3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});

			btn4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});

			btn5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});

			btn6.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});

			btn7.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});

			btn8.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});
			btn9.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});
		    /*btn1.setBorderPainted(false);
		    btn1.setFocusPainted(false);
		    btn1.setContentAreaFilled(false);
		    
		    btn2.setBorderPainted(false);
		    btn2.setFocusPainted(false);
		    btn2.setContentAreaFilled(false);
		    
		    btn3.setBorderPainted(false);
		    btn3.setFocusPainted(false);
		    btn3.setContentAreaFilled(false);
		    
		    btn4.setBorderPainted(false);
		    btn4.setFocusPainted(false);
		    btn4.setContentAreaFilled(false);
		    
		    btn5.setBorderPainted(false);
		    btn5.setFocusPainted(false);
		    btn5.setContentAreaFilled(false);
		    
		    btn6.setBorderPainted(false);
		    btn6.setFocusPainted(false);
		    btn6.setContentAreaFilled(false);
		    
		    btn7.setBorderPainted(false);
		    btn7.setFocusPainted(false);
		    btn7.setContentAreaFilled(false);
		    
		    btn8.setBorderPainted(false);
		    btn8.setFocusPainted(false);
		    btn8.setContentAreaFilled(false);
		    
		    btn9.setBorderPainted(false);
		    btn9.setFocusPainted(false);
		    btn9.setContentAreaFilled(false);*/
		  //setFocusPainted
			
			//setContentAreaFilled
			//setBoarderPainted
		    
		    panel.add(btn1);
		    panel.add(btn2);
		    panel.add(btn3);
		    panel.add(btn4);
		    panel.add(btn5);
		    panel.add(btn6);
		    panel.add(btn7);
		    panel.add(btn8);
		    panel.add(btn9);
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
}
