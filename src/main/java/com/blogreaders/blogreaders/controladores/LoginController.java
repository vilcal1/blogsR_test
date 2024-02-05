package com.blogreaders.blogreaders.controladores;

import com.blogreaders.blogreaders.ejb.UserBean;
import com.blogreaders.blogreaders.modelo.user;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @EJB
    private UserBean userService;
    private String username;
    private String password;

    private user usuario;

    @PostConstruct
    public void init() {
    }

    public String iniciarSesion() {
        usuario = userService.autenticarUsuario(username, password);
        if (usuario != null) {
            // Almacenar el usuario en la sesión para que pueda ser utilizado en otras partes de la aplicación
            // Ademas para que el filtro de autenticación pueda redirigir a la página de inicio de sesión si el usuario no está autenticado.
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
            return "/worksoft/index.xhtml?faces-redirect=true"; // Redirige a la página de inicio después de iniciar sesión
        } else {
            // Manejar el fallo de autenticación, por ejemplo, mostrar un mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de inicio de sesión", "Credenciales inválidas"));
            return null;
        }
    }

    public String cerrarSesion() {
        // Limpiar la sesión y redirigir a la página de inicio de sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
