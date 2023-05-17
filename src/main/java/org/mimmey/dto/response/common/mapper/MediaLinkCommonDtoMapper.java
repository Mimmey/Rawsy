package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.common.MediaLinkCommonDto;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.embedded_keys.MediaLinkPK;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaLinkCommonDtoMapper {

    @Mapping(source = "pk", target = "id", qualifiedByName = "getIdFromPk")
    @Mapping(source = "pk", target = "ownerId", qualifiedByName = "getOwnerIdFromPk")
    MediaLinkCommonDto toDto(MediaLink mediaLink);

    @Mapping(source = "pk", target = "id", qualifiedByName = "getIdFromPk")
    @Mapping(source = "pk", target = "ownerId", qualifiedByName = "getOwnerIdFromPk")
    List<MediaLinkCommonDto> toDtoList(List<MediaLink> mediaLinkList);

    @Named("getIdFromPk")
    default Long getIdFromPk(MediaLinkPK mediaLinkPK) {
        return mediaLinkPK.getId();
    }

    @Named("getOwnerIdFromPk")
    default Long getOwnerIdFromPk(MediaLinkPK mediaLinkPK) {
        return mediaLinkPK.getOwner().getId();
    }
}