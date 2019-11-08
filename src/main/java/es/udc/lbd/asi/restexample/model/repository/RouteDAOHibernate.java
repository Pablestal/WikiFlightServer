package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class RouteDAOHibernate extends GenericDAOHibernate implements RouteDAO{

	@Override
	public List<Route> findAll() {
		return getSession().createQuery("from Route").list();
	}

	@Override
	public List<Route> findByPilot(Pilot pilot) {
		return getSession().createQuery("from Route where pilot= :pilot").setParameter("pilot", pilot).list();
	}
	
	@Override
	public void save(Route route) {
		getSession().saveOrUpdate(route);
	}
	
	@Override
	public Route findById(Long id) {
		return (Route) getSession().createQuery("from Route a where a.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
		
	}

}
