package com.shuffle.cglib;

public class Test {

	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacade facade = (BookFacade) cglib.getInstance(new BookFacade());
		
		facade.addBook();
		
		facade.removeBook();
	}
}
