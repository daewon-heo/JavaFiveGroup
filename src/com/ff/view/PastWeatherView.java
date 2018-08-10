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

import develop.TestLoading;




public class PastWeatherView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;
	
	public PastWeatherView(String iconAdress){
		super("작년의 오늘 날씨와 현재 날씨");
		
		initView(iconAdress);
		
	}
	
	public void initView(String iconAdress) {
		
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
		
		JLabel titleLb1 = new JLabel("작년의 오늘 날씨");
		JLabel titleLb2 = new JLabel("현재 날씨");
		JLabel compareLb = new JLabel(new PastWeatherController().compareTemperature());
		
		Image myImg = new ImageIcon(iconAdress).getImage()
				.getScaledInstance(180, 180, 0);
		
		JLabel iconLb1 = new JLabel(new ImageIcon(myImg));
		
		pan1.setLayout(null);
		
		pan1.setBackground(Color.white);
		
		pan1.setBounds(0, 0, 355, 552);
		
		pan1.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		pan2.setLayout(null);
		
		pan2.setBackground(Color.white);
		
		pan2.setBounds(0, 0, 0, 0);
		
		pan2.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		pan3.setBackground(Color.white);
		
		
		titleLb1.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		titleLb1.setFont(titleLb1.getFont().deriveFont(20.0f));
		titleLb1.setLocation(95, 10);
		titleLb1.setSize(300,30);
		titleLb2.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		titleLb2.setFont(titleLb2.getFont().deriveFont(20.0f));
		titleLb2.setLocation(490,10);
		titleLb2.setSize(300,30);
		compareLb.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		compareLb.setFont(compareLb.getFont().deriveFont(20.0f));
		
		iconLb1.setLocation(85, 85);
		iconLb1.setSize(180, 180);
		
		pan1.add(titleLb1);
		pan1.add(iconLb1);
		pan2.add(titleLb2);
		pan3.add(compareLb);
		
		
		add(pan1);
		add(pan2);
		add(pan3,"South");
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
