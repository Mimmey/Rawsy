package org.mimmey.service.common.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mimmey.entity.TrackMood;
import org.mimmey.repository.TrackMoodRepository;
import org.mimmey.service.common.TrackMoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class TrackMoodServiceImpl implements TrackMoodService {

    private final TrackMoodRepository trackMoodRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackMood getMood(int id) {
        return trackMoodRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackMood> getMoods() {
        return trackMoodRepository.findAll();
    }
}
