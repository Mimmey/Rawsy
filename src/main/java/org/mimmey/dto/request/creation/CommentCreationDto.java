package org.mimmey.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class CommentCreationDto {

    @NotNull
    @JsonProperty(value = "trackId", access = JsonProperty.Access.WRITE_ONLY)
    private Long trackId;

    @NotBlank
    @JsonProperty(value = "content", access = JsonProperty.Access.WRITE_ONLY)
    private String content;

    @NotNull
    @Min(1)
    @Max(5)
    @JsonProperty(value = "rate", access = JsonProperty.Access.WRITE_ONLY)
    private Byte rate;
}
