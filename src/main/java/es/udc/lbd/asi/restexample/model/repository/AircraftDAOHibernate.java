package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Aircraft;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class AircraftDAOHibernate extends GenericDAOHibernate implements AircraftDAO {

	@Override
	public List<Aircraft> findAll() {
		return getSession().createQuery("from Aircraft a order by a.manufacturer, a.model").list();
	}

	@Override
	public void save(Aircraft aircraft) {
		getSession().saveOrUpdate(aircraft);
	}

	@Override
	public Aircraft findById(Long id) {
		return (Aircraft) getSession().createQuery("from Aircraft a where a.id = :id").setParameter("id", id).uniqueResult();
	}
	
	@Override
	public Aircraft findByModel(String model) {
		return (Aircraft) getSession().createQuery("from Aircraft a where a.model = :model").setParameter("model", model).uniqueResult();
	}
	
	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
		
	}

}
