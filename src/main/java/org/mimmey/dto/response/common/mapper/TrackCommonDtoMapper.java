package org.mimmey.dto.response.common.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.TrackGenreCommonDto;
import org.mimmey.dto.response.common.TrackMoodCommonDto;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.associative.TrackToGenreMatching;
import org.mimmey.entity.associative.TrackToMoodMatching;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TrackTypeCommonDtoMapper.class})
public abstract class TrackCommonDtoMapper {

    @Autowired
    protected TrackGenreCommonDtoMapper trackGenreCommonDtoMapper;

    @Autowired
    protected TrackMoodCommonDtoMapper trackMoodCommonDtoMapper;

    @Mapping(source = "author", target = "authorId", qualifiedByName = "getAuthorIdFromAuthor")
    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    @Mapping(source = "purchases", target = "purchasesCount", qualifiedByName = "purchasesToPurchasesCount")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "trackToGenreMatchingToGenreDto")
    @Mapping(source = "moods", target = "moods", qualifiedByName = "trackToMoodMatchingToMoodDto")
    public abstract TrackCommonDto toDto(Track track);

    @Mapping(source = "author", target = "authorId", qualifiedByName = "getAuthorIdFromAuthor")
    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    @Mapping(source = "purchases", target = "purchasesCount", qualifiedByName = "purchasesToPurchasesCount")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "trackToGenreMatchingToGenreDto")
    @Mapping(source = "moods", target = "moods", qualifiedByName = "trackToMoodMatchingToMoodDto")
    public abstract List<TrackCommonDto> toDtoList(List<Track> trackList);

    @Named("getAuthorIdFromAuthor")
    protected Long getAuthorIdFromAuthor(User author) {
        return author.getId();
    }

    @Named("favouriteAdditionsToFavouriteAdditionsCount")
    protected Long favouriteAdditionsToFavouriteAdditionsCount(List<FavouriteAddition> favouriteAdditionList) {
        return (long) favouriteAdditionList.size();
    }

    @Named("purchasesToPurchasesCount")
    protected Long purchasesToPurchasesCount(List<Purchase> purchaseList) {
        return (long) purchaseList.size();
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