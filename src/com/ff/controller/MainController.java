package com.ff.controller;

import com.ff.view.MainView;

public class MainController {
	MainView mainView = null;
	
	public MainController(){
		
	}
	
	public void viewShow() {
		mainView = new MainView();
	}
}
