package es.udc.lbd.asi.restexample.model.repository;

import es.udc.lbd.asi.restexample.PasswordResetToken;

public interface PasswordTokenDAO {
	
	void save(PasswordResetToken token);
	
}
