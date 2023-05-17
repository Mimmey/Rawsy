package org.mimmey.service.common;

public interface MultitrackDownloadingService {

    /**
     * The function that downloads the archive with a multitrack of the given track
     *
     * @param trackId ID of the track
     */
    void downloadMultitrack(long trackId);
}
