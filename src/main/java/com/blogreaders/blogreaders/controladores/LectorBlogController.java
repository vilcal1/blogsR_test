package com.blogreaders.blogreaders.controladores;


import com.blogreaders.blogreaders.ejb.BlogsBean;
import com.blogreaders.blogreaders.ejb.LectorBlogBean;
import com.blogreaders.blogreaders.ejb.ReaderBean;
import com.blogreaders.blogreaders.modelo.Reader;
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

    @EJB
    private ReaderBean readerBean;
    @EJB
    private BlogsBean blogBean;
    private Reader reader;
    private blogs blog;

    private Integer readerId;

    private Integer blogId;

    private List<Reader> listaReaders = new ArrayList<>();
    private List<blogs> listaBlogs = new ArrayList<>();


    // métodos para operaciones CRUD

    public LectorBlogController() {
    }

    @PostConstruct
    public void init() {
        reader = new Reader();
        listaReaders= readerBean.obtenerTodasLasReaders();
        listaBlogs= blogBean.obtenerTodosLosBlogs();
    }

    public void lectorSeleccionado(){
        reader = readerBean.obtenerReader(Long.valueOf(readerId));
    }

    public void blogSeleccionado(){
        blog = blogBean.obtenerBlog(Long.valueOf(blogId));
    }

    public void asignarBlogAlector() {
        reader.getBlogs().add(blog);
        blog.getReaders().add(reader);
        lectorBlogBean.agregarLectorBlog(reader);
        this.blogId= null;
        this.readerId= null;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public blogs getBlog() {
        return blog;
    }

    public void setBlog(blogs blog) {
        this.blog = blog;
    }

    public List<Reader> getListaReaders() {
        return listaReaders;
    }

    public void setListaReaders(List<Reader> listaReaders) {
        this.listaReaders = listaReaders;
    }

    public List<blogs> getListaBlogs() {
        return listaBlogs;
    }

    public void setListaBlogs(List<blogs> listaBlogs) {
        this.listaBlogs = listaBlogs;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}
