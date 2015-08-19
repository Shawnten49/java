package com.shuffle.proxy;

public class Test {

	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade book = (BookFacade) proxy.bind(new BookFacadeImpl());
		
		book.addBook();
	}
}
