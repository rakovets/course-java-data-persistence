package by.rakovets.course.datapersistence.example.dao.dao;

import by.rakovets.course.datapersistence.example.dao.entity.Artist;

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
