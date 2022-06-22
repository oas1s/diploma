package yc.sdk.dto.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFunctionDto {
    private String id;
    private String folderId;
    private String name;
    private String description;
    private String logGroupId;
    private String httpInvokeUrl;
    private String status;

}
