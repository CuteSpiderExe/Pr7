package com.example.Test.controllers;

import com.example.Test.models.Ranks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.RanksRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/ranks")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RanksController {


    @Autowired
    private RanksRep RanksRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Ranks> plane = RanksRep.findAll();
        model.addAttribute("ranks",plane);
        return "ranks/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Ranks ranks)
    {
        model.addAttribute("ranks",new Ranks());
        return "ranks/add-ranks";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("ranks")
            @Valid Ranks newRanks,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "ranks/add-ranks";


        RanksRep.save(newRanks);
        return "redirect:/ranks/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Ranks> RanksList = RanksRep.findByNameContains(name);
        model.addAttribute("ranks",RanksList);
        return "ranks/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Ranks> ranks = RanksRep.findById(id);
        ArrayList<Ranks> ranksArrayList = new ArrayList<>();
        ranks.ifPresent(ranksArrayList::add);
        model.addAttribute("ranks",ranksArrayList);
        return "ranks/info-ranks";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Ranks ranks = RanksRep.findById(id).orElseThrow();
        RanksRep.delete(ranks);
        return "redirect:/ranks/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!RanksRep.existsById(id))
        {
            return "redirect:/ranks/";
        }
        Optional<Ranks> ranks = RanksRep.findById(id);
        ArrayList<Ranks> ranksArrayList = new ArrayList<>();
        ranks.ifPresent(ranksArrayList::add);
        model.addAttribute("ranks",ranksArrayList.get(0));
        return "ranks/edit-ranks";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Ranks newRanks,
            BindingResult bindingResult,
            Model model)
    {
        if(!RanksRep.existsById(id))
        {
            return "redirect:/ranks";
        }
        if(bindingResult.hasErrors())
            return "ranks/edit-ranks";
        newRanks.setId(id);
        RanksRep.save(newRanks);
        return "redirect:/ranks/";
    }

}