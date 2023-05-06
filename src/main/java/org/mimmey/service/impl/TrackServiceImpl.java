package org.mimmey.service.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.ExtendedTrackDto;
import org.mimmey.dto.TrackDto;
import org.mimmey.utils.Filter;
import org.mimmey.utils.SortingType;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getGlobalTrackList(List<? extends Filter<?>> filterList,
                                             SortingType sortingType,
                                             String searchString,
                                             long page,
                                             long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getSubscriptionsLastTrackList(List<? extends Filter<?>> filterList,
                                                        SortingType sortingType,
                                                        String searchString,
                                                        long page,
                                                        long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getHottestPerWeek(List<? extends Filter<?>> filterList,
                                            String searchString,
                                            long page,
                                            long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackDto> getNewPerWeek(List<? extends Filter<?>> filterList,
                                        String searchString,
                                        long page,
                                        long unitsOnPage) {
        return null;
    }

    private String getSearchCriteria(List<? extends Filter<?>> filterList,
                                     SortingType sortingType,
                                     String searchString) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackDto getTrack(long trackId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeCost(long trackId, long newCost) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTrack(ExtendedTrackDto newTrackInfo) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void downloadMultitrack(long trackId) {

    }
}
