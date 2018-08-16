package com.ff.run;

import com.ff.controller.MainController;

import develop.Develop;

public class Run {

	public static void main(String[] args) {
		MainController test1 = MainController.getInstance();
		test1.viewShow();
		new Thread(test1).start();
		
//		test1.getDatas();
//		System.out.println("첫번째 팀 프로젝트");
//		new Develop().test();
	}
}
