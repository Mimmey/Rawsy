package org.mimmey.config.security;

import lombok.AllArgsConstructor;
import org.mimmey.config.security.utils.AppUserDetailsMapper;
import org.mimmey.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserDetailsMapper appUserDetailsMapper;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserDetailsMapper.toUserDetails(userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such registered user")));
    }
}
