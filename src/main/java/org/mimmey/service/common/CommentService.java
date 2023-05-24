package org.mimmey.service.common;

import org.mimmey.entity.Comment;
import org.springframework.data.domain.Page;

/**
 * @author Olga Motyleva
 */
public interface CommentService {

    /**
     * The function that saves the comment in the database
     *
     * @param comment comment to be saved
     */
    void createComment(Comment comment);

    /**
     * The function that returns the page of the list of comments of the given track
     *
     * @param trackId     ID of the track to get comments of
     * @param page        index of comment list's page
     * @param unitsOnPage number of comments per one page
     * @return the page of the list of comments of the given track
     */
    Page<Comment> getTrackComments(long trackId, int page, int unitsOnPage);

    /**
     * The function that removes the comment of the authorized user from the database
     *
     * @param trackId ID of the track that given comment belongs to
     */
    void removeComment(long trackId);
}
