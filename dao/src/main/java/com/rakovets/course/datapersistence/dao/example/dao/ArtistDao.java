package com.rakovets.course.datapersistence.dao.example.dao;

import com.rakovets.course.datapersistence.dao.example.entity.Artist;

public interface ArtistDao {
    Artist save(Artist artist);

    Artist findById(long id);

    Artist findByName(String name);
}
