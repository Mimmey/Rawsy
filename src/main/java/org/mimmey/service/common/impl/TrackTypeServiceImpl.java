package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.TrackType;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.TrackTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackTypeServiceImpl implements TrackTypeService {

    private final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackType getType(int id) {
        return trackRepository.findTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackType> getTypes() {
        return trackRepository.findAllTypes();
    }
}
