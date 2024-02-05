package com.blogreaders.blogreaders.controladores;


import com.blogreaders.blogreaders.ejb.LectorBlogBean;
import com.blogreaders.blogreaders.modelo.blogReaders;
import com.blogreaders.blogreaders.modelo.blogs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="lectorBlogController")
@ViewScoped
public class LectorBlogController implements Serializable {
    @EJB
    private LectorBlogBean lectorBlogBean;
    private blogReaders blogReaders;
    private List<blogReaders> listaLectoresBlogs = new ArrayList<>();

    // métodos para operaciones CRUD

    public LectorBlogController() {
    }

    @PostConstruct
    public void init() {
        blogReaders = new blogReaders();
        actualizarListaLectoresBlogs();
    }



    public void agregarLectoresBlogs() {
        lectorBlogBean.agregarLectorBlog(blogReaders);
        limpiar();
        actualizarListaLectoresBlogs();
    }

    public void obtenerLectorBlog(Long id) {
        blogReaders = lectorBlogBean.obtenerLectorBlog(id);
    }

    public void actualizarLectorBlog() {
        lectorBlogBean.actualizarLectorBlog(blogReaders);
        limpiar();
        actualizarListaLectoresBlogs();
    }

    public void eliminarLectorBlog() {
        lectorBlogBean.eliminarLectorBlog(blogReaders);
        limpiar();
        actualizarListaLectoresBlogs();
    }

    private void actualizarListaLectoresBlogs() {
        listaLectoresBlogs = lectorBlogBean.obtenerTodosLosLectoresBlogs();
    }

    private void limpiar() {
        blogReaders = new blogReaders();
    }

    public List<com.blogreaders.blogreaders.modelo.blogReaders> getListaLectoresBlogs() {
        return listaLectoresBlogs;
    }

    public void setListaLectoresBlogs(List<com.blogreaders.blogreaders.modelo.blogReaders> listaLectoresBlogs) {
        this.listaLectoresBlogs = listaLectoresBlogs;
    }

    public LectorBlogBean getLectorBlogBean() {
        return lectorBlogBean;
    }

    public void setLectorBlogBean(LectorBlogBean lectorBlogBean) {
        this.lectorBlogBean = lectorBlogBean;
    }

    public com.blogreaders.blogreaders.modelo.blogReaders getBlogReaders() {
        return blogReaders;
    }

    public void setBlogReaders(com.blogreaders.blogreaders.modelo.blogReaders blogReaders) {
        this.blogReaders = blogReaders;
    }
}
