package org.mimmey.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UserCreationDto {

    @NotBlank
    @JsonProperty(value = "nickname", access = JsonProperty.Access.WRITE_ONLY)
    private String nickname;

    @NotBlank
    @Email
    @JsonProperty(value = "email", access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @NotBlank
    @Size(min = 8)
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @JsonProperty(value = "countryId", access = JsonProperty.Access.WRITE_ONLY)
    private Long countryId;

    @JsonProperty(value = "about", access = JsonProperty.Access.WRITE_ONLY)
    private String about;

    @JsonProperty(value = "mediaLinks", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> mediaLinks;
}
