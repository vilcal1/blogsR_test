package com.blogreaders.blogreaders.modelo;

import javax.persistence.*;


@Table(name="user", schema="worksoft")
@NamedQueries({@NamedQuery(name = "listarusers", query = "SELECT r FROM user r")})
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String username;
    @Column
    private String password;

    @Column
    private String Salt;

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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