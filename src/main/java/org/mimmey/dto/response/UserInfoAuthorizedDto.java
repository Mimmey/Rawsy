package org.mimmey.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.dto.request.creation.CommentCreationDto;
import org.mimmey.dto.response.common.CountryCommonDto;
import org.mimmey.dto.response.common.MediaLinkCommonDto;
import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.entity.associative.UserReport;

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

    @JsonProperty(value = "mediaLinkStrings", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> mediaLinkStrings;

    @JsonProperty(value = "commentsAuthoredBy", access = JsonProperty.Access.READ_ONLY)
    private List<CommentCreationDto> commentsAuthoredBy;

    @JsonProperty(value = "favourites", access = JsonProperty.Access.READ_ONLY)
    private List<TrackCommonDto> favourites;

    @JsonProperty(value = "purchases", access = JsonProperty.Access.READ_ONLY)
    private List<TrackCommonDto> purchases;

    @JsonProperty(value = "tracksInBasket", access = JsonProperty.Access.READ_ONLY)
    private List<TrackCommonDto> tracksInBasket;
}
