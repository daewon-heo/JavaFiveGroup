package com.ff.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.ff.controller.MainController2;
import com.ff.controller.PastWeatherController2;
import com.ff.controller.SpecificDateController2;
import com.ff.controller.TodayStyleController2;
import com.ff.model.CommonStatic;

public class MainView2 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2153902511546168300L;
	
	JButton btn1 = null;
	JButton btn2 = null;
	JButton btn3 = null;
	
	PastWeatherController2 view1Controller = null;
	SpecificDateController2 view3Controller = null;
	
	private static MainView2 instance = null;
	
	public static MainView2 getInstance() {
		if(instance == null)
			instance = new MainView2();
		if(!instance.isVisible())
			instance.setVisible(true);
		return instance;
	}
	
	private MainView2(){
		super("날씨 봐라");
		init();
	}

	public void init() {
		initView();
		addListener();
		addComponent();
        
		try {
			this.setIconImage(ImageIO.read(new File(CommonStatic.MAIN_VIEW_ICON)));
		} catch (IOException e1) {
			System.out.println("이미지파일 오류 발생");
		}
	}
	
	public void initView() {
		btn1 = new JButton("화면1");
		btn2 = new JButton("화면2");
		btn3 = new JButton("화면3");
		
		Image myImg = new ImageIcon(CommonStatic.MAINVIEW_BTN1_IMG).getImage().getScaledInstance(120,35, 0);
		
        btn1 = new JButton(new ImageIcon(myImg));
        btn1.setFocusPainted(false); 
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        
        myImg = new ImageIcon(CommonStatic.MAINVIEW_BTN2_IMG).getImage().getScaledInstance(125, 35 , 0);
        btn2 = new JButton(new ImageIcon(myImg));
        btn2.setFocusPainted(false); 
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        
        myImg = new ImageIcon(CommonStatic.MAINVIEW_BTN3_IMG).getImage().getScaledInstance(125, 35 , 0);
        btn3 = new JButton(new ImageIcon(myImg));
        btn3.setFocusPainted(false); 
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        
		btn1.setBounds(0, 0, 200, 200);
		btn2.setBounds(200, 0, 200, 200);
		btn3.setBounds(400, 0, 200, 200);
		
		setBounds(500, 250, 800, 800);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addComponent() {
		add(btn1);
		add(btn2);
		add(btn3);
	}
	
	public void addListener() {
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view1Controller = PastWeatherController2.getInstance();
				view1Controller.viewShow();
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleController2 view2Controller = TodayStyleController2.getInstance();
				view2Controller.viewShow();
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view3Controller = SpecificDateController2.getInstance();
				view3Controller.viewShow();
			}
		});
		
	}
}
