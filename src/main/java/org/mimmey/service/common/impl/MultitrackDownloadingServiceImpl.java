package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.MultitrackDownloadingService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class MultitrackDownloadingServiceImpl implements MultitrackDownloadingService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getMultitrack(long trackId) {
        return null;
    }
}
