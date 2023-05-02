package org.mimmey.service;

import org.mimmey.dto.CommentDto;
import org.mimmey.entity.Comment;

import java.util.List;

/**
 * @author Olga Motyleva
 * */
public interface CommentService {

    /**
     * The function that saves the comment in the database
     * @param comment comment to be saved
     */
    void createComment(Comment comment);

    /**
     * The function that returns the list of comments of the given track
     * @param trackId ID of the track to get comments of
     * @param page index of comment list's page
     * @param unitsOnPage number of comments per one page
     * @return the page of the list of comments of the given track
     */
    List<CommentDto> getTrackCommentList(long trackId, long page, long unitsOnPage);

    /**
     * The function that removes the comment from the database
     * @param trackId ID of the track that given comment belongs to
     * @param commentId ID of the comment in the list of track comments
     */
    void removeComment(long trackId, long commentId);
}