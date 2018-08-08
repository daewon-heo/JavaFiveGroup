package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class View3View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114497684814447320L;
	
	private static View3View instance = null;
	
	public static View3View getInstance(){
		if(instance == null)
			instance = new View3View();
		return instance;
	}

	private View3View(){
		super("세번째 화면");
		setBounds(200, 200, 300, 300);
		setLayout(new GridLayout());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
