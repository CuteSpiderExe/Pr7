package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class    Atribut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @OneToMany(mappedBy = "atribut", fetch = FetchType.EAGER)
    private Collection<Dota> dotas;


    public Atribut(String name, Collection<Dota> dotas) {
        this.name = name;
        this.dotas = dotas;
    }

    public Atribut() {
    }

    public Collection<Dota> getDotas() {
        return dotas;
    }

    public void setDotas(Collection<Dota> dotas) {
        this.dotas = dotas;
    }

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

}
