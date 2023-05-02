package org.mimmey.controller.user;

import org.springframework.http.ResponseEntity;

public interface RelatedUserListController {
    ResponseEntity<List<ShortUserInfoDto>> getList(long userId, long unitsOnPage, long page);
    ResponseEntity<String> deleteFromList(long userId, long relatedUserId);
}
