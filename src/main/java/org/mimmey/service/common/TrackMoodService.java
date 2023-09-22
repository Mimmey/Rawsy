package org.mimmey.service.common;

import org.mimmey.entity.TrackMood;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface TrackMoodService {

    /**
     * The function that returns the track mood by its id
     *
     * @param id ID of the mood
     */
    TrackMood getMood(int id);

    /**
     * The function that returns the list of track moods
     */
    List<TrackMood> getMoods();
}
