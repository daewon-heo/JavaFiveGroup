package com.ff.run;

import com.ff.controller.MainController;

public class Run {

	public static void main(String[] args) {
		MainController test1 = MainController.getInstance();
		test1.viewShow();
		new Thread(test1).start();
	}
}
