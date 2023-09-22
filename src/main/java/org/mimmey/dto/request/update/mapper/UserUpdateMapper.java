package org.mimmey.dto.request.update.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mimmey.entity.User;

@Mapper(componentModel = "spring")
public interface UserUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(User updates, @MappingTarget User entity);
}
