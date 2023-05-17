package org.mimmey.dto.response.special.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.special.TrackAuthorDto;
import org.mimmey.entity.Track;
import org.mimmey.entity.associative.FavouriteAddition;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackAuthorDtoMapper {

    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    TrackAuthorDto toDto(Track track);

    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    List<TrackAuthorDto> toDtoList(List<Track> trackList);

    @Named("favouriteAdditionsToFavouriteAdditionsCount")
    default Long favouriteAdditionsToFavouriteAdditionsCount(List<FavouriteAddition> favouriteAdditionList) {
        return (long) favouriteAdditionList.size();
    }
}