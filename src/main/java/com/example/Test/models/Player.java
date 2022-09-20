package com.example.Test.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class    Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    String name;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Dota dota;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Mode mode;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Ranks ranks;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Server server;
    @OneToOne(optional = true, cascade = CascadeType.DETACH)
    private History history;
    public Player(String name,Dota dota,History history,Mode mode,Ranks ranks,Server server) {
        this.name = name;

        this.dota = dota;
        this.history = history;
        this.mode = mode;
        this.ranks = ranks;
        this.server = server;
    }

    public Player() {
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

    public Dota getDota() {
        return dota;
    }

    public void setDota(Dota dota) {
        this.dota = dota;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Ranks getRanks() {
        return ranks;
    }

    public void setRanks(Ranks ranks) {
        this.ranks = ranks;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}