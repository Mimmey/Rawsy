package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.response.TrackAuthorDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.MyTrackService;
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
public class MyTrackServiceImpl implements MyTrackService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackAuthorDto getTrack(long trackId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTrack(TrackUpdateDto trackUpdateDto) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void downloadMultitrack(long trackId) {

    }
}
