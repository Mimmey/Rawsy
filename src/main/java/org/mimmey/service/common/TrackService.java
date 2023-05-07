package org.mimmey.service.common;

import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.utils.Filter;
import org.mimmey.utils.SortingType;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface TrackService {

    /**
     * The function that returns the page of the list of all tracks in the database that include
     * searching string and match applied filters and sorting type
     *
     * @param filterList   list of filters that need to be applied
     * @param sortingType  sorting type that needs to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks that include searching string and
     * match applied filters and sorting types
     */
    List<TrackCommonDto> getGlobalTrackList(List<? extends Filter<?>> filterList,
                                            SortingType sortingType,
                                            String searchString,
                                            long page,
                                            long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks authored by users the currently authorized user is subscribed on.
     * All tracks include searching string and match applied filters and sorting type
     *
     * @param filterList   list of filters that need to be applied
     * @param sortingType  sorting type that needs to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks authored by users the currently authorized user is subscribed on.
     * All tracks include searching string and match applied filters and sorting type
     */
    List<TrackCommonDto> getSubscriptionsLastTrackList(List<? extends Filter<?>> filterList,
                                                       SortingType sortingType,
                                                       String searchString,
                                                       long page,
                                                       long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks that was published not later than week ago and
     * have the highest rating. Sorted by rating in descending order. All tracks include searching string and match applied filters.
     *
     * @param filterList   list of filters that need to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks that was published not later than week ago and
     * have the highest rating. Sorted by rating in descending order. All tracks include searching string and match applied filters.
     */
    List<TrackCommonDto> getHottestPerWeek(List<? extends Filter<?>> filterList,
                                           String searchString,
                                           long page,
                                           long unitsOnPage);

    /**
     * The function that returns the page of the list of tracks that was published not later than week ago.
     * Sorted by publication timestamp in descending order.
     * All tracks include searching string and match applied filters and sorting type
     *
     * @param filterList   list of filters that need to be applied
     * @param searchString string that needs to be included into the name of each track in list
     * @param page         index of track list's page
     * @param unitsOnPage  number of tracks per one page
     * @return the page of the list of tracks that was published not later than week ago.
     * Sorted by publication timestamp in descending order.
     * All tracks include searching string and match applied filters and sorting type
     */
    List<TrackCommonDto> getNewPerWeek(List<? extends Filter<?>> filterList,
                                       String searchString,
                                       long page,
                                       long unitsOnPage);

    /**
     * The function that returns the track got by ID
     *
     * @param trackId ID of the track
     */
    TrackCommonDto getTrack(long trackId);
}
