package yc.sdk.dto.apigateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateApiGatewayDto {
    private String updateMask;
    private String name;
    private String description;
    private ApiGatewayConnectivity connectivity;
    private String openapiSpec;
}
