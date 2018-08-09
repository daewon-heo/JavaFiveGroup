package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class PastWeatherView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;

	public PastWeatherView(){
		super("첫번째 화면");
		initView();
	}
	
	public void initView() {
		setBounds(300, 300, 500, 500);
		setLayout(new GridLayout());
//		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
