package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.response.common.MediaLinkCommonDto;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.User;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MediaLinkCommonDtoMapper {

    @Mapping(source = "owner", target = "ownerId", qualifiedByName = "getOwnerId")
    MediaLinkCommonDto toDto(MediaLink mediaLink);

    @Mapping(source = "owner", target = "ownerId", qualifiedByName = "getOwnerId")
    List<MediaLinkCommonDto> toDtoList(List<MediaLink> mediaLinkList);

    @Named("getOwnerId")
    default Long getOwnerId(User owner) {
        return owner.getId();
    }
}