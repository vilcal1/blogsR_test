package com.blogreaders.blogreaders.controladores;

import com.blogreaders.blogreaders.ejb.BlogsBean;
import com.blogreaders.blogreaders.modelo.blogs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="blogsController")
@ViewScoped
public class BlogsController implements Serializable {
    @EJB
    private BlogsBean blogsBean;
    private blogs Blogs;
    private List<blogs> listaBlogs = new ArrayList<>();

    // métodos para operaciones CRUD

    public BlogsController() {
    }

    @PostConstruct
    public void init() {
        Blogs = new blogs();
        actualizarListaBlogs();
    }



    public void agregarBlogs() {
        blogsBean.agregarBlog(Blogs);
        limpiar();
        actualizarListaBlogs();
    }

    public void obtenerBlog(Long id) {
        Blogs = blogsBean.obtenerBlog(id);
    }

    public void actualizarBlog() {
        blogsBean.actualizarBlog(Blogs);
        limpiar();
        actualizarListaBlogs();
    }

    public void eliminarBlog() {
        blogsBean.eliminarBlog(Blogs);
        limpiar();
        actualizarListaBlogs();
    }

    private void actualizarListaBlogs() {
        listaBlogs = blogsBean.obtenerTodosLosBlogs();
    }

    private void limpiar() {
        Blogs = new blogs();
    }

    public blogs getBlogs() {
        return Blogs;
    }

    public void setBlogs(blogs Blogs) {
        this.Blogs = Blogs;
    }

    public List<blogs> getListaBlogs() {
        return listaBlogs;
    }

    public void setListaBlogs(List<blogs> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }
}
