package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.MultitrackDownloadingService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultitrackDownloadingServiceImpl implements MultitrackDownloadingService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void downloadMultitrack(long trackId) {

    }
}