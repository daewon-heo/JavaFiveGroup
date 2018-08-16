package com.ff.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ff.controller.PastWeatherController;
import com.ff.model.CommonStatic;




public class PastWeatherView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;
	
	public static PastWeatherView instance = null;
	
	public static JPanel loadingPanel = new JPanel();
	
	public static PastWeatherView getInstance(){
		if(instance == null)
			instance = new PastWeatherView();
		return instance;
	}
		
	private PastWeatherView(){
		super("작년의 오늘은 어땠을까?");
    	
		initView();
		loadingView();
		addListener();
	}
	
	public void loadingView(){
		ImageIcon imageIcon = new ImageIcon(CommonStatic.LOADING_IMG_WEATHER);
		JLabel loadingLabel = new JLabel(imageIcon);
		JLabel background = 
				new JLabel(new ImageIcon("datas/images/day.jpg"));
		background.setBounds(0, 0, 710, 620);
		
		loadingPanel.add(background);
		background.setLayout(new BorderLayout());
		background.add(loadingLabel);
		loadingPanel.setBounds(0, -10, 710, 620);
		
		add(loadingPanel);
    	
    	try {
			this.setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));
		} catch (IOException e1) {
			System.out.println("이미지파일 오류 발생");
		}

	}
	
	public void initView() {
		
		setBounds(300, 300, 710, 620);
		setLayout(null);
		
		try {
			setIconImage(ImageIO.read(new File(CommonStatic.PASTWEATHER_ICON)));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void addListener(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				instance = null;
			}
		});
	}
	
	public void detailView(String pastIcon, String presentIcon, double temperature, String humidity, double ta, String hm, String presentDate, String pastDate){
		
		JLabel background = 
				new JLabel(new ImageIcon(CommonStatic.BACKGROUND_MAIN_IMG));
		
		background.setBounds(0,0,710,620);
		
		JLabel titleLb1 = new JLabel("작년의 오늘 날씨");
		JLabel titleLb2 = new JLabel("현재 날씨");
		JLabel pastLb = new JLabel(pastDate);
		JLabel presentLb = new JLabel(presentDate);
		JLabel compareLb = new JLabel(PastWeatherController.compareTemperature());
		
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
		
		
		titleLb1.setFont(new Font("맑은 고딕", Font.BOLD, 2));
		titleLb1.setFont(titleLb1.getFont().deriveFont(25.0f));
		titleLb1.setLocation(95, 10);
		titleLb1.setSize(300, 30);
		
		titleLb2.setFont(new Font("맑은 고딕", Font.BOLD, 2));
		titleLb2.setFont(titleLb2.getFont().deriveFont(25.0f));
		titleLb2.setLocation(490, 10);
		titleLb2.setSize(300, 30);
		
		pastLb.setFont(new Font("맑은 고딕", Font.BOLD, 2));
		pastLb.setFont(pastLb.getFont().deriveFont(16.0f));
		pastLb.setLocation(100, 60);
		pastLb.setSize(300, 30);
		
		presentLb.setFont(new Font("맑은 고딕", Font.BOLD, 2));
		presentLb.setFont(presentLb.getFont().deriveFont(16.0f));
		presentLb.setLocation(455, 60);
		presentLb.setSize(300, 30);
		
		compareLb.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		compareLb.setFont(compareLb.getFont().deriveFont(25.0f));
		compareLb.setBounds(150, 530, 500, 30);
		
		pastTemperature.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		pastTemperature.setFont(pastTemperature.getFont().deriveFont(35.0f));
		pastTemperature.setLocation(130, 350);
		pastTemperature.setSize(120, 30);
		
		pastHumidity.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		pastHumidity.setFont(pastHumidity.getFont().deriveFont(35.0f));
		pastHumidity.setLocation(130, 420);
		pastHumidity.setSize(120, 30);
		
		presentTemperature.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		presentTemperature.setFont(presentTemperature.getFont().deriveFont(35.0f));
		presentTemperature.setLocation(485, 350);
		presentTemperature.setSize(120, 30);
		
		presentHumidity.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		presentHumidity.setFont(presentHumidity.getFont().deriveFont(35.0f));
		presentHumidity.setLocation(485, 420);
		presentHumidity.setSize(120, 30);
		
		iconLb1.setLocation(80, 105);
		iconLb1.setSize(200, 200);
		iconLb2.setLocation(435, 105);
		iconLb2.setSize(200, 200);
		
		add(background);
		background.add(titleLb1);
		background.add(titleLb2);
		background.add(pastLb);
		background.add(presentLb);
		background.add(compareLb);
		background.add(pastTemperature);
		background.add(pastHumidity);
		background.add(presentTemperature);
		background.add(presentHumidity);
		background.add(iconLb1);
		background.add(iconLb2);
	}
	

}
