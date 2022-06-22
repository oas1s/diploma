import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudWebClient;
import yc.sdk.client.FolderWebClient;
import yc.sdk.dto.folder.CreateFolderDto;
import yc.sdk.dto.folder.UpdateFolderDto;

public class Main7 {
    public static void main(String[] args) {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        CloudWebClient client = new CloudWebClient();
        String cloudId = client.getClouds(iamToken).getClouds().get(0).getId();
        FolderWebClient folderWebClient = new FolderWebClient();
        folderWebClient.createFolder(iamToken,CreateFolderDto.builder().cloudId(cloudId).name("test2").description("desc").build());
        String folderId = folderWebClient.getFolders(iamToken,cloudId).getFolders().stream().filter(f -> f.getName().equals("test2")).findFirst().get().getId();
        folderWebClient.updateFolder(iamToken,folderId, UpdateFolderDto.builder().name("test1").build());
        folderWebClient.deleteFolder(iamToken,folderId);
    }
}
