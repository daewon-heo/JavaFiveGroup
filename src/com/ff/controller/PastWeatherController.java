package com.ff.controller;

import com.ff.view.PastWeatherView;

public class PastWeatherController {
	PastWeatherView view1View = null;
	
	public PastWeatherController(){
		
	}
	
	public void viewShow() {
		view1View = new PastWeatherView();
	}
}
