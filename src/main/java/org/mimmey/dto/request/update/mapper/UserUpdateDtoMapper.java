package org.mimmey.dto.request.update.mapper;

import lombok.AllArgsConstructor;
import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.User;
import org.mimmey.service.common.CountryService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserUpdateDtoMapper {

    private final PasswordEncoder passwordEncoder;
    private final CountryService countryService;

    public User toEntity(UserUpdateDto userUpdateDto) {
        User user = User.createEmpty();
        user.setNickname(userUpdateDto.getNickname());
        user.setEmail(userUpdateDto.getEmail());
        user.setCountry(countryService.getCountry(userUpdateDto.getCountryId()));
        user.setPassword(encode(userUpdateDto.getPassword()));
        user.setAbout(userUpdateDto.getAbout());
        user.setMediaLinks(stringsToMediaLinks(userUpdateDto.getMediaLinks()));
        return user;
    }

    public List<User> toEntityList(List<UserUpdateDto> userUpdateDtoList) {
        return userUpdateDtoList.stream().map(this::toEntity).toList();
    }

    protected List<MediaLink> stringsToMediaLinks(List<String> strings) {
        if (strings == null) {
            return null;
        }

        return strings.stream().map(MediaLink::new).collect(Collectors.toList());
    }

    protected String encode(String password) {
        return password == null ? null : passwordEncoder.encode(password);
    }
}
