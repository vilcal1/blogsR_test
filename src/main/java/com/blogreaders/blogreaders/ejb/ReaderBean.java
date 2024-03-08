package com.blogreaders.blogreaders.ejb;


import com.blogreaders.blogreaders.modelo.Reader;
import com.blogreaders.blogreaders.modelo.blogs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ReaderBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Reader agregarReader(Reader Reader) {
        entityManager.merge(Reader);
        entityManager.flush();
        return Reader;
    }

    public boolean obtenerLectorPorNombre(String nombre) {
        Reader lector = new Reader();
        try {
            lector = entityManager.createQuery("SELECT r FROM Reader r WHERE r.name = :name", Reader.class)
                    .setParameter("name", nombre)
                    .getSingleResult();

            return true;
        }
        catch(NoResultException e){}
        return false;

    }


    public Reader obtenerReader(Long id) {
        return entityManager.find(Reader.class, id);
    }

    public Reader obtenerReaderConBlogs(Long id) {
        return entityManager.createQuery("SELECT r FROM Reader r JOIN FETCH r.blogs WHERE r.id = :id", Reader.class)
                .setParameter("id", id).getSingleResult();
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
        Reader = entityManager.find(Reader.class, Reader.getId());
        if (Reader != null) {
            entityManager.remove(Reader);
        }
    }
}

