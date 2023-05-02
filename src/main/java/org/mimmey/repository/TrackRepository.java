package org.mimmey.repository;

import org.mimmey.entity.Track;

import java.util.List;

public interface TrackRepository {

    List<Track> getGlobalTrackList(String searchCriteria, long page, long unitsOnPage);

    List<Track> getSubscriptionsLastTrackList(long userId, String searchCriteria, long page, long unitsOnPage);

    Track getTrack(long trackId);

    void updateTrack(long trackId, Track newTrack);

    List<Track> getHottestPerWeek(long page, long unitsOnPage);

    List<Track> getNewPerWeek(long page, long unitsOnPage);
}
