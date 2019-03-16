package com.opinous.exception;

public class NullParameterException extends RuntimeException {
    private static final long serialVersionUID = -7362231474120212295L;

    public NullParameterException(String ex) {
        super(ex);
    }

    public NullParameterException(String ex, Throwable th) {
        super(ex, th);
    }
}
