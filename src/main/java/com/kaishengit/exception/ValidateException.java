package com.kaishengit.exception;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public ValidateException(){};
	public ValidateException(String msg){
		super(msg);
	}
	public ValidateException(Throwable e){
		super(e);
	}
	public ValidateException(String msg, Throwable e){
		super(msg,e);
	}

}
