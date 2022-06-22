package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.serviceaccounts.CreateServiceAccountDto;
import yc.sdk.dto.serviceaccounts.GetServiceAccountDto;
import yc.sdk.dto.serviceaccounts.GetServiceAccountsDto;
import yc.sdk.dto.serviceaccounts.UpdateServiceAccountDto;

public class ServiceAccountsWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://iam.api.cloud.yandex.net/iam/v1/serviceAccounts").build();

    public Operation createServiceAccount(String authenticationToken, CreateServiceAccountDto createServiceAccountDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createServiceAccountDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteServiceAccount(String authenticationToken, String serviceAccountId) {
        return webClient
                .delete()
                .uri("/" + serviceAccountId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetServiceAccountDto getServiceAccount(String authenticationToken, String serviceAccountId) {
        return webClient
                .get()
                .uri("/" + serviceAccountId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetServiceAccountDto.class)
                .block().getBody();
    }

    public GetServiceAccountsDto getServiceAccounts(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetServiceAccountsDto.class)
                .block().getBody();
    }

    public Operation updateServiceAccount(String authenticationToken, String serviceAccountId, UpdateServiceAccountDto updateServiceAccountDto) {
        return webClient
                .patch()
                .uri("/" + serviceAccountId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateServiceAccountDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
