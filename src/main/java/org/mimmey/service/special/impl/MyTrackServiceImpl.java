package org.mimmey.service.special.impl;

import jakarta.persistence.EntityNotFoundException;
import org.mimmey.config.security.utils.AuthorizedUserGetter;
import org.mimmey.dto.request.update.mapper.TrackUpdateMapper;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.impl.TrackServiceImpl;
import org.mimmey.service.special.MyTrackService;
import org.mimmey.utils.FileTypes;
import org.mimmey.utils.FileWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service("authorized-track")
public final class MyTrackServiceImpl extends TrackServiceImpl implements MyTrackService {

    private final TrackUpdateMapper trackUpdateMapper;

    private final AuthorizedUserGetter authorizedUserGetter;

    public MyTrackServiceImpl(@Autowired TrackRepository trackRepository,
                              @Autowired TrackUpdateMapper trackUpdateMapper,
                              @Autowired AuthorizedUserGetter authorizedUserGetter) {
        super(trackRepository);
        this.trackUpdateMapper = trackUpdateMapper;
        this.authorizedUserGetter = authorizedUserGetter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeTrack(Track trackUpdate) {
        Track track = trackRepository.findById(trackUpdate.getId()).orElseThrow(EntityNotFoundException::new);
        checkIfAuthorizedHasAccess(track);

        trackUpdateMapper.updateTrack(trackUpdate, track);
        trackRepository.save(track);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPreview(long trackId, byte[] preview) {
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);
        checkIfAuthorizedHasAccess(track);

        FileWorker.tryWriteToFile(track.getAudioPreviewPath(), preview, FileTypes.PREVIEW);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMultitrack(long trackId, byte[] multitrack) {
        Track track = trackRepository.findById(trackId).orElseThrow(EntityNotFoundException::new);
        checkIfAuthorizedHasAccess(track);

        FileWorker.tryWriteToFile(track.getTrackArchivePath(), multitrack, FileTypes.ARCHIVE);
    }

    private void checkIfAuthorizedHasAccess(Track track) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        if (!currentUser.getId().equals(track.getAuthor().getId())) {
            throw new AccessDeniedException("Access is denied");
        }
    }
}
