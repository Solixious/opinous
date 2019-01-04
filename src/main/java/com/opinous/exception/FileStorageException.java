package com.opinous.exception;

public class FileStorageException extends Exception {

    public FileStorageException(String ex) {
        super(ex);
    }
    public FileStorageException(String ex, Throwable th) {
        super(ex, th);
    }
}
