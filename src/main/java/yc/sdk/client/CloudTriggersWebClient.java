package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.triggers.CreateTriggerDto;
import yc.sdk.dto.triggers.GetTriggerDto;
import yc.sdk.dto.triggers.GetTriggersDto;
import yc.sdk.dto.triggers.UpdateTriggerDto;

public class CloudTriggersWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://serverless-triggers.api.cloud.yandex.net/triggers/v1/triggers").build();

    public Operation createTrigger(String authenticationToken, CreateTriggerDto createTriggerDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createTriggerDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteTrigger(String authenticationToken, String triggerId) {
        return webClient
                .delete()
                .uri("/" + triggerId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetTriggerDto getTrigger(String authenticationToken, String triggerId) {
        return webClient
                .get()
                .uri("/" + triggerId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetTriggerDto.class)
                .block().getBody();
    }

    public GetTriggersDto getTriggers(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetTriggersDto.class)
                .block().getBody();
    }

    public Operation updateTrigger(String authenticationToken, String triggerId, UpdateTriggerDto updateTriggerDto) {
        return webClient
                .patch()
                .uri("/" + triggerId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateTriggerDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
