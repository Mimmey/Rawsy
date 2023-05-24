package org.mimmey.service.admin.impl;

import org.mimmey.repository.TrackRepository;
import org.mimmey.service.admin.AdminTrackService;
import org.mimmey.service.common.impl.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("admin-track")
public final class AdminTrackServiceImpl extends TrackServiceImpl implements AdminTrackService {

    public AdminTrackServiceImpl(@Autowired TrackRepository trackRepository) {
        super(trackRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTrack(long id) {
        trackRepository.deleteById(id);
    }
}
