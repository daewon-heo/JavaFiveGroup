package com.ff.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		pan.setBackground(Color.WHITE);
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
		
		JTextArea area = new JTextArea();
		area.setLocation(125, 350);
		area.setSize(180, 100);
		
		
		
	

			
			
			
			
			
		btn.addActionListener(new ActionListener() {	
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
				
			String str = textField.getText();
			String stryear = str.substring(0,4);
			int intyear = Integer.valueOf(stryear);
			
			String[][] datas1 = Weather.GetPastWeather("108", intyear, "07");
			String[][] datas2 = Weather.GetPastWeather("108", intyear, "08");
			String[][] datas3 = Weather.GetPastWeather("108", intyear, "10");
			String[][] datas4 = Weather.GetPastWeather("108", intyear, "21");
			String[][] datas5 = Weather.GetPastWeather("108", intyear, "28");
			
				
				
				area.append("현재 온도  :\t"+datas1[2][3].toString() +"\n");
				area.append("최고 온도  :\t"+datas2[2][3].toString() +"\n");
				area.append("최저 온도  :\t"+datas3[2][3].toString() +"\n");
					//	    일 월
				if(datas4[2][3]==null){
					area.append("강 수 량  :\t0.0 mm\n");
				} else {
					area.append("강 수 량  :\t"+datas4[2][3].toString() +"\n");
				}
				if(datas5[2][3]==null){
					area.append("적 설 량  :\t0.0 cm\n");
				} else {
					area.append("적 설 량  :\t"+datas5[2][3].toString() +"\n");
				}
				//textField.setText("");
				
				
				
				
			}
		});
		
		pan.add(label1);
		pan.add(label2);
		pan.add(textField);
		pan.add(btn);
		pan.add(label3);
		pan.add(area);
		
		add(pan);
		
		
		
		setBounds(300, 300, 450, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
