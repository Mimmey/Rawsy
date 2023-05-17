package org.mimmey.dto.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoCommonDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "nickname", access = JsonProperty.Access.READ_ONLY)
    private String nickname;

    @JsonProperty(value = "email", access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonProperty(value = "country", access = JsonProperty.Access.READ_ONLY)
    private CountryCommonDto country;

    @JsonProperty(value = "about", access = JsonProperty.Access.READ_ONLY)
    private String about;

    @JsonProperty(value = "tracksInOtherUsersFavouritesCount", access = JsonProperty.Access.READ_ONLY)
    private Long tracksInOtherUsersFavouritesCount;

    @JsonProperty(value = "tracksPurchasedByOtherUsersCount", access = JsonProperty.Access.READ_ONLY)
    private Long tracksPurchasedByOtherUsersCount;

    @JsonProperty(value = "mediaLinks", access = JsonProperty.Access.READ_ONLY)
    private List<MediaLinkCommonDto> mediaLinks;
}
