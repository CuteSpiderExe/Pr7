package com.example.Test.controllers;

import com.example.Test.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.GameRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/game")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GameController {


    @Autowired
    private GameRep GameRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Game> plane = GameRep.findAll();
        model.addAttribute("game",plane);
        return "game/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Game game)
    {
        model.addAttribute("game",new Game());
        return "game/add-game";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("game")
            @Valid Game newGame,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "game/add-game";


        GameRep.save(newGame);
        return "redirect:/game/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Game> GameList = GameRep.findByNameContains(name);
        model.addAttribute("game",GameList);
        return "game/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Game> game = GameRep.findById(id);
        ArrayList<Game> gameArrayList = new ArrayList<>();
        game.ifPresent(gameArrayList::add);
        model.addAttribute("game",gameArrayList);
        return "game/info-game";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Game game = GameRep.findById(id).orElseThrow();
        GameRep.delete(game);
        return "redirect:/game/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!GameRep.existsById(id))
        {
            return "redirect:/game/";
        }
        Optional<Game> game = GameRep.findById(id);
        ArrayList<Game> gameArrayList = new ArrayList<>();
        game.ifPresent(gameArrayList::add);
        model.addAttribute("game",gameArrayList.get(0));
        return "game/edit-game";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Game newGame,
            BindingResult bindingResult,
            Model model)
    {
        if(!GameRep.existsById(id))
        {
            return "redirect:/game";
        }
        if(bindingResult.hasErrors())
            return "game/edit-game";
        newGame.setId(id);
        GameRep.save(newGame);
        return "redirect:/game/";
    }

}