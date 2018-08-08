package com.ff.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ff.controller.View1Controller;
import com.ff.controller.View2Controller;
import com.ff.controller.View3Controller;

public class MainView extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2153902511546168300L;

	public MainView(){
		super("메인화면");
		setBounds(200, 200, 300, 300);
		setLayout(new GridLayout());
		JButton btn1 = new JButton("화면1");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new View1Controller();
			}
		});
		add(btn1);
		
		
		JButton btn2 = new JButton("화면2");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new View2Controller();
			}
		});
		add(btn2);
		
		JButton btn3 = new JButton("화면3");
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new View3Controller();
			}
		});
		add(btn3);
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
