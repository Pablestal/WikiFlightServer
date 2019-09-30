package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class PilotDAOHibernate extends GenericDAOHibernate implements PilotDAO{
	
    @Override
    public List<Pilot> findAll() {
        return getSession().createQuery("from Pilot").list();
    }

    @Override
    public void save(Pilot pilot) {
        getSession().saveOrUpdate(pilot);
    }


    @Override
    public Pilot findByLogin(String login) {
        return (Pilot) getSession().createQuery("from Pilot where login = :login").setParameter("login", login).uniqueResult();
    }
}
