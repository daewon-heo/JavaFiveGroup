package com.ff.controller;

import com.ff.view.TodayStyle2View2;

public class TodayStyle2Controller2 {
	TodayStyle2View2 TodayStyle2View = null;
	
	
	private static TodayStyle2Controller2 instance = null;
	
	public static TodayStyle2Controller2 getInstance() {
		if(instance == null)
			instance = new TodayStyle2Controller2();
		
		return instance;
	}
	
	private TodayStyle2Controller2(){
		
	}
	
	public void viewShow() {
		TodayStyle2View = TodayStyle2View2.getInstance();
	}
}
