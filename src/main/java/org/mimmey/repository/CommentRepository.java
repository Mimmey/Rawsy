package org.mimmey.repository;

import org.mimmey.entity.Comment;

import java.util.List;

public interface CommentRepository {

     void save(Comment comment);

     List<Comment> getCommentList(long trackId, long page, long unitsOnPage);

     void remove(long trackId, long commentId);
}
