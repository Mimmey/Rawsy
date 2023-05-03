package org.mimmey.service;

import org.mimmey.dto.ReportDto;
import org.mimmey.entity.Report;
import org.mimmey.entity.TrackReport;
import org.mimmey.entity.UserReport;

import java.util.List;

/**
 * @author Olga Motyleva
 * */
public interface ReportService {

    /**
     * The function that saves the report in the database
     * @param report the report
     */
    void createReport(Report report);

    /**
     * The function that returns the report got by ID
     * @param reportId ID of the report
     * @return the report with the given ID
     */
    ReportDto getReport(long reportId);

    /**
     * The function that removes the report from the database
     * @param reportId ID of the report
     */
    void resolveReport(long reportId);

    /**
     * The function that returns the list of complaints against the given user
     * @param userId ID of the user to get complaints against
     * @param page index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the user
     */
    List<ReportDto> getUserReportList(long userId, long page, long unitsOnPage);

    /**
     * The function that returns the list of complaints against the given track
     * @param trackId ID of the track to get complaints against
     * @param page index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the track
     */
    List<ReportDto> getTrackReportList(long trackId, long page, long unitsOnPage);
}
