package com.rakovets.course.datapersistence.dao.example;

import com.rakovets.course.datapersistence.dao.example.dao.ArtistDao;
import com.rakovets.course.datapersistence.dao.example.entity.Artist;

public class MusicStoreApp {
    private static final ArtistDao artistDao = ArtistDao.getInstance();

    public static void main(String[] args) {
        Artist savedArtist = artistDao.save(new Artist("Scorpions"));
        Artist artistFromDb = artistDao.findById(savedArtist.getId());
        System.out.println(artistFromDb.equals(savedArtist));
    }
}
