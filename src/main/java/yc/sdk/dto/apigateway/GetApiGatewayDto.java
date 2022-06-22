package yc.sdk.dto.apigateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetApiGatewayDto {
    private String id;
    private String folderId;
    private String createdAt;
    private String name;
    private String description;
    private String status;
    private String domain;
    private String logGroupId;
    private List<ApiGatewayAttachedDomain> attachedDomains;
    private ApiGatewayConnectivity connectivity;
}

