package com.blogreaders.blogreaders.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name="reader", schema="worksoft")
@NamedQueries({@NamedQuery(name = "encontrarAll", query = "SELECT r FROM Reader r")
              ,@NamedQuery(name = "mntor", query = "SELECT r FROM Reader r")
, @NamedQuery(name = "readerConBlogs", query = "SELECT r FROM Reader r JOIN FETCH r.blogs WHERE r.readersId = :id")})
@Entity
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long readersId;

    @Column(name = "name")
    private String readersName;

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

    public Long getReadersId() {
        return readersId;
    }

    public void setReadersId(Long readersId) {
        this.readersId = readersId;
    }


    public String getReadersName() {
        return readersName;
    }

    public void setReadersName(String readersName) {
        this.readersName = readersName;
    }

    public Set<com.blogreaders.blogreaders.modelo.blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<com.blogreaders.blogreaders.modelo.blogs> blogs) {
        this.blogs = blogs;
    }
}