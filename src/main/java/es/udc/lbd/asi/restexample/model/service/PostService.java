package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.domain.Tag;
import es.udc.lbd.asi.restexample.model.repository.PostDAO;
import es.udc.lbd.asi.restexample.model.repository.TagDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.PostDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TagDAO tagDAO;

    public List<PostDTO> findAll() {
        return postDAO.findAll().stream().map(post -> new PostDTO(post)).collect(Collectors.toList());
    }

    public PostDTO findById(Long id) {
        return new PostDTO(postDAO.findById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')") // Con estas anotaciones evitamos que usuarios no autorizados accedan a
                                           // ciertas funcionalidades
    @Transactional(readOnly = false)
    public PostDTO save(PostDTO post) {
        Post bdPost = new Post(post.getTitle(), post.getBody());
        bdPost.setAuthor(userDAO.findById(post.getAuthor().getId()));
        post.getTags().forEach(tag -> {
            bdPost.getTags().add(tagDAO.findById(tag.getId()));
        });
        postDAO.save(bdPost);
        return new PostDTO(bdPost);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional(readOnly = false)
    public PostDTO update(PostDTO post) {
        Post bdPost = postDAO.findById(post.getId());
        bdPost.setTitle(post.getTitle());
        bdPost.setBody(post.getBody());
        bdPost.setAuthor(userDAO.findById(post.getAuthor().getId()));
        bdPost.getTags().clear();
        post.getTags().forEach(tag -> {
            bdPost.getTags().add(tagDAO.findById(tag.getId()));
        });
        postDAO.save(bdPost);
        return new PostDTO(bdPost);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        postDAO.deleteById(id);
    }
}
