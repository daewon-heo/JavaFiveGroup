package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class View3View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114497684814447320L;

	public View3View(){
		super("세번째 화면");
		initView();
	}
	
	public void initView() {
		setBounds(300, 300, 300, 300);
		setLayout(new GridLayout());
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
