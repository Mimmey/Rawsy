package org.mimmey.service.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.CommentDto;
import org.mimmey.entity.Comment;
import org.mimmey.repository.CommentRepository;
import org.mimmey.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createComment(Comment comment) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommentDto> getTrackCommentList(long trackId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeComment(long trackId, long commentId) {

    }
}
