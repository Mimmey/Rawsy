package org.mimmey.service.common;

public interface MultitrackDownloadingService {

    /**
     * The function that returns the archive with a multitrack of the given track
     *
     * @param trackId ID of the track
     */
    byte[] getMultitrack(long trackId);
}
