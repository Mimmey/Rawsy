package org.mimmey.service.admin.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.response.admin.ReportAdminDto;
import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.dto.response.admin.UserInfoAdminDto;
import org.mimmey.repository.ReportRepository;
import org.mimmey.service.admin.AdminReportService;
import org.mimmey.service.admin.AdminUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final ReportRepository reportRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void banUser(long userId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfoAdminDto getUserInfo(long userId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoAdminDto> getSubscriptionList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoAdminDto> getSubscriberList(long userId, long page, long unitsOnPage) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TrackAdminDto> getPublishedTrackList(long userId, long page, long unitsOnPage) {
        return null;
    }
}
