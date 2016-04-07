package com.kaishengit.exception;

public class DataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public DataException(){};
	public DataException(String msg){
		super(msg);
	}
	public DataException(Throwable e){
		super(e);
	}
	public DataException(String msg, Throwable e){
		super(msg,e);
	}

}
