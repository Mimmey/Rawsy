package org.mimmey.service.common.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.MultitrackDownloadingService;
import org.mimmey.utils.FileWorker;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class MultitrackDownloadingServiceImpl implements MultitrackDownloadingService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getMultitrack(long id) {
        return FileWorker.tryReadFile(trackRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new).getTrackArchivePath());
    }
}
