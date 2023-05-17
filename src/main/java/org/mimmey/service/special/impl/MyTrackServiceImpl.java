package org.mimmey.service.special.impl;

import org.mimmey.dto.request.update.mapper.TrackUpdateMapper;
import org.mimmey.entity.Track;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.impl.TrackServiceImpl;
import org.mimmey.service.special.MyTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorized-track")
public class MyTrackServiceImpl extends TrackServiceImpl implements MyTrackService {

    private final TrackUpdateMapper trackUpdateMapper;

    public MyTrackServiceImpl(@Autowired TrackRepository trackRepository,
                              @Autowired TrackUpdateMapper trackUpdateMapper) {
        super(trackRepository);
        this.trackUpdateMapper = trackUpdateMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTrack(Track trackUpdate) {
        Track track = trackRepository.findById(trackUpdate.getId()).orElseThrow(RuntimeException::new);
        trackUpdateMapper.updateTrack(trackUpdate, track);
        trackRepository.save(track);
    }
}
