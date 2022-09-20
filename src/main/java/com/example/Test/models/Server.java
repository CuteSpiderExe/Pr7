package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class    Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name, loc;
    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER)
    private Collection<Player> player;

    public Server(String name,String loc ) {
        this.name = name;
        this.loc = loc;
        this.player = player;
    }

    public Server() {
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

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Collection<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Collection<Player> player) {
        this.player = player;
    }
}
