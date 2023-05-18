package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.TrackType;
import org.mimmey.repository.TrackTypeRepository;
import org.mimmey.service.common.TrackTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackTypeServiceImpl implements TrackTypeService {

    private final TrackTypeRepository trackTypeRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackType getType(int id) {
        return trackTypeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackType> getTypes() {
        return trackTypeRepository.findAll();
    }
}
