package com.opinous.exception;

public class RoomOverloadedException extends Exception {

    public RoomOverloadedException(String ex) {
        super(ex);
    }

    public RoomOverloadedException(String ex, Throwable th) {
        super(ex, th);
    }
}
