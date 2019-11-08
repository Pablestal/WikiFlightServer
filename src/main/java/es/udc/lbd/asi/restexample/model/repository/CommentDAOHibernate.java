package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Comment;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class CommentDAOHibernate extends GenericDAOHibernate implements CommentDAO {

	@Override
	public List<Comment> findByRoute(Route route) {
		return getSession().createQuery("from Comment where route = :route order by date desc").setParameter("route", route).list();

	}
	
	@Override
	public Comment findById(Long id) {
		return (Comment) getSession().createQuery("from Comment a where a.id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public void save(Comment comment) {
		getSession().saveOrUpdate(comment);
		
	}

	@Override
	public void deleteById(Long id) {
		getSession().delete(findById(id));		
	}

	
}
