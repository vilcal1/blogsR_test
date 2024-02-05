package com.blogreaders.blogreaders.controladores;

import com.blogreaders.blogreaders.ejb.UserBean;
import com.blogreaders.blogreaders.modelo.user;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="userController")
@ViewScoped
public class UserController implements Serializable {
    @EJB
    private UserBean userBean;
    private user users;
    private List<user> listaUser = new ArrayList<>();

    // métodos para operaciones CRUD

    public UserController() {
    }

    @PostConstruct
    public void init() {
        users = new user();
        actualizarListaUsers();
    }

    public void agregarUser() {
        userBean.registrarUsuario(users);
        limpiar();
        actualizarListaUsers();
    }

    public void obtenerUser(Long id) {
        users = userBean.obtenerUser(id);
    }

    public void actualizarUser() {
        userBean.actualizarUser(users);
        limpiar();
        actualizarListaUsers();
    }

    public void eliminarUser() {
        userBean.eliminarBlog(users);
        limpiar();
        actualizarListaUsers();
    }

    public List<user> getListaUser() {
        return listaUser;
    }

    public void setListaUser(List<user> listaUser) {
        this.listaUser = listaUser;
    }

    private void actualizarListaUsers() {
        listaUser = userBean.obtenerTodosLosUser();
    }

    private void limpiar() {
        users = new user();
    }

    public user getUsers() {
        return users;
    }

    public void setUsers(user users) {
        users = users;
    }
}
