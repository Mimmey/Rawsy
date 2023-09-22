package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.common.CommentCommonDto;
import org.mimmey.entity.Comment;
import org.mimmey.entity.embedded_keys.CommentPK;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentCommonDtoMapper {

    @Mapping(source = "pk", target = "commentAuthorId", qualifiedByName = "getCommentAuthorIdFromPk")
    @Mapping(source = "pk", target = "trackId", qualifiedByName = "getTrackIdFromPk")
    CommentCommonDto toDto(Comment comment);

    @Mapping(source = "pk", target = "commentAuthorId", qualifiedByName = "getCommentAuthorIdFromPk")
    @Mapping(source = "pk", target = "trackId", qualifiedByName = "getTrackIdFromPk")
    List<CommentCommonDto> toDtoList(List<Comment> commentList);

    @Named("getCommentAuthorIdFromPk")
    default Long getCommentAuthorIdFromPk(CommentPK commentPK) {
        return commentPK.getAuthor().getId();
    }

    @Named("getTrackIdFromPk")
    default Long getTrackIdFromPk(CommentPK commentPK) {
        return commentPK.getTrack().getId();
    }
}