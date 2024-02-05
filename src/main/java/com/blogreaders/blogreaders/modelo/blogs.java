package com.blogreaders.blogreaders.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name="blogs", schema="worksoft")
@NamedQueries({@NamedQuery(name = "buscarTodo", query = "SELECT r FROM blogs r")
               ,@NamedQuery(name = "mntob", query = "SELECT r FROM blogs r")})
@Entity
public class blogs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "blog_reader",
            joinColumns = @JoinColumn(name = "r_id"),
            inverseJoinColumns = @JoinColumn(name = "b_id")
    )
    private Set<Reader> readers = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
