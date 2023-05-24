package org.mimmey.service.common.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mimmey.entity.TrackGenre;
import org.mimmey.repository.TrackGenreRepository;
import org.mimmey.service.common.TrackGenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class TrackGenreServiceImpl implements TrackGenreService {

    private final TrackGenreRepository trackGenreRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public TrackGenre getGenre(int id) {
        return trackGenreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackGenre> getGenres() {
        return trackGenreRepository.findAll();
    }
}
