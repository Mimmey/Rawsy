package org.mimmey.dto.response.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.response.admin.UserInfoAdminDto;
import org.mimmey.entity.User;

import java.util.List;

@SuppressWarnings("unmapped")
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoAdminDtoMapper {

    UserInfoAdminDto toDto(User user);

    List<UserInfoAdminDto> toDtoList(List<User> userList);
}
