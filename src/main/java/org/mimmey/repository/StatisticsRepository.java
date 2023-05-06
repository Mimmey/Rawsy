package org.mimmey.repository;

import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;

import java.util.List;

public interface StatisticsRepository {

    List<Purchase> getPurchasesOfMyTrackHistory(long userId, long page, long unitsOnPage);

    List<FavouriteAddition> getAddingMyTracksToFavouritesHistory(long userId, long page, long unitsOnPage);
}
