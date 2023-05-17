package org.mimmey.dto.response.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.admin.UserReportAdminDto;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.entity.embedded_keys.UserReportPK;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserReportAdminDtoMapper {

    @Mapping(source = "pk", target = "userSubjectId", qualifiedByName = "getSubjectId")
    @Mapping(source = "pk", target = "id", qualifiedByName = "getReportId")
    @Mapping(source = "pk", target = "authorId", qualifiedByName = "getAuthorId")
    @Mapping(source = "pk", target = "content", qualifiedByName = "getContent")
    @Mapping(source = "pk", target = "timestamp", qualifiedByName = "getTimestamp")
    UserReportAdminDto toDto(UserReport userReport);

    @Mapping(source = "pk", target = "userSubjectId", qualifiedByName = "getSubjectId")
    @Mapping(source = "pk", target = "id", qualifiedByName = "getReportId")
    @Mapping(source = "pk", target = "authorId", qualifiedByName = "getAuthorId")
    @Mapping(source = "pk", target = "content", qualifiedByName = "getContent")
    @Mapping(source = "pk", target = "timestamp", qualifiedByName = "getTimestamp")
    List<UserReportAdminDto> toDtoList(List<UserReport> userReport);

    @Named("getSubjectId")
    default Long getSubjectId(UserReportPK userReportPK) {
        return userReportPK.getUserSubject().getId();
    }

    @Named("getReportId")
    default Long getReportId(UserReportPK userReportPK) {
        return userReportPK.getReport().getId();
    }

    @Named("getAuthorId")
    default Long getAuthorId(UserReportPK userReportPK) {
        return userReportPK.getReport().getAuthor().getId();
    }

    @Named("getContent")
    default String getContent(UserReportPK userReportPK) {
        return userReportPK.getReport().getContent();
    }

    @Named("getTimestamp")
    default LocalDateTime getTimestamp(UserReportPK userReportPK) {
        return userReportPK.getReport().getTimestamp();
    }
}
