package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Flight;
import es.udc.lbd.asi.restexample.model.domain.Pilot;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class FlightDAOHibernate extends GenericDAOHibernate implements FlightDAO {

	@Override
	public List<Flight> findByPilot(Pilot pilot) {
		return getSession().createQuery("from Flight a where a.picUser = :pilot order by a.departureTime").setParameter("pilot", pilot).list();
	}

	@Override
	public void save(Flight flight) {
		getSession().saveOrUpdate(flight);
	}

	@Override
	public Flight findById(Long id) {
		return (Flight) getSession().createQuery("from Flight a where a.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
	}

}
