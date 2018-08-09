package com.ff.controller;

import com.ff.view.SpecificDateView;

public class SpecificDateController {
	SpecificDateView specificdateView = null;
	
	public SpecificDateController(){

	}
	
	public void viewShow() {
		specificdateView = new SpecificDateView();
	}
}
