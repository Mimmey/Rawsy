package org.mimmey.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("user")
public class UserReportAdminDto extends ReportAdminDto {

    @JsonProperty(value = "userSubjectId", access = JsonProperty.Access.READ_ONLY)
    private Long userSubjectId;
}
