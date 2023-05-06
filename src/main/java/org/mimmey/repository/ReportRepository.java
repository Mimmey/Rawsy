package org.mimmey.repository;

import org.mimmey.entity.Report;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;

import java.util.List;

public interface ReportRepository {

    void save(Report report);

    Report getReport(long reportId);

    void resolveReport(long reportId);

    List<UserReport> getUserReportList(long userId, long page, long unitsOnPage);

    List<TrackReport> getTrackReportList(long trackId, long page, long unitsOnPage);
}
