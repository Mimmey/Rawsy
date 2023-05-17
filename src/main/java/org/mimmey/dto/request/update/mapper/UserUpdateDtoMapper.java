package org.mimmey.dto.request.update.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserUpdateDtoMapper {

    @Mapping(source = "mediaLinks", target = "mediaLinks", qualifiedByName = "stringsToMediaLinks")
    public abstract User toEntity(UserUpdateDto userUpdateDto);

    @Mapping(source = "mediaLinks", target = "mediaLinks", qualifiedByName = "stringsToMediaLinks")
    public abstract List<User> toEntityList(List<UserUpdateDto> userUpdateDtoList);

    @Named("stringsToMediaLinks")
    public List<MediaLink> stringsToMediaLinks(List<String> strings) {
        return strings.stream().map(MediaLink::new).collect(Collectors.toList());
    }
}
