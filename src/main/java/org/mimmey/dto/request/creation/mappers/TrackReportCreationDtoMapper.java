package org.mimmey.dto.request.creation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.request.creation.TrackReportCreationDto;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.embedded_keys.TrackReportPK;
import org.mimmey.service.common.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TrackReportCreationDtoMapper {

    @Autowired
    @Qualifier("common-track")
    protected TrackService trackService;

    @Mapping(source = "trackSubjectId", target = "pk", qualifiedByName = "dtoToPk")
    public abstract TrackReport toEntity(TrackReportCreationDto trackReportCreationDto);

    @Mapping(source = "trackSubjectId", target = "pk", qualifiedByName = "dtoToPk")
    public abstract List<TrackReport> toEntityList(List<TrackReportCreationDto> trackReportCreationDto);

    @Named("dtoToPk")
    protected TrackReportPK dtoToPk(Long trackSubjectId) {
        return new TrackReportPK(null, trackService.getTrack(trackSubjectId));
    }
}
