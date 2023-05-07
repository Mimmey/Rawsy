package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.request.creation.TrackReportCreationDto;
import org.mimmey.dto.request.creation.UserReportCreationDto;
import org.mimmey.entity.Report;
import org.mimmey.repository.ReportRepository;
import org.mimmey.service.common.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUserReport(UserReportCreationDto userReportCreationDto) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTrackReport(TrackReportCreationDto trackReportCreationDto) {

    }
}
