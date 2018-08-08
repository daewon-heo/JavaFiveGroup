package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class View1View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;

	public View1View(){
		super("첫번째 화면");
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
