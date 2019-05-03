package com.EftimieVlad.Assignment.controller;

import com.EftimieVlad.Assignment.entity.RSS;
import com.EftimieVlad.Assignment.service.RSSservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class RSScontroller {
    @Autowired
    private RSSservice rsSservice;

    @GetMapping("/all")
    public List<RSS> getAll () {
        return this.rsSservice.getAll();
    }
    @GetMapping("/investigations/{id}")
    public RSS getInvestigations(@PathVariable int id) {
        return this.rsSservice.getById(id);
    }

}
