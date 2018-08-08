package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class View1View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;
	
	private static View1View instance = null;
	
	public static View1View getInstance(){
		if(instance == null)
			instance = new View1View();
		return instance;
	}

	private View1View(){
		super("첫번째 화면");
		setBounds(200, 200, 300, 300);
		setLayout(new GridLayout());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}
