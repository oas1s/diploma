import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.ServerlessContainerWebClient;
import yc.sdk.dto.serverlesscontainers.CreateServerlessContainerDto;
import yc.sdk.dto.serverlesscontainers.GetServerlessContainerDto;
import yc.sdk.dto.serverlesscontainers.GetServerlessContainersDto;
import yc.sdk.dto.serverlesscontainers.UpdateServerlessContainerDto;

public class Main11 {
    public static void main(String[] args) {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        ServerlessContainerWebClient client = new ServerlessContainerWebClient();
        client.createServerlessContainer(iamToken,CreateServerlessContainerDto.builder().name("test").description("adad").folderId(folderId).build());
        GetServerlessContainersDto getServerlessContainersDto = client.getServerlessContainers(iamToken,folderId);
        String id = getServerlessContainersDto.getContainers().stream().filter(c -> c.getName().equals("test")).findFirst().get().getId();
        client.updateServerlessContainer(iamToken,id, UpdateServerlessContainerDto.builder().name("tape").build());
        client.deleteServerlessContainer(iamToken,id);
    }
}
