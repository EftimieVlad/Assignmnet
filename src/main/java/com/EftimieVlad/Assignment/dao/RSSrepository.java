package com.EftimieVlad.Assignment.dao;


import com.EftimieVlad.Assignment.entity.RSS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RSSrepository extends JpaRepository<RSS, Integer> {

}
