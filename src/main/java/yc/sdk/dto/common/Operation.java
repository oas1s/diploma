package yc.sdk.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.sdk.dto.functions.ErrorDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Operation {
    private Boolean done;
    private String id;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private ErrorDto error;
}
