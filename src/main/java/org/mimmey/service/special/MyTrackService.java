package org.mimmey.service.special;

import org.mimmey.entity.Track;
import org.mimmey.service.common.TrackService;

public interface MyTrackService extends TrackService {

    /**
     * The function that changes the given track
     *
     * @param trackUpdate new track information
     */
    void changeTrack(Track trackUpdate);
}
