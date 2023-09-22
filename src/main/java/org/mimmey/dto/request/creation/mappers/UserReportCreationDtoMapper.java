package org.mimmey.dto.request.creation.mappers;

import org.mimmey.dto.request.creation.UserReportCreationDto;
import org.mimmey.entity.Report;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.entity.embedded_keys.UserReportPK;
import org.mimmey.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserReportCreationDtoMapper {

    @Autowired
    @Qualifier("common-user")
    protected UserService userService;

    public UserReport toEntity(UserReportCreationDto userReportCreationDto) {
        Report report = new Report();
        report.setContent(userReportCreationDto.getContent());

        UserReport userReport = new UserReport();
        userReport.setPk(new UserReportPK(report, userService.getUser(userReportCreationDto.getUserSubjectId())));

        return userReport;
    }

    public List<UserReport> toEntityList(List<UserReportCreationDto> trackReportCreationDtos) {
        return trackReportCreationDtos.stream().map(this::toEntity).toList();
    }
}
