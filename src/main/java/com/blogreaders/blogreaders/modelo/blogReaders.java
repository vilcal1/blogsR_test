package com.blogreaders.blogreaders.modelo;

import javax.persistence.*;
import java.io.Serializable;


@Table(name="blogreaders", schema="worksoft")
@NamedQueries({@NamedQuery(name = "findAll", query = "SELECT r FROM blogReaders r")})
@Entity
public class blogReaders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    /*@Column(name = "b_id")
    @JoinColumn(name = "idBlog, referencedColumnName = "b_id", nullable = false)
    @ManyToOne(optional = false)
    private blogs idBlog;

    @Column(name = "r_id")
    @JoinColumn(name = "idReader", referencedColumnName = "r_id", nullable = false)
    @ManyToOne(optional = false)
    private Reader idReader;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public Reader getIdReader() {
        return idReader;
    }



    public void setIdReader(Reader idReader) {
        this.idReader = idReader;
    }*/


}
