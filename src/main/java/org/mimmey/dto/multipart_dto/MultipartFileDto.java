package org.mimmey.dto.multipart_dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MultipartFileDto {
    MultipartFile file;
}
