package com.example.Test.controllers;

import com.example.Test.models.*;
import com.example.Test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/player")
public class PlayerController {


    @Autowired
    private PlayerRep PlayerRep;

    @Autowired
    private DotaRep DotaRep;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private ModeRep ModeRep;

    @Autowired
    private RanksRep RanksRep;

    @Autowired
    private ServerRep ServerRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Player> plane = PlayerRep.findAll();
        model.addAttribute("player",plane);
        return "player/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Player player)
    {
        Iterable<Dota> dota = DotaRep.findAll();
        model.addAttribute("dota",dota);
        Iterable<Mode> mode = ModeRep.findAll();
        model.addAttribute("mode",mode);
        Iterable<Ranks> ranks = RanksRep.findAll();
        model.addAttribute("ranks",ranks);
        Iterable<Server> server = ServerRep.findAll();
        model.addAttribute("server",server);
        model.addAttribute("player",new Player());
        return "player/add-player";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("player")
            @Valid Player newPlayer,
            BindingResult bindingResult,
            Model model,@RequestParam String dot,@RequestParam String mod,@RequestParam String rank,@RequestParam String ser)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Dota> dota = DotaRep.findAll();
            model.addAttribute("dota",dota);
            Iterable<Mode> mode = ModeRep.findAll();
            model.addAttribute("mode",mode);
            Iterable<Ranks> ranks = RanksRep.findAll();
            model.addAttribute("ranks",ranks);
            Iterable<Server> server = ServerRep.findAll();
            model.addAttribute("server",server);
            return "player/add-player";
        }
        Dota dot_find= DotaRep.findByName(dot).get(0);
        newPlayer.setDota(dot_find);
        Mode mod_find= ModeRep.findByName(mod).get(0);
        newPlayer.setMode(mod_find);
        Ranks rank_find= RanksRep.findByName(rank).get(0);
        newPlayer.setRanks(rank_find);
        Server ser_find= ServerRep.findByName(ser).get(0);
        newPlayer.setServer(ser_find);
        PlayerRep.save(newPlayer);
        return "redirect:/player/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Player> PlayerList = PlayerRep.findByNameContains(name);
        model.addAttribute("player",PlayerList);
        return "player/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Player> player = PlayerRep.findById(id);
        ArrayList<Player> playerArrayList = new ArrayList<>();
        player.ifPresent(playerArrayList::add);
        model.addAttribute("player",playerArrayList);
        return "player/info-player";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Player player = PlayerRep.findById(id).orElseThrow();
        PlayerRep.delete(player);
        return "redirect:/player/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!PlayerRep.existsById(id))
        {
            return "redirect:/player/";
        }
        Optional<Player> player = PlayerRep.findById(id);
        ArrayList<Player> playerArrayList = new ArrayList<>();
        player.ifPresent(playerArrayList::add);
        Iterable<Dota> dota = DotaRep.findAll();
        model.addAttribute("dota",dota);
        Iterable<Mode> mode = ModeRep.findAll();
        model.addAttribute("mode",mode);
        Iterable<Ranks> ranks = RanksRep.findAll();
        model.addAttribute("ranks",ranks);
        Iterable<Server> server = ServerRep.findAll();
        model.addAttribute("server",server);
        model.addAttribute("player",playerArrayList.get(0));
        return "player/edit-player";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Player newPlayer,
            BindingResult bindingResult,
            Model model,@RequestParam String dot,@RequestParam String mod,@RequestParam String rank,@RequestParam String ser)
    {
        if(!PlayerRep.existsById(id))
        {
            return "redirect:/player";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Dota> dota = DotaRep.findAll();
            model.addAttribute("dota",dota);
            Iterable<Mode> mode = ModeRep.findAll();
            model.addAttribute("mode",mode);
            Iterable<Ranks> ranks = RanksRep.findAll();
            model.addAttribute("ranks",ranks);
            Iterable<Server> server = ServerRep.findAll();
            model.addAttribute("server",server);
            return "player/edit-player";
        }
        Dota dot_find= DotaRep.findByName(dot).get(0);
        newPlayer.setDota(dot_find);
        Mode mod_find= ModeRep.findByName(mod).get(0);
        newPlayer.setMode(mod_find);
        Ranks rank_find= RanksRep.findByName(rank).get(0);
        newPlayer.setRanks(rank_find);
        Server ser_find= ServerRep.findByName(ser).get(0);
        newPlayer.setServer(ser_find);
        newPlayer.setId(id);


        PlayerRep.save(newPlayer);
        return "redirect:/player/";
    }

}