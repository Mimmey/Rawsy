package org.mimmey.dto.request.creation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.entity.Report;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReportCreationDtoMapper {

    Report toEntity(ReportCreationDto reportCreationDto);

    List<Report> toEntityList(List<ReportCreationDto> reportCreationDtoList);
}
