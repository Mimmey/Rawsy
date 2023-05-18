package org.mimmey.dto.request.creation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.request.creation.TrackCreationDto;
import org.mimmey.entity.Track;
import org.mimmey.entity.TrackType;
import org.mimmey.entity.associative.TrackToGenreMatching;
import org.mimmey.entity.associative.TrackToMoodMatching;
import org.mimmey.entity.embedded_keys.TrackToGenreMatchingPK;
import org.mimmey.entity.embedded_keys.TrackToMoodMatchingPK;
import org.mimmey.service.common.TrackGenreService;
import org.mimmey.service.common.TrackMoodService;
import org.mimmey.service.common.TrackTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TrackCreationDtoMapper {

    @Autowired
    protected TrackTypeService trackTypeService;

    @Autowired
    protected TrackGenreService trackGenreService;

    @Autowired
    protected TrackMoodService trackMoodService;

    @Mapping(source = "typeId", target = "type", qualifiedByName = "typeIdToType")
    @Mapping(source = "genreIds", target = "genres", qualifiedByName = "genreIdsToGenres")
    @Mapping(source = "moodIds", target = "moods", qualifiedByName = "moodIdsToMoods")
    public abstract Track toEntity(TrackCreationDto trackCreationDto);

    @Mapping(source = "typeId", target = "type", qualifiedByName = "typeIdToType")
    @Mapping(source = "genreIds", target = "genres", qualifiedByName = "genreIdsToGenres")
    @Mapping(source = "moodIds", target = "moods", qualifiedByName = "moodIdsToMoods")
    public abstract List<Track> toEntityList(List<TrackCreationDto> trackCreationDtoList);

    @Named("typeIdToType")
    protected TrackType typeIdToType(Integer typeId) {
        return trackTypeService.getType(typeId);
    }

    @Named("genreIdsToGenres")
    protected List<TrackToGenreMatching> genreIdsToGenres(List<Integer> genreIds) {
        return genreIds.stream()
                .map((it) -> new TrackToGenreMatching(new TrackToGenreMatchingPK(null, trackGenreService.getGenre(it))))
                .collect(Collectors.toList());
    }

    @Named("moodIdsToMoods")
    protected List<TrackToMoodMatching> moodIdsToMoods(List<Integer> moodIds) {
        return moodIds.stream()
                .map((it) -> new TrackToMoodMatching(new TrackToMoodMatchingPK(null, trackMoodService.getMood(it))))
                .collect(Collectors.toList());
    }
}
