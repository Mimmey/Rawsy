package org.mimmey.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.dto.response.common.TrackGenreCommonDto;
import org.mimmey.dto.response.common.TrackMoodCommonDto;
import org.mimmey.dto.response.common.TrackTypeCommonDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class TrackAdminDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    private String name;

    @JsonProperty(value = "publishingTimestamp", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDateTime publishingTimestamp;

    @JsonProperty(value = "authorId", access = JsonProperty.Access.READ_ONLY)
    private Long authorId;

    @JsonProperty(value = "type", access = JsonProperty.Access.READ_ONLY)
    private TrackTypeCommonDto type;

    @JsonProperty(value = "rating", access = JsonProperty.Access.READ_ONLY)
    private Double rating;

    @JsonProperty(value = "about", access = JsonProperty.Access.READ_ONLY)
    private String about;

    @JsonProperty(value = "invoice", access = JsonProperty.Access.READ_ONLY)
    private String invoice;

    @JsonProperty(value = "hasVocal", access = JsonProperty.Access.READ_ONLY)
    private Boolean hasVocal;

    @JsonProperty(value = "isCycled", access = JsonProperty.Access.READ_ONLY)
    private Boolean isCycled;

    @JsonProperty(value = "bpm", access = JsonProperty.Access.READ_ONLY)
    private Integer bpm;

    @JsonProperty(value = "duration", access = JsonProperty.Access.READ_ONLY)
    private Integer duration;

    @JsonProperty(value = "cost", access = JsonProperty.Access.READ_ONLY)
    private Long cost;

    @JsonIgnore
    private String audioPreviewPath;

    @JsonIgnore
    private String trackArchivePath;

    @JsonProperty(value = "genres", access = JsonProperty.Access.READ_ONLY)
    private List<TrackGenreCommonDto> genres;

    @JsonProperty(value = "moods", access = JsonProperty.Access.READ_ONLY)
    private List<TrackMoodCommonDto> moods;

    @JsonProperty(value = "favouriteAdditionsCount", access = JsonProperty.Access.READ_ONLY)
    private Long favouriteAdditionsCount;

    @JsonProperty(value = "purchasesCount", access = JsonProperty.Access.READ_ONLY)
    private Long purchasesCount;
}
