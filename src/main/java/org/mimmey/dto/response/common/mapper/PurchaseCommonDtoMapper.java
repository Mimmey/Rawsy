package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mimmey.dto.response.common.PurchaseCommonDto;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.entity.embedded_keys.PurchasePK;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseCommonDtoMapper {

    @Mapping(source = "pk", target = "purchaserId", qualifiedByName = "getPurchaserIdFromPk")
    @Mapping(source = "pk", target = "trackId", qualifiedByName = "getTrackIdFromPk")
    PurchaseCommonDto toDto(Purchase purchase);

    @Mapping(source = "pk", target = "purchaserId", qualifiedByName = "getPurchaserIdFromPk")
    @Mapping(source = "pk", target = "trackId", qualifiedByName = "getTrackIdFromPk")
    List<PurchaseCommonDto> toDtoList(List<Purchase> purchaseList);

    @Named("getPurchaserIdFromPk")
    default Long getPurchaserIdFromPk(PurchasePK purchasePK) {
        return purchasePK.getPurchaser().getId();
    }

    @Named("getTrackIdFromPk")
    default Long getTrackIdFromPk(PurchasePK purchasePK) {
        return purchasePK.getTrack().getId();
    }
}