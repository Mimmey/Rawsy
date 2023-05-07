package org.mimmey.service.common;

import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface StatisticsService {

    /**
     * The function that returns purchase statistics of tracks authored by the given user
     *
     * @param userId      id of the user to get statistics of
     * @param page        index of purchase list's page
     * @param unitsOnPage number of purchase per one page
     * @return the page of the list of purchases
     */
    List<Purchase> getPurchasesOfMyTrackHistory(long userId, long page, long unitsOnPage);

    /**
     * The function that returns addition to other users' favourite statistics of tracks authored by the given user
     *
     * @param userId      id of the user to get statistics of
     * @param page        index of purchase list's page
     * @param unitsOnPage number of purchase per one page
     * @return the page of the list of addition to other users' favourite
     */
    List<FavouriteAddition> getAddingMyTracksToFavouritesHistory(long userId, long page, long unitsOnPage);
}
