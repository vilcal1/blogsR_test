package com.blogreaders.blogreaders.ejb;

import com.blogreaders.blogreaders.modelo.blogReaders;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
public class LectorBlogBean implements Serializable {

    private List<blogReaders> listat;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public blogReaders agregarLectorBlog(blogReaders BlogReadersr) {
        entityManager.merge(BlogReadersr);
        return BlogReadersr;
    }

    public blogReaders obtenerLectorBlog(Long id) {
        return entityManager.find(blogReaders.class, id);
    }

    public List<blogReaders> obtenerTodosLosLectoresBlogs() {
        return entityManager.createNamedQuery("buscarTodo", blogReaders.class).getResultList();
    }
    @Transactional
    public blogReaders actualizarLectorBlog(blogReaders BlogReadersr) {
        entityManager.merge(BlogReadersr);
        entityManager.flush();
        return BlogReadersr;
    }

    @Transactional
    public void eliminarLectorBlog(blogReaders BlogReadersr) {
        BlogReadersr = entityManager.find(blogReaders.class, BlogReadersr.getId());
        if (BlogReadersr != null) {
            entityManager.remove(BlogReadersr);
        }
    }
}

