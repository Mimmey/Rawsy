package org.mimmey.config.security.utils;

import lombok.AllArgsConstructor;
import org.mimmey.config.security.AppUserDetails;
import org.mimmey.entity.User;
import org.mimmey.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppUserDetailsMapper {

    private final UserRepository userRepository;

    public UserDetails toUserDetails(User user) {
        return new AppUserDetails(
                user.getNickname(),
                user.getPassword(),
                user.getRole().getAuthorities().stream().toList(),
                !user.getIsBanned()
        );
    }

    public User toUser(AppUserDetails appUserDetails) {
        return userRepository.findByNickname(appUserDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("No such registered user"));
    }
}
