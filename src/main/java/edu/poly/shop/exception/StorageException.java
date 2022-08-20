package edu.poly.shop.exception;

public class StorageException extends RuntimeException {

	public StorageException(String message) {
		super(message);
		
	}

	public StorageException(String massage, Exception e) {
		super(massage, e);
	}
	
}
