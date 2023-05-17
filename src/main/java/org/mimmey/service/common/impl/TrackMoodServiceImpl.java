package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.TrackMood;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.TrackMoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackMoodServiceImpl implements TrackMoodService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackMood getMood(int id) {
        return trackRepository.findMoodById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackMood> getMoods() {
        return trackRepository.findAllMoods();
    }
}
