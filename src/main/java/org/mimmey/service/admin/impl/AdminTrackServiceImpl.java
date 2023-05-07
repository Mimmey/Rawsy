package org.mimmey.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.repository.ReportRepository;
import org.mimmey.service.admin.AdminTrackService;
import org.mimmey.utils.Filter;
import org.mimmey.utils.SortingType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class AdminTrackServiceImpl implements AdminTrackService {

    private final ReportRepository reportRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackAdminDto> getGlobalTrackList(List<? extends Filter<?>> filterList,
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
    public List<TrackAdminDto> getSubscriptionsLastTrackList(List<? extends Filter<?>> filterList,
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
    public List<TrackAdminDto> getHottestPerWeek(List<? extends Filter<?>> filterList,
                                                 String searchString,
                                                 long page,
                                                 long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackAdminDto> getNewPerWeek(List<? extends Filter<?>> filterList,
                                             String searchString,
                                             long page,
                                             long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackAdminDto getTrack(long trackId) {
        return null;
    }
}
