package org.mimmey.service.admin;

import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.response.admin.ReportAdminDto;

import java.util.List;

public interface AdminReportService {

    /**
     * The function that returns the report got by ID
     *
     * @param reportId ID of the report
     * @return the report with the given ID
     */
    ReportAdminDto getReport(long reportId);

    /**
     * The function that removes the report from the database
     *
     * @param reportId ID of the report
     */
    void resolveReport(long reportId);

    /**
     * The function that returns the list of complaints against the given user
     *
     * @param userId      ID of the user to get complaints against
     * @param page        index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the user
     */
    List<ReportCreationDto> getUserReportList(long userId, long page, long unitsOnPage);

    /**
     * The function that returns the list of complaints against the given track
     *
     * @param trackId     ID of the track to get complaints against
     * @param page        index of complaint list's page
     * @param unitsOnPage number of complaints per one page
     * @return the page of the list of complaints against the track
     */
    List<ReportCreationDto> getTrackReportList(long trackId, long page, long unitsOnPage);
}
