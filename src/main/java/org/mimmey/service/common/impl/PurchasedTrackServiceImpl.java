package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.PurchasedTrackService;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class PurchasedTrackServiceImpl implements PurchasedTrackService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void downloadMultitrack(long trackId) {

    }
}
