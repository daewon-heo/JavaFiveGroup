package com.ff.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ff.controller.PastWeatherController;
import com.ff.controller.View2Controller;
import com.ff.controller.View3Controller;

public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2153902511546168300L;
	
	JButton btn1 = null;
	JButton btn2 = null;
	JButton btn3 = null;
	
	PastWeatherController view1Controller = null;
	View2Controller view2Controller = null;
	View3Controller view3Controller = null;
	
	public MainView(){
		super("메인화면");
		init();
	}
	
	public void init() {
		initView();
		addListener();
		addComponent();
	}
	
	public void initView() {
		setBounds(200, 200, 300, 300);
		setLayout(new GridLayout());
		
		btn1 = new JButton("화면1");
		btn2 = new JButton("화면2");
		btn3 = new JButton("화면3");
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addListener() {
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view1Controller = new PastWeatherController();
				view1Controller.viewShow();
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view2Controller = new View2Controller();
				view2Controller.viewShow();
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view3Controller = new View3Controller();
				view3Controller.viewShow();
			}
		});
	}
	
	public void addComponent() {
		add(btn1);
		add(btn2);
		add(btn3);
	}
}
