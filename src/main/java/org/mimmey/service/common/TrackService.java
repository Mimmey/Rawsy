package org.mimmey.service.common;

import org.mimmey.entity.Track;
import org.mimmey.utils.TrackFilter;
import org.mimmey.utils.TrackSortingTypes;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface TrackService {

    /**
     * The function that returns the page of the list of all tracks in the database that include
     * searching string and match applied filters and sorting type
     *
     * @param filters      list of filters that need to be applied
     * @param sortingType  sorting type that needs to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks that include searching string and
     * match applied filters and sorting types
     */
    Page<Track> getGlobalTracks(List<TrackFilter> filters,
                                TrackSortingTypes sortingType,
                                String searchString,
                                int page,
                                int unitsOnPage);

    /**
     * The function that returns the page of the list of tracks that was published not later than week ago and
     * have the highest rating. Sorted by rating in descending order. All tracks include searching string and match applied filters.
     *
     * @param filters      list of filters that need to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks that was published not later than week ago and
     * have the highest rating. Sorted by rating in descending order. All tracks include searching string and match applied filters.
     */
    Page<Track> getHottestPerWeek(List<TrackFilter> filters,
                                  String searchString,
                                  int page,
                                  int unitsOnPage);

    /**
     * The function that returns the page of the list of tracks that was published not later than week ago.
     * Sorted by publication timestamp in descending order.
     * All tracks include searching string and match applied filters and sorting type
     *
     * @param filters      list of filters that need to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks that was published not later than week ago.
     * Sorted by publication timestamp in descending order.
     * All tracks include searching string and match applied filters and sorting type
     */
    Page<Track> getNewPerWeek(List<TrackFilter> filters,
                              String searchString,
                              int page,
                              int unitsOnPage);

    /**
     * The function that returns the track got by ID
     *
     * @param id ID of the track
     * @return the track with the given ID
     */
    Track getTrack(long id);

    /**
     * The function that returns the track audio preview
     *
     * @param id ID of the track
     */
    byte[] getTrackPreview(long id);
}
