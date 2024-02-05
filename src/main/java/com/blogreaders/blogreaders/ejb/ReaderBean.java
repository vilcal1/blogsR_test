package com.blogreaders.blogreaders.ejb;


import com.blogreaders.blogreaders.modelo.Reader;
import com.blogreaders.blogreaders.modelo.blogs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ReaderBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Reader agregarReader(Reader Reader) {
       /* entityManager.createNamedQuery("mntor", blogs.class).getResultList();*/
        entityManager.merge(Reader);
        /*entityManager.persist(Reader);*/
        entityManager.flush();
        return Reader;
    }

    public Reader obtenerReader(Long id) {
        return entityManager.find(Reader.class, id);
    }

    public List<Reader> obtenerTodasLasReaders() {
        return entityManager.createNamedQuery("encontrarAll", Reader.class).getResultList();
    }
    @Transactional
    public Reader actualizarReader(Reader Reader) {
        entityManager.merge(Reader);
        entityManager.flush();
        return Reader;
    }

    @Transactional
    public void eliminarReader(Reader Reader) {
        Reader = entityManager.find(Reader.class, Reader.getReadersId());
        if (Reader != null) {
            entityManager.remove(Reader);
        }
    }
}

