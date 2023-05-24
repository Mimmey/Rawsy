package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.config.security.utils.AuthorizedUserGetter;
import org.mimmey.entity.Comment;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.embedded_keys.CommentPK;
import org.mimmey.repository.CommentRepository;
import org.mimmey.repository.PurchaseRepository;
import org.mimmey.service.common.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class CommentServiceImpl implements CommentService {

    private final AuthorizedUserGetter authorizedUserGetter;

    private final CommentRepository commentRepository;

    private final PurchaseRepository purchaseRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createComment(Comment comment) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        checkPurchase(currentUser, comment.getPk().getTrack());
        checkIfCommentAlreadyPresent(currentUser, comment.getPk().getTrack());

        comment.setPk(new CommentPK(currentUser, comment.getPk().getTrack()));
        commentRepository.save(comment);
    }

    private void checkPurchase(User user, Track track) {
        purchaseRepository.findByPurchaserIdAndTrackId(user.getId(), track.getId())
                .orElseThrow(() -> new AccessDeniedException("Публикация комментария доступна только после покупки трека"));
    }

    private void checkIfCommentAlreadyPresent(User user, Track track) {
        if (commentRepository.findByAuthorIdAndTrackId(user.getId(), track.getId()).isPresent()) {
            throw new AccessDeniedException("Вы уже прокоммментировали трек");
        }
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
    public void removeComment(long trackId) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        try {
            commentRepository.deleteByAuthorIdAndTrackId(currentUser.getId(), trackId);
        } catch (
                JpaSystemException ignored) {
        }
    }
}
