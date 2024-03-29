package org.mimmey.dto.request.creation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.request.creation.UserCreationDto;
import org.mimmey.entity.Country;
import org.mimmey.entity.MediaLink;
import org.mimmey.entity.User;
import org.mimmey.service.common.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserCreationDtoMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected CountryService countryService;

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(source = "mediaLinks", target = "mediaLinks", qualifiedByName = "stringsToMediaLinks")
    @Mapping(source = "countryId", target = "country", qualifiedByName = "countryIdToCountry")
    public abstract User toEntity(UserCreationDto userCreationDto);

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    @Mapping(source = "mediaLinks", target = "mediaLinks", qualifiedByName = "stringsToMediaLinks")
    @Mapping(source = "countryId", target = "country", qualifiedByName = "countryIdToCountry")
    public abstract List<User> toEntityList(List<UserCreationDto> userCreationDto);

    @Named("stringsToMediaLinks")
    protected List<MediaLink> stringsToMediaLinks(List<String> strings) {
        return strings.stream().map(MediaLink::new).collect(Collectors.toList());
    }

    @Named("countryIdToCountry")
    protected Country countryIdToCountry(Integer countryId) {
        return countryService.getCountry(countryId);
    }

    @Named("encode")
    protected String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
