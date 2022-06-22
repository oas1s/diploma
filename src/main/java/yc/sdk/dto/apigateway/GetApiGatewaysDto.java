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
public class GetApiGatewaysDto {
    private List<GetApiGatewayDto> apiGateways;
}
