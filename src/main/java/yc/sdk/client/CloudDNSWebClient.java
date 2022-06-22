package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.clouddns.CreateCloudDNSDto;
import yc.sdk.dto.clouddns.GetCloudDNSDto;
import yc.sdk.dto.clouddns.GetCloudDNSesDto;
import yc.sdk.dto.clouddns.UpdateCloudDNSDto;
import yc.sdk.dto.common.Operation;


public class CloudDNSWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://dns.api.cloud.yandex.net/dns/v1/zones").build();

    public Operation createCloudDNS(String authenticationToken, CreateCloudDNSDto createCloudDNSDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createCloudDNSDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteCloudDNS(String authenticationToken, String cloudDNSId) {
        return webClient
                .delete()
                .uri("/" + cloudDNSId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetCloudDNSDto getCloudDNS(String authenticationToken, String cloudDNSId) {
        return webClient
                .get()
                .uri("/" + cloudDNSId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetCloudDNSDto.class)
                .block().getBody();
    }

    public GetCloudDNSesDto getCloudDNSs(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetCloudDNSesDto.class)
                .block().getBody();
    }

    public Operation updateCloudDNS(String authenticationToken, String cloudDNSId, UpdateCloudDNSDto updateCloudDNSDto) {
        return webClient
                .patch()
                .uri("/" + cloudDNSId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateCloudDNSDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
