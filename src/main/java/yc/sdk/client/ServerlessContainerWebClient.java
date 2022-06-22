package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.serverlesscontainers.CreateServerlessContainerDto;
import yc.sdk.dto.serverlesscontainers.GetServerlessContainerDto;
import yc.sdk.dto.serverlesscontainers.GetServerlessContainersDto;
import yc.sdk.dto.serverlesscontainers.UpdateServerlessContainerDto;

public class ServerlessContainerWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://serverless-containers.api.cloud.yandex.net/containers/v1/containers").build();

    public Operation createServerlessContainer(String authenticationToken, CreateServerlessContainerDto createServerlessContainerDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createServerlessContainerDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteServerlessContainer(String authenticationToken, String serverlessContainerId) {
        return webClient
                .delete()
                .uri("/" + serverlessContainerId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetServerlessContainerDto getServerlessContainer(String authenticationToken, String serverlessContainerId) {
        return webClient
                .get()
                .uri("/" + serverlessContainerId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetServerlessContainerDto.class)
                .block().getBody();
    }

    public GetServerlessContainersDto getServerlessContainers(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetServerlessContainersDto.class)
                .block().getBody();
    }

    public Operation updateServerlessContainer(String authenticationToken, String serverlessContainerId, UpdateServerlessContainerDto updateServerlessContainerDto) {
        return webClient
                .patch()
                .uri("/" + serverlessContainerId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateServerlessContainerDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
