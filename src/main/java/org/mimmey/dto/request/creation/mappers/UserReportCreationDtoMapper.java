package org.mimmey.dto.request.creation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.request.creation.UserReportCreationDto;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.entity.embedded_keys.UserReportPK;
import org.mimmey.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserReportCreationDtoMapper {

    @Autowired
    @Qualifier("common-user")
    protected UserService userService;

    @Mapping(source = "userSubjectId", target = "pk", qualifiedByName = "dtoToPk")
    public abstract UserReport toEntity(UserReportCreationDto userReportCreationDto);

    @Mapping(source = "userSubjectId", target = "pk", qualifiedByName = "dtoToPk")
    public abstract List<UserReport> toEntityList(List<UserReportCreationDto> trackReportCreationDto);

    @Named("dtoToPk")
    protected UserReportPK dtoToPk(Long userSubjectId) {
        return new UserReportPK(null, userService.getUser(userSubjectId));
    }
}
