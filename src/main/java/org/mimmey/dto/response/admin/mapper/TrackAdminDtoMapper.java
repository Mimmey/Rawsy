package org.mimmey.dto.response.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackAdminDtoMapper {

    @Mapping(source = "author", target = "authorId", qualifiedByName = "getAuthorIdFromAuthor")
    @Mapping(source = "favouriteAdditions", target = "favouriteAdditionsCount", qualifiedByName = "favouriteAdditionsToFavouriteAdditionsCount")
    @Mapping(source = "purchases", target = "purchasesCount", qualifiedByName = "purchasesToPurchasesCount")
    TrackAdminDto toDto(Track track);

    @Mapping(source = "author", target = "authorId", qualifiedByName = "getAuthorIdFromAuthor")
    List<TrackAdminDto> toDtoList(List<Track> trackList);

    @Named("getAuthorIdFromAuthor")
    default Long getAuthorIdFromAuthor(User author) {
        return author.getId();
    }

    @Named("favouriteAdditionsToFavouriteAdditionsCount")
    default Long favouriteAdditionsToFavouriteAdditionsCount(List<FavouriteAddition> favouriteAdditionList) {
        return (long) favouriteAdditionList.size();
    }

    @Named("purchasesToPurchasesCount")
    default Long purchasesToPurchasesCount(List<Purchase> purchaseList) {
        return (long) purchaseList.size();
    }
}