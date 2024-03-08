package com.blogreaders.blogreaders.controladores;

import com.blogreaders.blogreaders.ejb.BlogsBean;
import com.blogreaders.blogreaders.modelo.blogs;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
        PrimeFaces.current().executeScript("PF('confirmaAgregaBlogDialog').show()");
    }

    public void obtenerBlog(Long id) {
        Blogs = blogsBean.obtenerBlog(id);
    }



    public void actualizarBlog() {
            boolean buscaBlog;
            buscaBlog = blogsBean.obtenerBlogPorTitulo(Blogs.getTitle());

            if (!buscaBlog) {
                blogsBean.actualizarBlog(Blogs);
                limpiar();
                actualizarListaBlogs();
                PrimeFaces.current().executeScript("PF('manageBlogDialog').hide()");
            }
            else
                PrimeFacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"error","ya existe ese registro"));

    }





    public void eliminarBlog() {
        blogsBean.eliminarBlog(Blogs);
        limpiar();
        actualizarListaBlogs();
        PrimeFaces.current().executeScript("PF('borraBlogDialog').hide()");

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
