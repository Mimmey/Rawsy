package org.mimmey.service.admin.impl;

import org.mimmey.config.security.AuthorizedUserGetter;
import org.mimmey.entity.Report;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.repository.ReportRepository;
import org.mimmey.repository.TrackReportRepository;
import org.mimmey.repository.UserReportRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.admin.AdminReportService;
import org.mimmey.service.common.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("admin-report")
public class AdminReportServiceImpl extends ReportServiceImpl implements AdminReportService {

    public AdminReportServiceImpl(@Autowired UserRepository userRepository,
                                  @Autowired ReportRepository reportRepository,
                                  @Autowired UserReportRepository userReportRepository,
                                  @Autowired TrackReportRepository trackReportRepository,
                                  @Autowired AuthorizedUserGetter authorizedUserGetter) {
        super(userRepository, reportRepository, userReportRepository, trackReportRepository, authorizedUserGetter);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Report getReport(long id) {
        return reportRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserReport getUserReport(long id) {
        return userReportRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackReport getTrackReport(long id) {
        return trackReportRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolveReport(long id) {
        reportRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<UserReport> getUserReports(long userId, long page, long unitsOnPage) {
        Pageable pageable = PageRequest.of((int) page, (int) unitsOnPage);
        return userReportRepository.getUserReportsByUserSubjectId(userId, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrackReport> getTrackReports(long trackId, long page, long unitsOnPage) {
        Pageable pageable = PageRequest.of((int) page, (int) unitsOnPage);
        return trackReportRepository.findAllByTrackSubjectId(trackId, pageable);
    }
}
