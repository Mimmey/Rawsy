package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.CommentCreationDto;
import org.mimmey.dto.response.common.CommentCommonDto;
import org.mimmey.repository.CommentRepository;
import org.mimmey.service.common.CommentService;
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
    public void createComment(CommentCreationDto commentCreationDto) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommentCommonDto> getTrackCommentList(long trackId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeComment(long trackId, long commentId) {

    }
}
