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
     */
    TrackGenre getGenre(int id);

    /**
     * The function that returns the list of track genres
     */
    List<TrackGenre> getGenres();
}
