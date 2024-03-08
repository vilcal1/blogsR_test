package com.blogreaders.blogreaders.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name="reader", schema="worksoft")
@NamedQueries({@NamedQuery(name = "encontrarAll", query = "SELECT r FROM Reader r")
              ,@NamedQuery(name = "mntor", query = "SELECT r FROM Reader r")
, @NamedQuery(name = "readerConBlogs", query = "SELECT r FROM Reader r JOIN FETCH r.blogs WHERE r.id = :id")})
@Entity
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },  fetch = FetchType.EAGER)
    @JoinTable(
            schema = "worksoft",
            name = "blog_reader",
            joinColumns = @JoinColumn(name = "r_id"),
            inverseJoinColumns = @JoinColumn(name = "b_id")
    )
    private Set<com.blogreaders.blogreaders.modelo.blogs> blogs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<com.blogreaders.blogreaders.modelo.blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<com.blogreaders.blogreaders.modelo.blogs> blogs) {
        this.blogs = blogs;
    }
}