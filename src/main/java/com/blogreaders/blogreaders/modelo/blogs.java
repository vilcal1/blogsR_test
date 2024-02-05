package com.blogreaders.blogreaders.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name="blogs", schema="worksoft")
@NamedQueries({@NamedQuery(name = "buscarTodo", query = "SELECT r FROM blogs r")
               ,@NamedQuery(name = "mntob", query = "SELECT r FROM blogs r"),
    @NamedQuery(name = "blogConReaders", query = "SELECT r FROM blogs r JOIN FETCH r.readers WHERE r.id = :id")})
@Entity
public class blogs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "blogs", fetch = FetchType.EAGER)
    private Set<Reader> readers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }
}
