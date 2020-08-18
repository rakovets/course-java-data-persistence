package com.rakovets.course.datapersistence.dao;

import com.rakovets.course.datapersistence.dao.dao.ArtistDao;
import com.rakovets.course.datapersistence.dao.entity.Artist;

public class ArtistDaoExample {
    private static final ArtistDao artistDao = ArtistDao.getInstance();

    public static void main(String[] args) {
        Artist savedArtist = artistDao.save(new Artist("Scorpions"));
        Artist artistFromDb = artistDao.findById(savedArtist.getId());
        System.out.println(artistFromDb.equals(savedArtist));
    }
}
