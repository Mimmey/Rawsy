package org.mimmey.service.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.ReportDto;
import org.mimmey.entity.Report;
import org.mimmey.entity.TrackReport;
import org.mimmey.entity.UserReport;
import org.mimmey.repository.ReportRepository;
import org.mimmey.service.ReportService;
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
    public void createReport(Report report) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportDto getReport(long reportId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolveReport(long reportId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportDto> getUserReportList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportDto> getTrackReportList(long trackId, long page, long unitsOnPage) {
        return null;
    }
}
