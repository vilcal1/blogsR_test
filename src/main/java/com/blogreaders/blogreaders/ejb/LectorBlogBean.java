package com.blogreaders.blogreaders.ejb;

import com.blogreaders.blogreaders.modelo.Reader;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
public class LectorBlogBean implements Serializable {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Reader agregarLectorBlog(Reader reader) {
        entityManager.merge(reader);
        return reader;
    }


}

