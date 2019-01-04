package com.opinous.exception;

public class MyFileNotFoundException extends Exception {

    public MyFileNotFoundException(String ex) {
        super(ex);
    }

    public MyFileNotFoundException(String ex, Throwable th) {
        super(ex, th);
    }
}
