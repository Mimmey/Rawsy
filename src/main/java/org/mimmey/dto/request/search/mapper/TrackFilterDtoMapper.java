package org.mimmey.dto.request.search.mapper;

import org.mimmey.dto.request.search.TrackFilterDto;
import org.mimmey.utils.TrackFilter;
import org.mimmey.utils.TrackFilterTypes;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackFilterDtoMapper {

    public TrackFilter toEntity(TrackFilterDto dto) {
        TrackFilterTypes filterType = TrackFilterTypes.getByProperty(dto.getProperty());
        return new TrackFilter(filterType, dto.getValue());
    }

    public List<TrackFilter> toEntityList(List<TrackFilterDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
