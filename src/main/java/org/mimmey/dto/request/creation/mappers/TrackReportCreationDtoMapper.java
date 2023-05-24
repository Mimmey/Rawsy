package org.mimmey.dto.request.creation.mappers;

import org.mimmey.dto.request.creation.TrackReportCreationDto;
import org.mimmey.entity.Report;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.embedded_keys.TrackReportPK;
import org.mimmey.service.common.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackReportCreationDtoMapper {

    @Autowired
    @Qualifier("common-track")
    protected TrackService trackService;

    public TrackReport toEntity(TrackReportCreationDto trackReportCreationDto) {
        Report report = new Report();
        report.setContent(trackReportCreationDto.getContent());

        TrackReport trackReport = new TrackReport();
        trackReport.setPk(new TrackReportPK(report, trackService.getTrack(trackReportCreationDto.getTrackSubjectId())));

        return trackReport;
    }

    public List<TrackReport> toEntityList(List<TrackReportCreationDto> trackReportCreationDtos) {
        return trackReportCreationDtos.stream().map(this::toEntity).toList();
    }
}
