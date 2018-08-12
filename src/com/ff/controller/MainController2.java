package com.ff.controller;

import com.ff.view.MainView2;

public class MainController2 {
	MainView2 mainView = null;
	private static MainController2 instance = null;
	
	public static MainController2 getInstance() {
		if(instance == null)
			instance = new MainController2();
		return instance;
	}

	private MainController2(){

	}

	public void viewShow() {
		mainView = MainView2.getInstance();
	}
}
