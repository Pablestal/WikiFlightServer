package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Post;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class PostDAOHibernate extends GenericDAOHibernate implements PostDAO {

    @Override
    public List<Post> findAll() {
        return getSession().createQuery("from Post").list();
    }

    @Override
    public Post findById(Long id) {
        return (Post) getSession().createQuery("from Post p where p.id = :id").setParameter("id", id).uniqueResult();
    }

    @Override
    public void save(Post post) {
        getSession().saveOrUpdate(post);
    }

    @Override
    public void deleteById(Long id) {
        getSession().delete(findById(id));
    }
}
