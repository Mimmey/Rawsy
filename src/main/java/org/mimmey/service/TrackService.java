package org.mimmey.service;

import org.mimmey.dto.TrackDto;
import org.mimmey.entity.Filter;
import org.mimmey.entity.SortingType;

import java.util.List;

/**
 * @author Olga Motyleva
 * */
public interface TrackService {

    /**
     * The function that returns the page of the list of all tracks in the database that include
     * searching string and match applied filters and sorting type
     * @param filterList list of filters that need to be applied
     * @param sortingType sorting type that needs to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page index of track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks that include searching string and
     * match applied filters and sorting types
     */
    List<TrackDto> getGlobalTrackList(List<? extends Filter<?>> filterList,
                                      SortingType sortingType,
                                      String searchString,
                                      long page,
                                      long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks authored by users the currently authorized user is subscribed on.
     * All tracks include searching string and match applied filters and sorting type
     * @param filterList list of filters that need to be applied
     * @param sortingType sorting type that needs to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page index of track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks authored by users the currently authorized user is subscribed on.
     * All tracks include searching string and match applied filters and sorting type
     */
    List<TrackDto> getSubscriptionsLastTrackList(List<? extends Filter<?>> filterList,
                                                 SortingType sortingType,
                                                 String searchString,
                                                 long page,
                                                 long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks that was published not later than week ago and
     * have the highest rating. Sorted by rating in descending order. All tracks include searching string and match applied filters.
     * @param filterList list of filters that need to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page index of track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks that was published not later than week ago and
     * have the highest rating. Sorted by rating in descending order. All tracks include searching string and match applied filters.
     */
    List<TrackDto> getHottestPerWeek(List<? extends Filter<?>> filterList,
                                     String searchString,
                                     long page,
                                     long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks that was published not later than week ago.
     * Sorted by publication timestamp in descending order.
     * All tracks include searching string and match applied filters and sorting type
     * @param filterList list of filters that need to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page index of track list's page
     * @param unitsOnPage number of tracks per one page
     * @return the page of the list of tracks that was published not later than week ago.
     * Sorted by publication timestamp in descending order.
     * All tracks include searching string and match applied filters and sorting type
     */
    List<TrackDto> getNewPerWeek(List<? extends Filter<?>> filterList,
                                 String searchString,
                                 long page,
                                 long unitsOnPage);

    /**
     * The function that returns the track got by ID
     * @param trackId ID of the track
     */
    TrackDto getTrack(long trackId);

    /**
     * The function that changes the cost of the given track
     * @param trackId ID of the track
     * @param newCost new cost of the given track
     */
    void changeCost(long trackId, long newCost);

    /**
     * The function that changes the given track
     * @param trackId ID of the track
     * @param newTrackInfo new track information
     */
    void changeTrack(long trackId, TrackDto newTrackInfo);

    /**
     * The function that downloads the archive with a multitrack of the given track
     * @param trackId ID of the track
     */
    void downloadMultitrack(long trackId);
}
