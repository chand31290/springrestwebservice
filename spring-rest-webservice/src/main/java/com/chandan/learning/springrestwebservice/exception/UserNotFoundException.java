package com.chandan.learning.springrestwebservice.exception;

//@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 530027171072861113L;

	public UserNotFoundException(String message)
	{
		super(message);
	}
	
	

}
