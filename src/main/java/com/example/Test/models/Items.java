package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class    Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @NotNull(message = "Поле не может быть пустым")
    @Min(message = "Количество не может быть отрицательным",value = 0)
    Integer damage;
    @OneToMany(mappedBy = "items", fetch = FetchType.EAGER)
    private Collection<Dota> dotas;

    public Items(String name, Integer damage,Collection<Dota> dotas) {
        this.name = name;
        this.damage = damage;
        this.dotas = dotas;
    }

    public Items() {
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

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Collection<Dota> getDotas() {
        return dotas;
    }

    public void setDotas(Collection<Dota> dotas) {
        this.dotas = dotas;
    }
}
