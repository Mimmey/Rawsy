package org.mimmey.dto.request.update.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mimmey.entity.Track;

@Mapper(componentModel = "spring")
public interface TrackUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTrack(Track updates, @MappingTarget Track entity);
}
