package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.Track;
import org.mimmey.repository.TrackRepository;
import org.mimmey.service.common.TrackService;
import org.mimmey.utils.TrackFilter;
import org.mimmey.utils.TrackSortingTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("common-track")
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    protected final TrackRepository trackRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getGlobalTracks(List<TrackFilter> filters,
                                       TrackSortingTypes sortingType,
                                       String searchString,
                                       int page,
                                       int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage, sortingType.getSort());
        return trackRepository.findAll(getSpecification(filters, searchString), pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getHottestPerWeek(List<TrackFilter> filters,
                                         String searchString,
                                         int page,
                                         int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage, TrackSortingTypes.BEST.getSort());
        return trackRepository.getHottestPerWeek(getSpecification(filters, searchString), pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Track> getNewPerWeek(List<TrackFilter> filters,
                                     String searchString,
                                     int page,
                                     int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage, TrackSortingTypes.NEW.getSort());
        return trackRepository.getHottestPerWeek(getSpecification(filters, searchString), pageable);
    }

    private Specification<Track> getSpecification(List<TrackFilter> filters,
                                                  String searchString) {
        Specification<Track> specification = (track, cq, cb) -> cb.like(track.get("name"), "%" + searchString + "%");
        for (TrackFilter filter : filters) {
            specification.and(filter.getSpecification());
        }

        return specification;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Track getTrack(long id) {
        return trackRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
