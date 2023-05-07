package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.TrackService;
import org.mimmey.utils.Filter;
import org.mimmey.utils.SortingType;
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
    public List<TrackCommonDto> getGlobalTrackList(List<? extends Filter<?>> filterList,
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
    public List<TrackCommonDto> getSubscriptionsLastTrackList(List<? extends Filter<?>> filterList,
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
    public List<TrackCommonDto> getHottestPerWeek(List<? extends Filter<?>> filterList,
                                                  String searchString,
                                                  long page,
                                                  long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackCommonDto> getNewPerWeek(List<? extends Filter<?>> filterList,
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
    public TrackCommonDto getTrack(long trackId) {
        return null;
    }
}
