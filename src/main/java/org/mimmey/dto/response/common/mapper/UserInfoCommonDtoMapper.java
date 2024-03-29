package org.mimmey.dto.response.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.entity.User;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {MediaLinkCommonDtoMapper.class})
public interface UserInfoCommonDtoMapper {

    UserInfoCommonDto toDto(User user);

    List<UserInfoCommonDto> toDtoList(List<User> userList);
}