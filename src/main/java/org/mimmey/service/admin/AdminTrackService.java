package org.mimmey.service.admin;

import org.mimmey.service.common.TrackService;

public interface AdminTrackService extends TrackService {

    /**
     * The function that deletes the track by its id
     *
     * @param id ID of the track to be deleted
     */
    void deleteTrack(long id);
}
