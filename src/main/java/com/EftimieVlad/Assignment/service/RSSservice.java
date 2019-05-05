package com.EftimieVlad.Assignment.service;


import com.EftimieVlad.Assignment.dao.RSSrepository;
import com.EftimieVlad.Assignment.entity.RSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RSSservice {

    @Autowired
    private RSSrepository rsSrepository;


    public List<RSS> getAll () {       return this.rsSrepository.findAll();    }
    public void save (RSS rss) {
        this.rsSrepository.save(rss);
    }




}
