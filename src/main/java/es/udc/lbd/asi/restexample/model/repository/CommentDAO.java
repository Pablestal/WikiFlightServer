package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Comment;
import es.udc.lbd.asi.restexample.model.domain.Route;

public interface CommentDAO {
	
	Comment findById(Long id);

	List<Comment> findByRoute(Route route);
	
	void save(Comment comment);
	
	void deleteById(Long id);
}
