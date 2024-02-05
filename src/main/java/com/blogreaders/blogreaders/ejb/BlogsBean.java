package com.blogreaders.blogreaders.ejb;

import com.blogreaders.blogreaders.modelo.blogs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BlogsBean implements Serializable {

    private List<blogs> listat;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public blogs agregarBlog(blogs Blogs) {
        /*blogs managedEntity = entityManager
                .find(blogs.class, blogs.class.getModifiers());*/
        entityManager.merge(Blogs);
        /*entityManager.getTransaction().commit();*/
        return Blogs;
    }

    public blogs obtenerBlog(Long id) {
        return entityManager.find(blogs.class, id);
    }

    public List<blogs> obtenerTodosLosBlogs() {
        return entityManager.createNamedQuery("buscarTodo", blogs.class).getResultList();
    }
    @Transactional
    public blogs actualizarBlog(blogs Blogs) {
        entityManager.merge(Blogs);
        entityManager.flush();
        return Blogs;
    }

    @Transactional
    public void eliminarBlog(blogs Blogs) {
        Blogs = entityManager.find(blogs.class, Blogs.getId());
        if (Blogs != null) {
            entityManager.remove(Blogs);
        }
    }
}

