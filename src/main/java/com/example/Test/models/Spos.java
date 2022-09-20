package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class    Spos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @NotNull(message = "Поле не может быть пустым")
    @Min(message = "Количество не может быть отрицательным",value = 0)
    Integer damage, mana;
    @OneToMany(mappedBy = "spos", fetch = FetchType.EAGER)
    private Collection<Dota> dotas;
    public Spos(String name, Integer damage,  Integer mana,Collection<Dota> dotas) {
        this.name = name;
        this.damage = damage;
        this.mana = mana;
        this.dotas = dotas;

    }

    public Spos() {
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

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public Collection<Dota> getDotas() {
        return dotas;
    }

    public void setDotas(Collection<Dota> dotas) {
        this.dotas = dotas;
    }
}