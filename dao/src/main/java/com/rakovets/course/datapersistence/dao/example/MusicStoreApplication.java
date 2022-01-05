package com.rakovets.course.datapersistence.dao.example;

import com.rakovets.course.datapersistence.dao.example.dao.ArtistDao;
import com.rakovets.course.datapersistence.dao.example.dao.ArtistDaoJdbc;
import com.rakovets.course.datapersistence.dao.example.entity.Artist;

public class MusicStoreApplication {
    private static final ArtistDao artistDao = ArtistDaoJdbc.getInstance();

    public static void main(String[] args) {
        Artist newArtist = new Artist("Scorpions", "Germany");

        Artist savedArtist = artistDao.save(newArtist);
        Artist artistFromDb = artistDao.findByName(newArtist.getName());
        System.out.println(newArtist);
        System.out.println(savedArtist);
        System.out.println(artistFromDb);
    }
}
