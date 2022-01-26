package by.rakovets.course.datapersistence.example.dao;

import by.rakovets.course.datapersistence.example.dao.dao.ArtistDao;
import by.rakovets.course.datapersistence.example.dao.dao.ArtistDaoJdbc;
import by.rakovets.course.datapersistence.example.dao.entity.Artist;

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
