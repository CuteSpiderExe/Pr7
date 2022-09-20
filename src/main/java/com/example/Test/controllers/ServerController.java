package com.example.Test.controllers;

import com.example.Test.models.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Test.repositories.ServerRep;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/server")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ServerController {


    @Autowired
    private ServerRep ServerRep;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Server> plane = ServerRep.findAll();
        model.addAttribute("server",plane);
        return "server/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Server server)
    {
        model.addAttribute("server",new Server());
        return "server/add-server";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("server")
            @Valid Server newServer,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "server/add-server";


        ServerRep.save(newServer);
        return "redirect:/server/";
    }

    @GetMapping("/search")
    public String add(
            @RequestParam("name") String name,
            Model model)
    {
        List<Server> ServerList = ServerRep.findByNameContains(name);
        model.addAttribute("server",ServerList);
        return "server/index";

    }
    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Server> server = ServerRep.findById(id);
        ArrayList<Server> serverArrayList = new ArrayList<>();
        server.ifPresent(serverArrayList::add);
        model.addAttribute("server",serverArrayList);
        return "server/info-server";
    }
    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Server server = ServerRep.findById(id).orElseThrow();
        ServerRep.delete(server);
        return "redirect:/server/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!ServerRep.existsById(id))
        {
            return "redirect:/server/";
        }
        Optional<Server> server = ServerRep.findById(id);
        ArrayList<Server> serverArrayList = new ArrayList<>();
        server.ifPresent(serverArrayList::add);
        model.addAttribute("server",serverArrayList.get(0));
        return "server/edit-server";
    }
    @PostMapping("/edit/{id}")
    public String editGames(
            @PathVariable("id") Long id,
            @ModelAttribute("games") @Valid Server newServer,
            BindingResult bindingResult,
            Model model)
    {
        if(!ServerRep.existsById(id))
        {
            return "redirect:/server";
        }
        if(bindingResult.hasErrors())
            return "server/edit-server";
        newServer.setId(id);
        ServerRep.save(newServer);
        return "redirect:/server/";
    }

}
