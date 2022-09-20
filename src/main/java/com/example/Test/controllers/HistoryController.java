package com.example.Test.controllers;


import com.example.Test.models.Game;
import com.example.Test.models.History;
import com.example.Test.models.Player;
import com.example.Test.models.Spos;
import com.example.Test.repositories.GameRep;
import com.example.Test.repositories.PlayerRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.HistoryRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/history")

public class HistoryController {


    @Autowired
    private HistoryRep HistoryRep;

    @Autowired
    private GameRep GameRep;

    @Autowired
    private PlayerRep PlayerRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<History> plane = HistoryRep.findAll();
        model.addAttribute("history",plane);
        return "history/index";
    }

    @GetMapping("/add")
    public String addView(Model model, History history)
    {
        Iterable<Game> game = GameRep.findAll();
        model.addAttribute("game",game);
        Iterable<Player> player = PlayerRep.findAll();
        model.addAttribute("player",player);
        model.addAttribute("history",new History());
        return "history/add-history";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("history")
            @Valid History newHistory,
            BindingResult bindingResult,
            Model model,@RequestParam String gm,@RequestParam String pl)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Game> game = GameRep.findAll();
            model.addAttribute("game",game);
            Iterable<Player> player = PlayerRep.findAll();
            model.addAttribute("player",player);
            return "history/add-history";
        }
        Game gm_find= GameRep.findByName(gm).get(0);
        newHistory.setGame(gm_find);
        Player pl_find= PlayerRep.findByName(pl).get(0);
        newHistory.setPlayer(pl_find);
        HistoryRep.save(newHistory);
        return "redirect:/history/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<History> HistoryList = HistoryRep.findByNameContains(name);
        model.addAttribute("history",HistoryList);
        return "history/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<History> history = HistoryRep.findById(id);
        ArrayList<History> historyArrayList = new ArrayList<>();
        history.ifPresent(historyArrayList::add);
        model.addAttribute("history",historyArrayList);
        return "history/info-history";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        History history = HistoryRep.findById(id).orElseThrow();
        HistoryRep.delete(history);
        return "redirect:/history/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!HistoryRep.existsById(id))
        {
            return "redirect:/history/";
        }
        Optional<History> history = HistoryRep.findById(id);
        ArrayList<History> historyArrayList = new ArrayList<>();
        history.ifPresent(historyArrayList::add);
        model.addAttribute("history",historyArrayList.get(0));
        Iterable<Game> game = GameRep.findAll();
        model.addAttribute("game",game);
        Iterable<Player> player = PlayerRep.findAll();
        model.addAttribute("player",player);
        return "history/edit-history";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid History newHistory,
            BindingResult bindingResult,
            Model model,@RequestParam String gm,@RequestParam String pl)
    {
        if(!HistoryRep.existsById(id))
        {
            return "redirect:/history";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Game> game = GameRep.findAll();
            model.addAttribute("game",game);
            Iterable<Player> player = PlayerRep.findAll();
            model.addAttribute("player",player);
            return "history/edit-history";
        }
        Game gm_find= GameRep.findByName(gm).get(0);
        newHistory.setGame(gm_find);
        Player pl_find= PlayerRep.findByName(pl).get(0);
        newHistory.setPlayer(pl_find);
        newHistory.setId(id);
        HistoryRep.save(newHistory);
        return "redirect:/history/";
    }

}