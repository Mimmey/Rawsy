package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.common.FavouriteAdditionCommonDto;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.embedded_keys.FavouriteAdditionPK;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouriteAdditionCommonDtoMapper {

    @Mapping(source = "pk", target = "ownerId", qualifiedByName = "getOwnerIdFromPk")
    @Mapping(source = "pk", target = "trackId", qualifiedByName = "getTrackIdFromPk")
    FavouriteAdditionCommonDto toDto(FavouriteAddition favouriteAddition);

    @Mapping(source = "pk", target = "ownerId", qualifiedByName = "getOwnerIdFromPk")
    @Mapping(source = "pk", target = "trackId", qualifiedByName = "getTrackIdFromPk")
    List<FavouriteAdditionCommonDto> toDtoList(List<FavouriteAddition> favouriteAddition);

    @Named("getOwnerIdFromPk")
    default Long getOwnerIdFromPk(FavouriteAdditionPK favouriteAdditionPK) {
        return favouriteAdditionPK.getOwner().getId();
    }

    @Named("getTrackIdFromPk")
    default Long getTrackIdFromPk(FavouriteAdditionPK favouriteAdditionPK) {
        return favouriteAdditionPK.getTrack().getId();
    }
}