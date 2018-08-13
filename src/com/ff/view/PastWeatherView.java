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
import develop.Weather;




public class PastWeatherView extends JFrame {

	/**
	 * 
	 */
	
	private PastWeatherController pwc = new PastWeatherController();
	private static final long serialVersionUID = -5581181417128426852L;
	
	public PastWeatherView(String pastIcon, String presentIcon, double temperature, String humidity, double ta, String hm){
		super("작년의 오늘 날씨와 현재 날씨");
		
		initView(pastIcon , presentIcon ,temperature, humidity, ta, hm);
		
	}
	
	public void initView(String pastIcon, String presentIcon, double temperature, String humidity, double ta, String hm) {
		
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
		JLabel compareLb = new JLabel(pwc.compareTemperature());
		
		Image pastWeatherIcon = new ImageIcon(pastIcon).getImage()
				.getScaledInstance(200, 200, 0);
		
		Image presentWeaterIcon = new ImageIcon(presentIcon).getImage()
				.getScaledInstance(200, 200, 0);
		
		JLabel iconLb1 = new JLabel(new ImageIcon(pastWeatherIcon));
		
		JLabel iconLb2 = new JLabel(new ImageIcon(presentWeaterIcon));
		
		JLabel pastTemperature = new JLabel(temperature+" ℃");
		JLabel pastHumidity = new JLabel(humidity);
		
		JLabel presentTemperature = new JLabel(ta+" ℃");
		JLabel presentHumidity = new JLabel(hm);
		
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
		pastTemperature.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		pastTemperature.setFont(pastTemperature.getFont().deriveFont(20.0f));
		pastTemperature.setLocation(150, 300);
		pastTemperature.setSize(100,30);
		pastHumidity.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		pastHumidity.setFont(pastHumidity.getFont().deriveFont(20.0f));
		pastHumidity.setLocation(150, 350);
		pastHumidity.setSize(100,30);
		presentTemperature.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		presentTemperature.setFont(presentTemperature.getFont().deriveFont(20.0f));
		presentTemperature.setLocation(500, 300);
		presentTemperature.setSize(100,30);
		presentHumidity.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		presentHumidity.setFont(presentHumidity.getFont().deriveFont(20.0f));
		presentHumidity.setLocation(500, 350);
		presentHumidity.setSize(100,30);
		
		
		iconLb1.setLocation(85, 85);
		iconLb1.setSize(200, 200);
		iconLb2.setLocation(440, 85);
		iconLb2.setSize(200, 200);
		
		pan1.add(titleLb1);
		pan1.add(iconLb1);
		pan1.add(pastTemperature);
		pan1.add(pastHumidity);
		pan2.add(titleLb2);
		pan2.add(iconLb2);
		pan2.add(presentTemperature);
		pan2.add(presentHumidity);
		pan3.add(compareLb);
		
		
		add(pan1);
		add(pan2);
		add(pan3,"South");
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	

}
