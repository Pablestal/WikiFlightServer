package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class AerodromeDAOHibernate extends GenericDAOHibernate implements AerodromeDAO{
	
	@Override
	public List<Aerodrome> findAll() {
		return getSession().createQuery("from Aerodrome a order by a.name").list();
	}

	@Override
	public void save(Aerodrome aerodrome) {
		getSession().saveOrUpdate(aerodrome);
	}

	@Override
	public Aerodrome findById(Long id) {
		return (Aerodrome) getSession().createQuery("from Aerodrome a where a.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));
		
	}
}
