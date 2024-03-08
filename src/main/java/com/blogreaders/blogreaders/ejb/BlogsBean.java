package com.blogreaders.blogreaders.ejb;

import com.blogreaders.blogreaders.modelo.blogs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        entityManager.merge(Blogs);
        return Blogs;
    }

    public blogs obtenerBlog(Long id) {
        return entityManager.find(blogs.class, id);
    }

    public boolean obtenerBlogPorTitulo(String title) {
        blogs datos = new blogs();
        try {
        datos = entityManager.createQuery("SELECT b FROM blogs b WHERE b.title = :title", blogs.class)
                .setParameter("title", title)
                .getSingleResult();

            return true;
        }
        catch(NoResultException e){}
        return false;

    }

    public blogs obtenerBlogConReaders(Long id) {
        return entityManager.createNamedQuery("blogConReaders", blogs.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<blogs> obtenerTodosLosBlogs() {
        return entityManager.createNamedQuery("buscarTodo", blogs.class).getResultList();
    }
    @Transactional
    public blogs actualizarBlog(blogs Blogs) {
        {
            entityManager.merge(Blogs);
            entityManager.flush();
        }
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

