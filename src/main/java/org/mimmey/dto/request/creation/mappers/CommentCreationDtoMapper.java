package org.mimmey.dto.request.creation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.request.creation.CommentCreationDto;
import org.mimmey.entity.Comment;
import org.mimmey.entity.embedded_keys.CommentPK;
import org.mimmey.service.common.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CommentCreationDtoMapper {

    @Autowired
    @Qualifier("common-track")
    protected TrackService trackService;

    @Mapping(source = "trackId", target = "pk", qualifiedByName = "trackToCommentPk")
    public abstract Comment toEntity(CommentCreationDto commentCreationDto);

    @Mapping(source = "trackId", target = "pk", qualifiedByName = "trackToCommentPk")
    public abstract List<Comment> toEntityList(List<CommentCreationDto> commentCreationDtoList);

    @Named("trackToCommentPk")
    public CommentPK trackToCommentPk(Long trackId) {
        return new CommentPK(null, trackService.getTrack(trackId));
    }
}
