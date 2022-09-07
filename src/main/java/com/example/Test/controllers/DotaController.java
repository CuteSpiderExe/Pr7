package com.example.Test.controllers;

import com.example.Test.models.Dota;
import com.example.Test.models.Plane;
import com.example.Test.repositories.DotaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/dota")
public class DotaController {


    @Autowired
    private DotaRep DotaRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Dota> plane = DotaRep.findAll();
        model.addAttribute("dota",plane);
        return "dota/index";
    }

    @GetMapping("/add")
    public String addView(Model model)
    {
        return "dota/add-dota";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("name") String name,
            @RequestParam("tip") String tip,
            @RequestParam("hp") Integer hp,
            @RequestParam("mana") Integer mana,
            @RequestParam("damage") Integer damage,
            Model model)
    {
        Dota dotaOne = new Dota(name,tip,hp,mana,damage);
        DotaRep.save(dotaOne);
        return "redirect:/dota/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Dota> DotaList = DotaRep.findByNameContains(name);
        model.addAttribute("dota",DotaList);
        return "dota/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Dota> dota = DotaRep.findById(id);
        ArrayList<Dota> dotaArrayList = new ArrayList<>();
        dota.ifPresent(dotaArrayList::add);
        model.addAttribute("dota",dotaArrayList);
        return "dota/info-dota";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Dota dota = DotaRep.findById(id).orElseThrow();
        DotaRep.delete(dota);
        return "redirect:/dota/";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!DotaRep.existsById(id)) {
            return "redirect:/dota/";
        }

        Optional<Dota> dota = DotaRep.findById(id);
        ArrayList<Dota> dotaArrayList = new ArrayList<>();
        dota.ifPresent(dotaArrayList::add);
        model.addAttribute("dota", dotaArrayList);
        return "dota/edit-dota";
    }
    @PostMapping("/edit/{id}")
    public String editDota(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("tip") String tip,
            @RequestParam("hp") Integer hp,
            @RequestParam("mana") Integer mana,
            @RequestParam("damage") Integer damage,
            Model model)
    {

        Dota dota = DotaRep.findById(id).orElseThrow();

        dota.setName(name);
        dota.setTip(tip);
        dota.setMana(hp);
        dota.setMana(mana);
        dota.setDamage(damage);

        DotaRep.save(dota);

        return "redirect:/dota/";
    }

}
