package com.ff.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import develop.Weather;

public class SpecificDateView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1114497684814447320L;

	public SpecificDateView(){
		super("특정 날짜 조회");
		initView();
		
	}
	
	public void initView() {

		
		
		setResizable(false);
		setLayout(null);
		
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBackground(Color.lightGray);
		pan.setBounds(0, 0, 450, 600);
		
		ImageIcon image1  = new ImageIcon("datas/images/nalssibacloud.png");
	    JLabel label1  = new JLabel(image1);
	    label1.setLocation(120,11);
		label1.setSize(200, 300);
		
		ImageIcon image2  = new ImageIcon("datas/images/nalssiba.png");
	    JLabel label2  = new JLabel(image2);
	    label2.setLocation(120,100);
		label2.setSize(200, 300);
	     
		ImageIcon image3  = new ImageIcon("datas/images/search.png");
		JLabel label3  = new JLabel(image3);
		JButton btn = new JButton(image3); 
		btn.setLocation(275,300);
		btn.setSize(40,29);
		
		JTextField textField = new JTextField(20);
		textField.setLocation(125, 300);
		textField.setSize(150, 30);
		
		
		
		
		

			
			
		btn.addActionListener(new ActionListener() { // ActionListener open	
		 
		
		@Override
		public void actionPerformed(ActionEvent e) { // ActionEvent
				
			

		
			
//			ImageIcon image4  = new ImageIcon("datas/images/cloud.png");
//		    JLabel label4  = new JLabel(image4);
//		    label4.setLocation(125, 450);
//			label4.setSize(180, 100);	
//			pan.add(label4);
//			remove(label4);
			
			JTextArea area = new JTextArea("");
			area.setLocation(125, 350);
			area.setSize(180, 200);	
			pan.add(area);
			
			
			
			
			
			String str = textField.getText();
			
			
				String stryear = str.substring(0,4);	// 년도
				int intyear = Integer.valueOf(stryear);
				
				
				if(intyear < 1960 || intyear > 2018){
					String yearfault = "조회 년도를 잘못 입력하셨습니다.";
					area.setText("");
					area.append(yearfault);
					System.out.println(yearfault);
				} else {
							System.out.println("int year : "+intyear);
							
							String[][] datas1 = Weather.GetPastWeather("108", intyear, "07");
							String[][] datas2 = Weather.GetPastWeather("108", intyear, "08");
							String[][] datas3 = Weather.GetPastWeather("108", intyear, "10");
							String[][] datas4 = Weather.GetPastWeather("108", intyear, "21");
							String[][] datas5 = Weather.GetPastWeather("108", intyear, "28");
							
						
							
							
							
								String strmonth;	// 월
								if((int)str.charAt(4)==0 ){
									strmonth = str.substring(5,6);	// ex) 20180612 0일때 6만 출력
								} else {
									strmonth = str.substring(4,6);
								}
								System.out.println("str month : " + strmonth);
								int intmonth = Integer.valueOf(strmonth);
								System.out.println("int month : " +intmonth);
								
							
							
								String strday;	// 일
								if((int)str.charAt(6)==0){
									strday = str.substring(7,8);	// ex) 20181206 0일때 6만 출력
								} else {
									strday = str.substring(6,8);
								}
								int intday = Integer.valueOf(strday);
								System.out.println("int day : "+intday);
							
								
								// 특정 날짜 요일
								Calendar cal = new GregorianCalendar();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 날씨\n");
								cal.set(intyear, intmonth-1, intday);
								System.out.println("?? : " + intmonth);
								String specific = sdf.format(cal.getTime()); 
						
								area.setText("");
								area.append(specific);
								area.append("현재 온도  :\t"+datas1[intday][intmonth].toString() +" ℃\n");
								area.append("최고 온도  :\t"+datas2[intday][intmonth].toString() +" ℃\n");
								area.append("최저 온도  :\t"+datas3[intday][intmonth].toString() +" ℃\n");
								
								if(datas4[intday][intmonth]==null){
									area.append("강 수 량  :\t0.0 mm\n");
								} else {
									area.append("강 수 량  :\t"+datas4[intday][intmonth].toString() +" mm\n");
								}
								if(datas5[intday][intmonth]==null){
									area.append("적 설 량  :\t0.0 cm\n");
								} else {
									area.append("적 설 량  :\t"+datas5[intday][intmonth].toString() +" cm\n");
								}
								/*repaint();
								revalidate();*/
							
							//textField.setText("");
							
					}	// if문 close
				area.repaint();
				area.revalidate();
				
				area.setVisible(true);
				} // ActionEvent
							
						
					}); // ActionListener close
		
		pan.add(label1);
		pan.add(label2);
		pan.add(textField);
		pan.add(btn);
		pan.add(label3);
		
		
		
		add(pan);
		
		
		
		setBounds(300, 300, 450, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
}
