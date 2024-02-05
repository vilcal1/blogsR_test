package com.blogreaders.blogreaders.controladores;

import com.blogreaders.blogreaders.ejb.ReaderBean;
import com.blogreaders.blogreaders.modelo.Reader;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;

@ManagedBean(name="readerController")
@ViewScoped
public class ReaderController implements Serializable {
    @EJB
    private ReaderBean readerBean;
    private Reader reader;
    private List<Reader> listaReaders = new ArrayList<>();

    // métodos para operaciones CRUD

    public ReaderController() {
    }

    @PostConstruct
    public void init() {
        reader = new Reader();
        actualizarListaReaders();
    }



    public void agregarReader() {
        readerBean.agregarReader(reader);
        limpiar();
        actualizarListaReaders();
    }

    public void óobtenerReader(Long id) {
        reader = readerBean.obtenerReader(id);
    }

    public void actualizarReader() {
        readerBean.actualizarReader(reader);
        limpiar();
        actualizarListaReaders();
    }

    public void eliminarReader() {
        readerBean.eliminarReader(reader);
        limpiar();
        actualizarListaReaders();
    }

    private void actualizarListaReaders() {
        listaReaders = readerBean.obtenerTodasLasReaders();
    }

    private void limpiar() {
        reader = new Reader();
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public List<Reader> getListaReaders() {
        return listaReaders;
    }

    public void setListaReaders(List<Reader> listaReaders) {
        this.listaReaders = listaReaders;
    }
}
