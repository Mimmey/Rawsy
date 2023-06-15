package org.mimmey.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
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
public class UserUpdateDto {

    @JsonProperty(value = "nickname", access = JsonProperty.Access.WRITE_ONLY)
    private String nickname;

    @JsonProperty(value = "email", access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @JsonProperty(value = "country", access = JsonProperty.Access.WRITE_ONLY)
    private Integer countryId;

    @Size(min = 8)
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(value = "about", access = JsonProperty.Access.WRITE_ONLY)
    private String about;

    @JsonProperty(value = "mediaLinks", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> mediaLinks;
}
