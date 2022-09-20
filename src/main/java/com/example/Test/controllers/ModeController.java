package com.example.Test.controllers;

import com.example.Test.models.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.ModeRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/mode")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ModeController {


    @Autowired
    private ModeRep ModeRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Mode> plane = ModeRep.findAll();
        model.addAttribute("mode",plane);
        return "mode/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Mode mode)
    {
        model.addAttribute("mode",new Mode());
        return "mode/add-mode";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("mode")
            @Valid Mode newMode,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "mode/add-mode";


        ModeRep.save(newMode);
        return "redirect:/mode/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Mode> ModeList = ModeRep.findByNameContains(name);
        model.addAttribute("mode",ModeList);
        return "Mode/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Mode> mode = ModeRep.findById(id);
        ArrayList<Mode> modeArrayList = new ArrayList<>();
        mode.ifPresent(modeArrayList::add);
        model.addAttribute("mode",modeArrayList);
        return "mode/info-mode";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Mode mode = ModeRep.findById(id).orElseThrow();
        ModeRep.delete(mode);
        return "redirect:/mode/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!ModeRep.existsById(id))
        {
            return "redirect:/mode/";
        }
        Optional<Mode> mode = ModeRep.findById(id);
        ArrayList<Mode> modeArrayList = new ArrayList<>();
        mode.ifPresent(modeArrayList::add);
        model.addAttribute("mode",modeArrayList.get(0));
        return "mode/edit-mode";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Mode newMode,
            BindingResult bindingResult,
            Model model)
    {
        if(!ModeRep.existsById(id))
        {
            return "redirect:/mode";
        }
        if(bindingResult.hasErrors())
            return "mode/edit-mode";
        newMode.setId(id);
        ModeRep.save(newMode);
        return "redirect:/mode/";
    }

}