package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.config.security.utils.AuthorizedUserGetter;
import org.mimmey.entity.Comment;
import org.mimmey.entity.User;
import org.mimmey.entity.embedded_keys.CommentPK;
import org.mimmey.repository.CommentRepository;
import org.mimmey.service.common.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class CommentServiceImpl implements CommentService {

    private final AuthorizedUserGetter authorizedUserGetter;

    private final CommentRepository commentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createComment(Comment comment) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();
        comment.setPk(new CommentPK(currentUser, comment.getPk().getTrack()));
        commentRepository.save(comment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Comment> getTrackComments(long trackId, int page, int unitsOnPage) {
        Pageable pageable = PageRequest.of(page, unitsOnPage);
        return commentRepository.findAllByTrackId(trackId, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeComment(long authorId, long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        if (!currentUser.getId().equals(authorId)) {
            throw new AccessDeniedException("Access is denied");
        }

        commentRepository.deleteByAuthorIdAndTrackId(authorId, trackId);
    }
}
