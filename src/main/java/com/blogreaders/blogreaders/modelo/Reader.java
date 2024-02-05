package com.blogreaders.blogreaders.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name="reader", schema="worksoft")
@NamedQueries({@NamedQuery(name = "encontrarAll", query = "SELECT r FROM Reader r")
              ,@NamedQuery(name = "mntor", query = "SELECT r FROM Reader r")})
@Entity
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int readersId;

    @Column(name = "name")
    private String readersName;

    @ManyToMany(mappedBy = "readers")
    private Set<com.blogreaders.blogreaders.modelo.blogs> blogs = new HashSet<>();

    public int getReadersId() {
        return readersId;
    }

    public void setReadersId(int readersId) {
        this.readersId = readersId;
    }


    public String getReadersName() {
        return readersName;
    }

    public void setReadersName(String readersName) {
        this.readersName = readersName;
    }
}