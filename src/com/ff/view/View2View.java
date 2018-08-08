package com.ff.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class View2View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 181983372302562901L;
	
	public View2View(){
		super("두번째 화면");
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
