package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mimmey.dto.response.common.TrackGenreCommonDto;
import org.mimmey.entity.TrackGenre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackGenreCommonDtoMapper {

    TrackGenreCommonDto toDto(TrackGenre genre);

    List<TrackGenreCommonDto> toDtoList(List<TrackGenre> genreList);
}