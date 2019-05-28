package es.udc.lbd.asi.restexample.model.repository;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.PasswordResetToken;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class PasswordTokenHibernate extends GenericDAOHibernate implements PasswordTokenDAO{

	@Override
	public void save(PasswordResetToken token) {
		getSession().saveOrUpdate(token);
		
	}

	
}
