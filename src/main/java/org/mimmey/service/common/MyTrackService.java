package org.mimmey.service.common;

import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.response.TrackAuthorDto;

public interface MyTrackService {

    /**
     * The function that returns the track got by ID. Contains extended info
     *
     * @param trackId ID of the track
     */
    TrackAuthorDto getTrack(long trackId);

    /**
     * The function that changes the given track
     *
     * @param trackUpdateDto new track information
     */
    void changeTrack(TrackUpdateDto trackUpdateDto);

    /**
     * The function that downloads the archive with a multitrack of the given track
     *
     * @param trackId ID of the track
     */
    void downloadMultitrack(long trackId);
}
