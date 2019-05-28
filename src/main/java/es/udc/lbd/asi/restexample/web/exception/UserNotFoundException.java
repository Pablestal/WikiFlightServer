package es.udc.lbd.asi.restexample.web.exception;

public class UserNotFoundException extends ResourceException {
	
	public UserNotFoundException(String errorMsg) {
		super(errorMsg);
	}

}


