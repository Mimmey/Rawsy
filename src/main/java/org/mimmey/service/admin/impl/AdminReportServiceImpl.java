package org.mimmey.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.response.admin.ReportAdminDto;
import org.mimmey.repository.ReportRepository;
import org.mimmey.service.admin.AdminReportService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class AdminReportServiceImpl implements AdminReportService {

    private final ReportRepository reportRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportAdminDto getReport(long reportId) {
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
    public List<ReportCreationDto> getUserReportList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportCreationDto> getTrackReportList(long trackId, long page, long unitsOnPage) {
        return null;
    }
}
