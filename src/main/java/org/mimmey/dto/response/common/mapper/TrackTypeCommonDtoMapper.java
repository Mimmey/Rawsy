package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mimmey.dto.response.common.TrackTypeCommonDto;
import org.mimmey.entity.TrackType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackTypeCommonDtoMapper {

    TrackTypeCommonDto toDto(TrackType trackType);

    List<TrackTypeCommonDto> toDtoList(List<TrackType> trackType);
}