package org.mimmey.service.common;

import org.mimmey.entity.TrackType;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface TrackTypeService {

    /**
     * The function that returns the track type by its id
     *
     * @param id ID of the type
     * @return the track type with the given ID
     */
    TrackType getType(int id);

    /**
     * The function that returns the list of track types
     *
     * @return list of all track types in the DB
     */
    List<TrackType> getTypes();
}
