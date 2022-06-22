package yc.sdk.dto.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCloudFunctionVersionDto {
    private String functionId;
    private String runtime;
    private String entrypoint;
    private CloudFunctionVersionResources resources;
    private String executionTimeout;
    private String content;
}
