package es.udc.lbd.asi.restexample.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Comment;
import es.udc.lbd.asi.restexample.model.domain.Route;
import es.udc.lbd.asi.restexample.model.repository.CommentDAO;
import es.udc.lbd.asi.restexample.model.repository.RouteDAO;
import es.udc.lbd.asi.restexample.model.service.dto.CommentDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CommentService {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private RouteDAO routeDAO;
	
	public CommentDTO findById(Long id) {
		return new CommentDTO(commentDAO.findById(id));
	}
	
	public List<CommentDTO> findByRoute(Long id) {
		Route route = routeDAO.findById(id);
		return commentDAO.findByRoute(route).stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());	
	}
	
	@Transactional(readOnly = false)
	public CommentDTO save(Comment comment) {
		Comment bdComment = new Comment(comment.getDescription(), LocalDate.now(), comment.getRoute(), comment.getPilot());
		
		commentDAO.save(bdComment);
		return new CommentDTO(bdComment);
	}

	@Transactional(readOnly = false)
	public CommentDTO update(Comment comment) {
		Comment bdComment = commentDAO.findById(comment.getId());
		bdComment.setDescription(comment.getDescription());
		bdComment.setDate(comment.getDate());
		bdComment.setRoute(comment.getRoute());
		bdComment.setPilot(comment.getPilot());
		
		commentDAO.save(bdComment);
		return new CommentDTO(bdComment);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		commentDAO.deleteById(id);
	}
}
