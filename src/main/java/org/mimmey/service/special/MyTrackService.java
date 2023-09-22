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

    /**
     * The function that sets the preview to the given track
     *
     * @param trackId ID of the track
     * @param preview the preview as a byte sequence
     */
    void setPreview(long trackId, byte[] preview);

    /**
     * The function that sets the multitrack archive to the given track
     *
     * @param trackId    ID of the track
     * @param multitrack the multitrack as a byte sequence
     */
    void setMultitrack(long trackId, byte[] multitrack);
}
