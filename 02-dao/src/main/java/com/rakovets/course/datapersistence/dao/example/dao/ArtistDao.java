package com.rakovets.course.datapersistence.dao.example.dao;

import com.rakovets.course.datapersistence.dao.example.entity.Artist;

public interface ArtistDao {
    /**
     * Сохранение сущности Artist в DataSource
     *
     * @param artist сущность {@link Artist}
     * @return сущность {@link Artist}
     */
    Artist save(Artist artist);

    Artist findById(long id);

    Artist findByName(String name);
}
