package org.mimmey.dto.response.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.admin.TrackReportAdminDto;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.embedded_keys.TrackReportPK;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackReportAdminDtoMapper {

    @Mapping(source = "pk", target = "trackSubjectId", qualifiedByName = "getSubjectId")
    @Mapping(source = "pk", target = "id", qualifiedByName = "getReportId")
    @Mapping(source = "pk", target = "authorId", qualifiedByName = "getAuthorId")
    @Mapping(source = "pk", target = "content", qualifiedByName = "getContent")
    @Mapping(source = "pk", target = "timestamp", qualifiedByName = "getTimestamp")
    TrackReportAdminDto toDto(TrackReport trackReport);

    @Mapping(source = "pk", target = "trackSubjectId", qualifiedByName = "getSubjectId")
    @Mapping(source = "pk", target = "id", qualifiedByName = "getReportId")
    @Mapping(source = "pk", target = "authorId", qualifiedByName = "getAuthorId")
    @Mapping(source = "pk", target = "content", qualifiedByName = "getContent")
    @Mapping(source = "pk", target = "timestamp", qualifiedByName = "getTimestamp")
    List<TrackReportAdminDto> toDtoList(List<TrackReport> trackReport);

    @Named("getSubjectId")
    default Long getSubjectId(TrackReportPK trackReportPK) {
        return trackReportPK.getTrackSubject().getId();
    }

    @Named("getReportId")
    default Long getReportId(TrackReportPK trackReportPK) {
        return trackReportPK.getReport().getId();
    }

    @Named("getAuthorId")
    default Long getAuthorId(TrackReportPK trackReportPK) {
        return trackReportPK.getReport().getAuthor().getId();
    }

    @Named("getContent")
    default String getContent(TrackReportPK trackReportPK) {
        return trackReportPK.getReport().getContent();
    }

    @Named("getTimestamp")
    default LocalDateTime getTimestamp(TrackReportPK trackReportPK) {
        return trackReportPK.getReport().getTimestamp();
    }
}
