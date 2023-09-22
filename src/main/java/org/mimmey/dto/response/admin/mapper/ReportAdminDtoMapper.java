package org.mimmey.dto.response.admin.mapper;

import org.mimmey.dto.response.admin.ReportAdminDto;
import org.mimmey.entity.Report;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.entity.embedded_keys.TrackReportPK;
import org.mimmey.entity.embedded_keys.UserReportPK;
import org.mimmey.service.admin.AdminReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportAdminDtoMapper {

    @Autowired
    private UserReportAdminDtoMapper userReportAdminDtoMapper;

    @Autowired
    private TrackReportAdminDtoMapper trackReportAdminDtoMapper;

    @Autowired
    private AdminReportService adminReportService;

    public ReportAdminDto toDto(Report report) {

        try {
            UserReport userReport = adminReportService.getUserReport(report.getId());
            return userReportAdminDtoMapper.toDto(new UserReport(new UserReportPK(report, userReport.getPk().getUserSubject())));
        } catch (RuntimeException e) {
            TrackReport trackReport = adminReportService.getTrackReport(report.getId());
            return trackReportAdminDtoMapper.toDto(new TrackReport(new TrackReportPK(report, trackReport.getPk().getTrackSubject())));
        }
    }

    public List<ReportAdminDto> toDtoList(List<Report> reportList) {
        return reportList.stream().map(this::toDto).toList();
    }
}
