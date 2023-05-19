package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.config.security.utils.AuthorizedUserGetter;
import org.mimmey.entity.Report;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.entity.embedded_keys.TrackReportPK;
import org.mimmey.entity.embedded_keys.UserReportPK;
import org.mimmey.repository.ReportRepository;
import org.mimmey.repository.TrackReportRepository;
import org.mimmey.repository.UserReportRepository;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.ReportService;
import org.springframework.stereotype.Service;

@Service("common-report")
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    protected final UserRepository userRepository;
    protected final ReportRepository reportRepository;
    protected final UserReportRepository userReportRepository;
    protected final TrackReportRepository trackReportRepository;
    private final AuthorizedUserGetter authorizedUserGetter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createUserReport(UserReport userReport) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        Report report = userReport.getPk().getReport();
        report.setAuthor(currentUser);
        reportRepository.save(report);

        userReport.setPk(new UserReportPK(report, userReport.getPk().getUserSubject()));
        userReportRepository.save(userReport);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTrackReport(TrackReport trackReport) {
        User currentUser = authorizedUserGetter.getAuthorizedUser();

        Report report = trackReport.getPk().getReport();
        report.setAuthor(currentUser);
        reportRepository.save(report);

        trackReport.setPk(new TrackReportPK(report, trackReport.getPk().getTrackSubject()));
        trackReportRepository.save(trackReport);
    }
}
