package yc.sdk.client;

import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.folder.CreateFolderDto;
import yc.sdk.dto.folder.GetFolderDto;
import yc.sdk.dto.folder.GetFoldersDto;
import yc.sdk.dto.folder.UpdateFolderDto;

public class FolderWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://resource-manager.api.cloud.yandex.net/resource-manager/v1/folders").build();

    public Operation createFolder(String authenticationToken,CreateFolderDto createFolderDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createFolderDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteFolder(String authenticationToken, String folderId) {
        return webClient
                .delete()
                .uri("/" + folderId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetFolderDto getFolder(String authenticationToken, String folderId) {
        return webClient
                .get()
                .uri("/" + folderId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetFolderDto.class)
                .block().getBody();
    }

    public GetFoldersDto getFolders(String authenticationToken,String cloudId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("cloudId",cloudId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetFoldersDto.class)
                .block().getBody();
    }

    public Operation updateFolder(String authenticationToken, String folderId, UpdateFolderDto updateFolderDto) {
        return webClient
                .patch()
                .uri("/" + folderId)
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(updateFolderDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }
}
