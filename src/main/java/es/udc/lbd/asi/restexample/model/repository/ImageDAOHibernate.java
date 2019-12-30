package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Aerodrome;
import es.udc.lbd.asi.restexample.model.domain.Image;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class ImageDAOHibernate extends GenericDAOHibernate implements ImageDAO{

	@Override
	public List<Image> findByRoute(Route route) {
		return getSession().createQuery("from Image a where a.route = :route order by a.id").setParameter("route", route).list();
	}

	@Override
	public void save(Image image) {
		getSession().saveOrUpdate(image);
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));		
	}

	@Override
	public Image findById(Long id) {
		return (Image) getSession().createQuery("from Image a where a.id = :id").setParameter("id", id).uniqueResult();
	}

}
