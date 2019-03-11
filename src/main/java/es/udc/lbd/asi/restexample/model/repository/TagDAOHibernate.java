package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Tag;
import es.udc.lbd.asi.restexample.model.repository.util.GenericDAOHibernate;

@Repository
public class TagDAOHibernate extends GenericDAOHibernate implements TagDAO {

    @Override
    public void save(Tag tag) {
        getSession().saveOrUpdate(tag);
    }

    @Override
    public List<Tag> findAll() {
        return getSession().createQuery("from Tag").list();
    }

    @Override
    public Tag findById(Long id) {
        return (Tag) getSession().createQuery("from Tag t where t.id = :id").setParameter("id", id)
                .uniqueResult();
    }
    
    
}
