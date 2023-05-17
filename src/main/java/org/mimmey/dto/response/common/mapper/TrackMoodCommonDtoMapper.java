package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mimmey.dto.response.common.TrackMoodCommonDto;
import org.mimmey.entity.TrackMood;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMoodCommonDtoMapper {

    TrackMoodCommonDto toDto(TrackMood mood);

    List<TrackMoodCommonDto> toDtoList(List<TrackMood> moodList);
}