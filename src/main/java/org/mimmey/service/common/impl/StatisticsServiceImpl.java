package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.repository.StatisticsRepository;
import org.mimmey.service.common.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Purchase> getPurchasesOfMyTrackHistory(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FavouriteAddition> getAddingMyTracksToFavouritesHistory(long userId, long page, long unitsOnPage) {
        return null;
    }
}
