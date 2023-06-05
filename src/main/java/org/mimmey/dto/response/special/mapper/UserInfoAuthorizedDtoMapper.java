package org.mimmey.dto.response.special.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.config.security.utils.Role;
import org.mimmey.dto.response.common.mapper.MediaLinkCommonDtoMapper;
import org.mimmey.dto.response.special.UserInfoAuthorizedDto;
import org.mimmey.entity.Country;
import org.mimmey.entity.User;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {MediaLinkCommonDtoMapper.class})
public interface UserInfoAuthorizedDtoMapper {

    @Mapping(source = "country", target = "countryId", qualifiedByName = "getCountryId")
    @Mapping(source = "role", target = "role", qualifiedByName = "getRole")
    UserInfoAuthorizedDto toDto(User user);

    @Mapping(source = "country", target = "countryId", qualifiedByName = "getCountryId")
    @Mapping(source = "role", target = "role", qualifiedByName = "getRole")
    List<UserInfoAuthorizedDto> toDtoList(List<User> userList);

    @Named("getCountryId")
    default Integer getCountryId(Country country) {
        return country.getId();
    }

    @Named("getRole")
    default String getRole(Role role) {
        return role.name();
    }
}