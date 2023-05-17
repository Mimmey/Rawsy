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
     */
    TrackType getType(int id);

    /**
     * The function that returns the list of track types
     */
    List<TrackType> getTypes();
}
