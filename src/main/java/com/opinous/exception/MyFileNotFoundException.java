package com.opinous.exception;

public class MyFileNotFoundException extends Exception {

	private static final long serialVersionUID = -1902654914919760278L;

	public MyFileNotFoundException(String ex) {
		super(ex);
	}

	public MyFileNotFoundException(String ex, Throwable th) {
		super(ex, th);
	}
}
