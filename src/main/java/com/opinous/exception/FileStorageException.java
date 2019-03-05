package com.opinous.exception;

public class FileStorageException extends Exception {

	private static final long serialVersionUID = -7362238474120258295L;

	public FileStorageException(String ex) {
		super(ex);
	}

	public FileStorageException(String ex, Throwable th) {
		super(ex, th);
	}
}
