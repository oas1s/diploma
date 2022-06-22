package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.cloud.CreateCloudDto;
import yc.sdk.dto.cloud.GetCloudDto;
import yc.sdk.dto.cloud.GetCloudsDto;
import yc.sdk.dto.cloud.UpdateCloudDto;
import yc.sdk.dto.common.Operation;

public class CloudWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://resource-manager.api.cloud.yandex.net/resource-manager/v1/clouds").build();

    public Operation createCloud(String authenticationToken, CreateCloudDto createCloudDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createCloudDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteCloud(String authenticationToken, String cloudId) {
        return webClient
                .delete()
                .uri("/" + cloudId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetCloudDto getCloud(String authenticationToken, String cloudId) {
        return webClient
                .get()
                .uri("/" + cloudId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetCloudDto.class)
                .block().getBody();
    }

    public GetCloudsDto getClouds(String authenticationToken) {
        return webClient
                .get()
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetCloudsDto.class)
                .block().getBody();
    }

    public Operation updateCloud(String authenticationToken, String cloudId, UpdateCloudDto updateCloudDto) {
        return webClient
                .patch()
                .uri("/" + cloudId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateCloudDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
