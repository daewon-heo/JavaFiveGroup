package com.ff.controller;


import com.ff.view.TodayStyle2View;

public class TodayStyle2Controller {
	TodayStyle2View TodayStyle2View = null;
	
	private static TodayStyle2Controller instance = null;
	
	public static TodayStyle2Controller getInstance() {
		if(instance == null)
			instance = new TodayStyle2Controller();
		
		return instance;
	}
	
	private TodayStyle2Controller(){
		
	}
	
	
	public void viewShow() {
		
		TodayStyle2View = TodayStyle2View.getInstance();

	}

}
