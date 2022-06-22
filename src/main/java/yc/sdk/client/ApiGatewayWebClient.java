package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.apigateway.CreateApiGatewayDto;
import yc.sdk.dto.apigateway.GetApiGatewayDto;
import yc.sdk.dto.apigateway.GetApiGatewaysDto;
import yc.sdk.dto.apigateway.UpdateApiGatewayDto;
import yc.sdk.dto.common.Operation;

public class ApiGatewayWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://serverless-apigateway.api.cloud.yandex.net/apigateways/v1/apigateways").build();

    public Operation createApiGateway(String authenticationToken, CreateApiGatewayDto createApiGatewayDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createApiGatewayDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteApiGateway(String authenticationToken, String apiGatewayId) {
        return webClient
                .delete()
                .uri("/" + apiGatewayId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetApiGatewayDto getApiGateway(String authenticationToken, String apiGatewayId) {
        return webClient
                .get()
                .uri("/" + apiGatewayId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetApiGatewayDto.class)
                .block().getBody();
    }

    public GetApiGatewaysDto getApiGateways(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetApiGatewaysDto.class)
                .block().getBody();
    }

    public Operation updateApiGateway(String authenticationToken, String apiGatewayId, UpdateApiGatewayDto updateApiGatewayDto) {
        return webClient
                .patch()
                .uri("/" + apiGatewayId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateApiGatewayDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
