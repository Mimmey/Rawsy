package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.TrackGenre;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.TrackGenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackGenreServiceImpl implements TrackGenreService {

    private final TrackRepository trackRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public TrackGenre getGenre(int id) {
        return trackRepository.findGenreById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackGenre> getGenres() {
        return trackRepository.findAllGenres();
    }
}
