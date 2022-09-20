package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class    Dota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @NotNull(message = "Поле не может быть пустым")
    @Min(message = "Количество не может быть отрицательным",value = 0)
    Integer hp , mana, damage;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Atribut atribut;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Items items;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Spos spos;
    @OneToMany(mappedBy = "dota", fetch = FetchType.EAGER)
    private Collection<Player> player;

    public Dota(String name, Integer hp, Integer mana, Integer damage, Atribut atribut,Items items,Spos spos,Collection<Player> player) {
        this.name = name;

        this.hp = hp;
        this.mana = mana;
        this.damage = damage;
        this.atribut = atribut;
        this.items = items;
        this.spos = spos;
        this.player = player;
    }

    public Dota() {
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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Atribut getAtribut() {
        return atribut;
    }

    public void setAtribut(Atribut atribut) {
        this.atribut = atribut;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Spos getSpos() {
        return spos;
    }

    public void setSpos(Spos spos) {
        this.spos = spos;
    }

    public Collection<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Collection<Player> player) {
        this.player = player;
    }
}
