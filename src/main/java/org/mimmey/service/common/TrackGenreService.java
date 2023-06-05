package org.mimmey.service.common;

import org.mimmey.entity.TrackGenre;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface TrackGenreService {

    /**
     * The function that returns the track genre by its id
     *
     * @param id ID of the genre
     * @return the track genre with the given ID
     */
    TrackGenre getGenre(int id);

    /**
     * The function that returns the list of track genres
     *
     * @return list of all track genres in the DB
     */
    List<TrackGenre> getGenres();
}
