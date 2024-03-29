package org.mimmey.dto.request.update.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mimmey.dto.request.update.TrackUpdateDto;
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

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TrackUpdateDtoMapper {

    @Autowired
    protected TrackTypeService trackTypeService;

    @Autowired
    protected TrackGenreService trackGenreService;

    @Autowired
    protected TrackMoodService trackMoodService;

    @Mapping(source = "typeId", target = "type", qualifiedByName = "typeIdToType")
    @Mapping(source = "genreIds", target = "genres", qualifiedByName = "genreIdsToGenres")
    @Mapping(source = "moodIds", target = "moods", qualifiedByName = "moodIdsToMoods")
    public abstract Track toEntity(TrackUpdateDto trackUpdateDto);

    @Mapping(source = "typeId", target = "type", qualifiedByName = "typeIdToType")
    @Mapping(source = "genreIds", target = "genres", qualifiedByName = "genreIdsToGenres")
    @Mapping(source = "moodIds", target = "moods", qualifiedByName = "moodIdsToMoods")
    public abstract List<Track> toEntityList(List<TrackUpdateDto> trackUpdateDtoList);

    @Named("typeIdToType")
    protected TrackType typeIdToType(Integer typeId) {
        if (typeId == null) {
            return null;
        }

        return trackTypeService.getType(typeId);
    }

    @Named("genreIdsToGenres")
    protected List<TrackToGenreMatching> genreIdsToGenres(List<Integer> genreIds) {
        if (genreIds == null) {
            return null;
        }

        return genreIds.stream()
                .map((it) -> new TrackToGenreMatching(new TrackToGenreMatchingPK(null, trackGenreService.getGenre(it))))
                .collect(Collectors.toList());
    }

    @Named("moodIdsToMoods")
    protected List<TrackToMoodMatching> moodIdsToMoods(List<Integer> moodIds) {
        if (moodIds == null) {
            return null;
        }

        return moodIds.stream()
                .map((it) -> new TrackToMoodMatching(new TrackToMoodMatchingPK(null, trackMoodService.getMood(it))))
                .collect(Collectors.toList());
    }
}
