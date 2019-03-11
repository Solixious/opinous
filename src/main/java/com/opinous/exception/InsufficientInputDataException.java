package com.opinous.exception;

public class InsufficientInputDataException extends Exception {
    private static final long serialVersionUID = -7362231474120212295L;

    public InsufficientInputDataException(String ex) {
        super(ex);
    }

    public InsufficientInputDataException(String ex, Throwable th) {
        super(ex, th);
    }
}
