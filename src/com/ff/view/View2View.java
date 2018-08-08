package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class View2View extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 181983372302562901L;
	
	private static View2View instance = null;
	
	public static View2View getInstance(){
		if(instance == null)
			instance = new View2View();
		return instance;
	}
	
	private View2View(){
		super("두번째 화면");
		setBounds(200, 200, 300, 300);
		setLayout(new GridLayout());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
