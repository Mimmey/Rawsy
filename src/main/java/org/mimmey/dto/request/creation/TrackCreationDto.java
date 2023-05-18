package org.mimmey.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrackCreationDto {

    @NotBlank
    @Size(min = 5)
    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @NotNull
    @JsonProperty(value = "typeId", access = JsonProperty.Access.WRITE_ONLY)
    private Integer typeId;

    @NotBlank
    @JsonProperty(value = "about", access = JsonProperty.Access.WRITE_ONLY)
    private String about;

    @NotBlank
    @JsonProperty(value = "invoice", access = JsonProperty.Access.WRITE_ONLY)
    private String invoice;

    @JsonProperty(value = "hasVocal", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean hasVocal;

    @JsonProperty(value = "isCycled", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isCycled;

    @NotNull
    @Positive
    @JsonProperty(value = "bpm", access = JsonProperty.Access.WRITE_ONLY)
    private Integer bpm;

    @NotNull
    @PositiveOrZero
    @JsonProperty(value = "cost", access = JsonProperty.Access.WRITE_ONLY)
    private Long cost;

    @JsonProperty(value = "genreIds", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> genreIds;

    @JsonProperty(value = "moodIds", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> moodIds;
}
