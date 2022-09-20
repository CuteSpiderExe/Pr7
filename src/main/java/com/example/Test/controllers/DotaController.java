package com.example.Test.controllers;


import com.example.Test.models.Atribut;
import com.example.Test.models.Dota;
import com.example.Test.models.Items;
import com.example.Test.models.Spos;
import com.example.Test.repositories.AtributRep;
import com.example.Test.repositories.ItemsRep;
import com.example.Test.repositories.SposRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.DotaRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/dota")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DotaController {

    @Autowired
    private DotaRep DotaRep;
    @Autowired
    private AtributRep AtributRep;
    @Autowired
    private ItemsRep ItemsRep;

    @Autowired
    private SposRep SposRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Dota> plane = DotaRep.findAll();
        model.addAttribute("dota",plane);
        return "dota/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Dota dota)
    {
        Iterable<Atribut> atribut = AtributRep.findAll();
        model.addAttribute("atribut",atribut);
        Iterable<Items> items = ItemsRep.findAll();
        model.addAttribute("items",items);
        Iterable<Spos> spos = SposRep.findAll();
        model.addAttribute("ssss",spos);
        model.addAttribute("dota",new Dota());

        return "dota/add-dota";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("dota")
            @Valid Dota newDota,
            BindingResult bindingResult,
            Model model,@RequestParam String atr,@RequestParam String itm,@RequestParam String sps)
    {
        if(bindingResult.hasErrors()) {
            Iterable<Atribut> atribut = AtributRep.findAll();
            model.addAttribute("atribut",atribut);
            Iterable<Items> items = ItemsRep.findAll();
            model.addAttribute("items",items);
            Iterable<Spos> spos = SposRep.findAll();
            model.addAttribute("ssss",spos);
            model.addAttribute("dota",newDota);
            return "dota/add-dota";
        }
        Atribut atr_find= AtributRep.findByName(atr).get(0);
        newDota.setAtribut(atr_find);
        Items itm_find= ItemsRep.findByName(itm).get(0);
        newDota.setItems(itm_find);
        Spos sps_find= SposRep.findByName(sps).get(0);
        newDota.setSpos(sps_find);

        DotaRep.save(newDota);
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
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!DotaRep.existsById(id))
        {
            return "redirect:/dota/";
        }
        Optional<Dota> dota = DotaRep.findById(id);
        ArrayList<Dota> dotaArrayList = new ArrayList<>();
        dota.ifPresent(dotaArrayList::add);
        model.addAttribute("dota",dotaArrayList.get(0));
        Iterable<Atribut> atribut = AtributRep.findAll();
        model.addAttribute("atribut",atribut);
        Iterable<Items> items = ItemsRep.findAll();
        model.addAttribute("items",items);
        Iterable<Spos> spos = SposRep.findAll();
        model.addAttribute("ssss",spos);
        return "dota/edit-dota";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Dota newDota,
            BindingResult bindingResult,
            Model model,@RequestParam String atr,@RequestParam String itm,@RequestParam String sps) {
        if (!DotaRep.existsById(id)) {
            return "redirect:/dota";
        }
        if (bindingResult.hasErrors())
        {
            Iterable<Atribut> atribut = AtributRep.findAll();
            model.addAttribute("atribut",atribut);
            Iterable<Items> items = ItemsRep.findAll();
            model.addAttribute("items",items);
            Iterable<Spos> spos = SposRep.findAll();
            model.addAttribute("ssss",spos);
            model.addAttribute("dota",newDota);
            return "dota/edit-dota";
    }
        Atribut atr_find= AtributRep.findByName(atr).get(0);
        newDota.setAtribut(atr_find);
        Items itm_find= ItemsRep.findByName(itm).get(0);
        newDota.setItems(itm_find);
        Spos sps_find= SposRep.findByName(sps).get(0);
        newDota.setSpos(sps_find);
            newDota.setId(id);
            DotaRep.save(newDota);
            return "redirect:/dota/";

    }

}
