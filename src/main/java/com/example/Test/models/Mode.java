package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class    Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @OneToMany(mappedBy = "mode", fetch = FetchType.EAGER)
    private Collection<Player> player;

    public Mode(String name,Collection<Player> player) {
        this.name = name;
        this.player = player;

    }

    public Mode() {
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

    public Collection<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Collection<Player> player) {
        this.player = player;
    }
}
