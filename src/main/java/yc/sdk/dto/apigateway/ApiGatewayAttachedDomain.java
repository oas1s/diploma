package yc.sdk.dto.apigateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiGatewayAttachedDomain {
    private String domainId;
    private String certificateId;
    private Boolean enabled;
    private String domain;
}
