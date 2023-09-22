package org.mimmey.service.admin;

import org.mimmey.entity.Report;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.service.common.ReportService;
import org.springframework.data.domain.Page;

public interface AdminReportService extends ReportService {

    /**
     * The function that returns the report got by ID
     *
     * @param id ID of the report
     * @return the report with the given ID
     */
    Report getReport(long id);

    /**
     * The function that returns the user report got by ID
     *
     * @param id ID of the user report
     * @return the user report with the given ID
     */
    UserReport getUserReport(long id);

    /**
     * The function that returns the track report got by ID
     *
     * @param id ID of the track report
     * @return the track report with the given ID
     */
    TrackReport getTrackReport(long id);

    /**
     * The function that removes the report from the database
     *
     * @param id ID of the report
     */
    void resolveReport(long id);

    /**
     * The function that returns the page of the list of reports against the given user
     *
     * @param userId      ID of the user to get complaints against
     * @param page        index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the user
     */
    Page<UserReport> getReportsAgainstUser(long userId, long page, long unitsOnPage);

    /**
     * The function that returns the page of the list of reports against the given track
     *
     * @param trackId     ID of the track to get complaints against
     * @param page        index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the track
     */
    Page<TrackReport> getReportsAgainstTrack(long trackId, long page, long unitsOnPage);

    /**
     * The function that returns the page of the global list of user reports
     *
     * @param page        index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the user
     */
    Page<UserReport> getUserReports(long page, long unitsOnPage);

    /**
     * The function that returns the page of the global list of track reports
     *
     * @param page        index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the track
     */
    Page<TrackReport> getTrackReports(long page, long unitsOnPage);
}
