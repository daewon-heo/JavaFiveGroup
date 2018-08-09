package com.ff.controller;

import com.ff.view.View2View;

public class View2Controller {
	View2View view2View = null;
	
	public View2Controller(){
		System.out.println("안녕");
	}
	
	public void viewShow() {
		view2View = new View2View();
	}
}
