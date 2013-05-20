package com.ebtc.base.exception;

public class BusinessException extends Exception {

	public BusinessException(){
		
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Exception e){
		super(e);
	}
}
