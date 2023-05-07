package org.mimmey.service.common;

import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.request.creation.TrackReportCreationDto;
import org.mimmey.dto.request.creation.UserReportCreationDto;

/**
 * @author Olga Motyleva
 */
public interface ReportService {

    /**
     * The function that saves the report against the given user in the database
     *
     * @param userReportCreationDto report to be created
     */
    void createUserReport(UserReportCreationDto userReportCreationDto);

    /**
     * The function that saves the report against the given track in the database
     *
     * @param trackReportCreationDto report to be created
     */
    void createTrackReport(TrackReportCreationDto trackReportCreationDto);
}
