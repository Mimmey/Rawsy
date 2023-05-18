package org.mimmey.dto.request.update.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserUpdateDtoMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(source = "mediaLinks", target = "mediaLinks", qualifiedByName = "stringsToMediaLinks")
    public abstract User toEntity(UserUpdateDto userUpdateDto);

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(source = "mediaLinks", target = "mediaLinks", qualifiedByName = "stringsToMediaLinks")
    public abstract List<User> toEntityList(List<UserUpdateDto> userUpdateDtoList);

    @Named("stringsToMediaLinks")
    protected List<MediaLink> stringsToMediaLinks(List<String> strings) {
        return strings.stream().map(MediaLink::new).collect(Collectors.toList());
    }

    @Named("encode")
    protected String encode(String password) {
        return password == null ? null : passwordEncoder.encode(password);
    }
}
