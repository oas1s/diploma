package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.containerregistry.CreateRegistryDto;
import yc.sdk.dto.containerregistry.GetRegistriesDto;
import yc.sdk.dto.containerregistry.GetRegistryDto;
import yc.sdk.dto.containerregistry.UpdateRegistryDto;

public class ContainerRegistryWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://container-registry.api.cloud.yandex.net/container-registry/v1/registries").build();

    public Operation createRegistry(String authenticationToken, CreateRegistryDto createRegistryDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createRegistryDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteRegistry(String authenticationToken, String registryId) {
        return webClient
                .delete()
                .uri("/" + registryId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetRegistryDto getRegistry(String authenticationToken, String registryId) {
        return webClient
                .get()
                .uri("/" + registryId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetRegistryDto.class)
                .block().getBody();
    }

    public GetRegistriesDto getRegistrys(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetRegistriesDto.class)
                .block().getBody();
    }

    public Operation updateRegistry(String authenticationToken, String registryId, UpdateRegistryDto updateRegistryDto) {
        return webClient
                .patch()
                .uri("/" + registryId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateRegistryDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
