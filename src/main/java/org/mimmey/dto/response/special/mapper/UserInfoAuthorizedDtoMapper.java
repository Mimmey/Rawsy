package org.mimmey.dto.response.special.mapper;

import org.mapstruct.Mapper;
import org.mimmey.dto.response.special.UserInfoAuthorizedDto;
import org.mimmey.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInfoAuthorizedDtoMapper {

    UserInfoAuthorizedDto toDto(User user);

    List<UserInfoAuthorizedDto> toDtoList(List<User> userList);
}