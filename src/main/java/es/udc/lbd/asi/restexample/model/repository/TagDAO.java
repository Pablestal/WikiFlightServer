package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Tag;

public interface TagDAO {

    void save(Tag tag);

    List<Tag> findAll();

    Tag findById(Long id);
}
