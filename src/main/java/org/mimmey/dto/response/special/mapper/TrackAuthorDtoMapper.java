package org.mimmey.dto.response.special.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.common.TrackGenreCommonDto;
import org.mimmey.dto.response.common.TrackMoodCommonDto;
import org.mimmey.dto.response.common.mapper.PurchaseCommonDtoMapper;
import org.mimmey.dto.response.common.mapper.TrackGenreCommonDtoMapper;
import org.mimmey.dto.response.common.mapper.TrackMoodCommonDtoMapper;
import org.mimmey.dto.response.common.mapper.TrackTypeCommonDtoMapper;
import org.mimmey.dto.response.special.TrackAuthorDto;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.TrackToGenreMatching;
import org.mimmey.entity.associative.TrackToMoodMatching;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TrackTypeCommonDtoMapper.class,
                PurchaseCommonDtoMapper.class})
public abstract class TrackAuthorDtoMapper {

    @Autowired
    protected TrackGenreCommonDtoMapper trackGenreCommonDtoMapper;

    @Autowired
    protected TrackMoodCommonDtoMapper trackMoodCommonDtoMapper;

    @Mapping(source = "author", target = "authorId", qualifiedByName = "getAuthorId")
    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "trackToGenreMatchingToGenreDto")
    @Mapping(source = "moods", target = "moods", qualifiedByName = "trackToMoodMatchingToMoodDto")
    public abstract TrackAuthorDto toDto(Track track);

    @Mapping(source = "author", target = "authorId", qualifiedByName = "getAuthorId")
    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "trackToGenreMatchingToGenreDto")
    @Mapping(source = "moods", target = "moods", qualifiedByName = "trackToMoodMatchingToMoodDto")
    public abstract List<TrackAuthorDto> toDtoList(List<Track> trackList);

    @Named("getAuthorId")
    protected Long getAuthorId(User author) {
        return author.getId();
    }

    @Named("favouriteAdditionsToFavouriteAdditionsCount")
    protected Long favouriteAdditionsToFavouriteAdditionsCount(List<FavouriteAddition> favouriteAdditionList) {
        return (long) favouriteAdditionList.size();
    }

    @Named("trackToGenreMatchingToGenreDto")
    protected TrackGenreCommonDto trackToGenreMatchingToGenreDto(TrackToGenreMatching matching) {
        return trackGenreCommonDtoMapper.toDto(matching.getPk().getGenre());
    }

    @Named("trackToMoodMatchingToMoodDto")
    protected TrackMoodCommonDto trackToMoodMatchingToMoodDto(TrackToMoodMatching matching) {
        return trackMoodCommonDtoMapper.toDto(matching.getPk().getMood());
    }
}