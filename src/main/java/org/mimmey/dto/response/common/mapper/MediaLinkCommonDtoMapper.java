package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mimmey.dto.response.common.MediaLinkCommonDto;
import org.mimmey.entity.MediaLink;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaLinkCommonDtoMapper {

    MediaLinkCommonDto toDto(MediaLink mediaLink);

    List<MediaLinkCommonDto> toDtoList(List<MediaLink> mediaLinkList);
}