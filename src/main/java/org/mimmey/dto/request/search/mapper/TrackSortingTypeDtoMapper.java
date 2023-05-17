package org.mimmey.dto.request.search.mapper;

import org.mimmey.utils.TrackSortingTypes;
import org.springframework.stereotype.Component;

@Component
public class TrackSortingTypeDtoMapper {

    public TrackSortingTypes toEntity(String dto) {
        return TrackSortingTypes.getByName(dto);
    }
}
