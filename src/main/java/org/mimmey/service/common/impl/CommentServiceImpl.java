package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.config.security.AuthorizedUserGetter;
import org.mimmey.entity.Comment;
import org.mimmey.entity.User;
import org.mimmey.entity.embedded_keys.CommentPK;
import org.mimmey.repository.CommentRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final AuthorizedUserGetter authorizedUserGetter;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createComment(Comment comment) {
        User currentUser = userRepository.findById(authorizedUserGetter.getAuthorizedUser().getId()).orElseThrow(RuntimeException::new);
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
        commentRepository.deleteByAuthorIdAndTrackId(authorId, trackId);
    }
}
