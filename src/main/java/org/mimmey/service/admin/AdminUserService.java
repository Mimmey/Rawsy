package org.mimmey.service.admin;

import org.mimmey.service.common.UserService;

public interface AdminUserService extends UserService {

    /**
     * The function that bans the given user
     *
     * @param id ID of the user to be banned
     */
    void banUser(long id);
}
