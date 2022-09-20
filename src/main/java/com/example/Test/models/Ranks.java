package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class    Ranks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @NotNull(message = "Поле не может быть пустым")
    @Min(message = "Количество не может быть отрицательным",value = 0)
    Integer kol;
    @OneToMany(mappedBy = "ranks", fetch = FetchType.EAGER)
    private Collection<Player> player;
    public Ranks(String name, Integer kol,Collection<Player> player) {
        this.name = name;
        this.kol = kol;
        this.player = player;
    }

    public Ranks() {
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

    public Integer getKol() {
        return kol;
    }

    public void setKol(Integer kol) {
        this.kol = kol;
    }

    public Collection<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Collection<Player> player) {
        this.player = player;
    }
}