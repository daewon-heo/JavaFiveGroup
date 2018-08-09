package com.ff.controller;

import com.ff.view.TodayStyleView;

public class TodayStyleController {
	TodayStyleView TodayStyleView = null;
	
	public TodayStyleController(){
		System.out.println("안녕");
	}
	
	public void viewShow() {
		TodayStyleView = new TodayStyleView();
	}
}
