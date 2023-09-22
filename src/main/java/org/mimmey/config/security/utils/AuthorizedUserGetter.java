package org.mimmey.config.security.utils;

import lombok.AllArgsConstructor;
import org.mimmey.config.exception.UnauthorizedException;
import org.mimmey.config.security.AppUserDetails;
import org.mimmey.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public final class AuthorizedUserGetter {

    private final AppUserDetailsMapper appUserDetailsMapper;

    public User getAuthorizedUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UnauthorizedException();
        }

        return appUserDetailsMapper.toUser((AppUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
