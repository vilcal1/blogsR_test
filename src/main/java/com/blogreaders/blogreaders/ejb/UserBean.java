package com.blogreaders.blogreaders.ejb;


import com.blogreaders.blogreaders.modelo.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import com.blogreaders.blogreaders.modelo.user;
import org.apache.commons.codec.digest.DigestUtils;

@Stateless
public class UserBean implements Serializable {
    private String username;
    private String password;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void registrarUsuario(user usuario) {
        String salt = generarSalt();
        String passwordHash = cifrarConSalt(usuario.getPassword(), salt);

        usuario.setSalt(salt);
        usuario.setPassword(passwordHash);

        entityManager.merge(usuario);
    }

    public user autenticarUsuario(String username, String password) {
        user usuario = obtenerUsuarioPorNombre(username);

        if (usuario != null && validarCredenciales(password, usuario.getPassword(), usuario.getSalt())) {
            return usuario;
        }

        return null; // Usuario no encontrado o credenciales inválidas
    }

    private user obtenerUsuarioPorNombre(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM user u WHERE u.username = :username", user.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Usuario no encontrado
        }
    }

    private String generarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String cifrarConSalt(String password, String salt) {
        String passwordWithSalt = password + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(passwordWithSalt.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar la contraseña.", e);
        }
    }

    private boolean validarCredenciales(String inputPassword, String storedPasswordHash, String salt) {
        String inputPasswordHash = cifrarConSalt(inputPassword, salt);
        return inputPasswordHash.equals(storedPasswordHash);
    }


/******logica transaccional */

    public user obtenerUser(Long id) {
        return entityManager.find(user.class, id);
    }

    public List<user> obtenerTodosLosUser() {
        return entityManager.createNamedQuery("listarusers", user.class).getResultList();
    }
    @Transactional
    public user actualizarUser(user User) {
        entityManager.merge(User);
        entityManager.flush();
        return User;
    }

    @Transactional
    public void eliminarBlog(user User) {
        User = entityManager.find(com.blogreaders.blogreaders.modelo.user.class, User.getId());
        if (User != null) {
            entityManager.remove(User);
        }
    }

    /**************************************/


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




