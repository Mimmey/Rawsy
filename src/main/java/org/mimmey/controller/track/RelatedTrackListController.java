package org.mimmey.controller.track;

import org.springframework.http.ResponseEntity;

public interface RelatedTrackListController {
    ResponseEntity<List<TrackDto>> getList(long userId, long unitsOnPage, long page);
}
