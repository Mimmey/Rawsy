package org.mimmey.dto.response.special;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
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
public class UserInfoAuthorizedDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "nickname", access = JsonProperty.Access.READ_ONLY)
    private String nickname;

    @Email
    @JsonProperty(value = "email", access = JsonProperty.Access.READ_ONLY)
    private String email;

    @Email
    @JsonProperty(value = "role", access = JsonProperty.Access.READ_ONLY)
    private String role;

    @JsonProperty(value = "country", access = JsonProperty.Access.READ_ONLY)
    private CountryCommonDto country;

    @JsonProperty(value = "countryId", access = JsonProperty.Access.WRITE_ONLY)
    private Long countryId;

    @JsonProperty(value = "about", access = JsonProperty.Access.READ_ONLY)
    private String about;

    @JsonProperty(value = "tracksInOtherUsersFavouritesCount", access = JsonProperty.Access.READ_ONLY)
    private Long tracksInOtherUsersFavouritesCount;

    @JsonProperty(value = "tracksPurchasedByOtherUsersCount", access = JsonProperty.Access.READ_ONLY)
    private Long tracksPurchasedByOtherUsersCount;

    @JsonProperty(value = "mediaLinks", access = JsonProperty.Access.READ_ONLY)
    private List<MediaLinkCommonDto> mediaLinks;
}
