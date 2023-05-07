package org.mimmey.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.dto.response.common.CountryCommonDto;
import org.mimmey.dto.response.common.MediaLinkCommonDto;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoAdminDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "nickname", access = JsonProperty.Access.READ_ONLY)
    private String nickname;

    @JsonProperty(value = "email", access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonProperty(value = "isBanned", access = JsonProperty.Access.READ_ONLY)
    private Boolean isBanned;

    @JsonProperty(value = "country", access = JsonProperty.Access.READ_ONLY)
    private CountryCommonDto country;

    @JsonProperty(value = "about", access = JsonProperty.Access.READ_ONLY)
    private String about;

    @JsonIgnore
    private String jinglePath;

    @JsonIgnore
    private String avatarPath;

    @JsonProperty(value = "tracksInOtherUsersFavouritesCount", access = JsonProperty.Access.READ_ONLY)
    private Long tracksInOtherUsersFavouritesCount;

    @JsonProperty(value = "tracksPurchasedByOtherUsersCount", access = JsonProperty.Access.READ_ONLY)
    private Long tracksPurchasedByOtherUsersCount;

    @JsonProperty(value = "mediaLinks", access = JsonProperty.Access.READ_ONLY)
    private List<MediaLinkCommonDto> mediaLinks;
}
