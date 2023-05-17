package org.mimmey.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @JsonProperty(value = "hasVocal", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean hasVocal;

    @NotNull
    @JsonProperty(value = "isCycled", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isCycled;

    @NotNull
    @Min(0)
    @JsonProperty(value = "bpm", access = JsonProperty.Access.WRITE_ONLY)
    private Integer bpm;

    @NotNull
    @Min(0)
    @JsonProperty(value = "cost", access = JsonProperty.Access.WRITE_ONLY)
    private Long cost;

    @JsonProperty(value = "genreIds", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> genreIds;

    @JsonProperty(value = "moodIds", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> moodIds;
}
