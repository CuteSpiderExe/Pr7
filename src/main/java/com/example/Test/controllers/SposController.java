package com.example.Test.controllers;

import com.example.Test.models.Spos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.SposRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/spos")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SposController {


    @Autowired
    private SposRep SposRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Spos> plane = SposRep.findAll();
        model.addAttribute("spos",plane);
        return "spos/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Spos spos)
    {
        model.addAttribute("spos",new Spos());
        return "spos/add-spos";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("spos")
            @Valid Spos newSpos,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "spos/add-spos";


        SposRep.save(newSpos);
        return "redirect:/spos/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Spos> SposList = SposRep.findByNameContains(name);
        model.addAttribute("spos",SposList);
        return "spos/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Spos> spos = SposRep.findById(id);
        ArrayList<Spos> sposArrayList = new ArrayList<>();
        spos.ifPresent(sposArrayList::add);
        model.addAttribute("spos",sposArrayList);
        return "spos/info-spos";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Spos spos = SposRep.findById(id).orElseThrow();
        SposRep.delete(spos);
        return "redirect:/spos/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!SposRep.existsById(id))
        {
            return "redirect:/spos/";
        }
        Optional<Spos> spos = SposRep.findById(id);
        ArrayList<Spos> sposArrayList = new ArrayList<>();
        spos.ifPresent(sposArrayList::add);
        model.addAttribute("spos",sposArrayList.get(0));
        return "spos/edit-spos";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Spos newSpos,
            BindingResult bindingResult,
            Model model)
    {
        if(!SposRep.existsById(id))
        {
            return "redirect:/spos";
        }
        if(bindingResult.hasErrors())
            return "spos/edit-spos";
        newSpos.setId(id);
        SposRep.save(newSpos);
        return "redirect:/spos/";
    }
}
