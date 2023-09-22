package org.mimmey.service.common;

import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;

/**
 * @author Olga Motyleva
 */
public interface ReportService {

    /**
     * The function that saves the report against the given user in the database
     *
     * @param userReport report to be created
     */
    void createUserReport(UserReport userReport);

    /**
     * The function that saves the report against the given track in the database
     *
     * @param trackReport report to be created
     */
    void createTrackReport(TrackReport trackReport);
}
