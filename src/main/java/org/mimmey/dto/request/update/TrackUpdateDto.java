package org.mimmey.dto.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class TrackUpdateDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @JsonProperty(value = "type", access = JsonProperty.Access.WRITE_ONLY)
    private Integer typeId;

    @JsonProperty(value = "about", access = JsonProperty.Access.WRITE_ONLY)
    private String about;

    @JsonProperty(value = "invoice", access = JsonProperty.Access.WRITE_ONLY)
    private String invoice;

    @JsonProperty(value = "hasVocal", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean hasVocal;

    @JsonProperty(value = "isCycled", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isCycled;

    @Positive
    @JsonProperty(value = "bpm", access = JsonProperty.Access.WRITE_ONLY)
    private Integer bpm;

    @Positive
    @JsonProperty(value = "duration", access = JsonProperty.Access.WRITE_ONLY)
    private Integer duration;

    @PositiveOrZero
    @JsonProperty(value = "cost", access = JsonProperty.Access.WRITE_ONLY)
    private Long cost;

    @JsonProperty(value = "genreIds", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> genreIds;

    @JsonProperty(value = "moodIds", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> moodIds;
}
