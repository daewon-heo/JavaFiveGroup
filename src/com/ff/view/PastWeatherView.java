package com.ff.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.ff.controller.PastWeatherController;
import com.ff.model.CommonStatic;




public class PastWeatherView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;

	public PastWeatherView(String str){
		super("작년의 오늘 날씨와 현재 날씨");
		initView(str);
	}
	
	public void initView(String str) {
		
		PastWeatherController pwc = new PastWeatherController();
		
		setBounds(300, 300, 710, 618);
		setLayout(new BorderLayout());
		
		try {
			setIconImage(ImageIO.read(new File(CommonStatic.PASTWEATHER_ICON)));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		
		JLabel lb1 = new JLabel("작년의 오늘 날씨");
		JLabel lb2 = new JLabel("현재 날씨");
		JLabel lb3 = new JLabel(pwc.compareTemperature());
		
		Image myImg = new ImageIcon(str).getImage()
				.getScaledInstance(180, 180, 0);
		
		JLabel wLb1 = new JLabel(new ImageIcon(myImg));
		
		JLabel wLb2 = new JLabel(new ImageIcon(myImg));
		
		pan1.setLayout(null);
		
		pan1.setBackground(Color.white);
		
		pan1.setBounds(0, 0, 355, 552);
		
		pan1.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		pan2.setLayout(null);
		
		pan2.setBackground(Color.white);
		
		pan2.setBounds(0, 0, 0, 0);
		
		pan2.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		pan3.setBackground(Color.white);
		
		
		lb1.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lb1.setFont(lb1.getFont().deriveFont(20.0f));
		lb1.setLocation(95, 10);
		lb1.setSize(300,30);
		lb2.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lb2.setFont(lb2.getFont().deriveFont(20.0f));
		lb2.setLocation(490,10);
		lb2.setSize(300,30);
		lb3.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lb3.setFont(lb3.getFont().deriveFont(20.0f));
		
		wLb1.setLocation(85, 85);
		wLb1.setSize(180, 180);
		
		pan1.add(lb1);
		pan1.add(wLb1);
		pan2.add(lb2);
		pan3.add(lb3);
		
		
		add(pan1);
		add(pan2);
		add(pan3,"South");
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
